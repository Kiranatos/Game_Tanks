package net.kiranatos;

import java.util.logging.*;
import net.kiranatos.IO.Input;
import net.kiranatos.fx.GameWindow;
import net.kiranatos.game.Player;
import net.kiranatos.graphics.MyTextureAtlas;
import net.kiranatos.level.MyLevel;
import net.kiranatos.subsidiary.Information;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;
import net.kiranatos.utils.Time;
        
public class MyGame implements Runnable {
    
    public static final int     WIDTH    = 800;
    public static final int     HEIGHT    = 600;
    public static final String  TITLE    = "Tanks";
    public static final int     CLEAR_COLOR    = 0xff000000;
    public static final int     NUM_BUFFERS    = 3;
    
    public static final float   UPDATE_RATE   = 60.0f;
    public static final float   UPDATE_INTERVAL   = Time.SECOND / UPDATE_RATE;
    public static final long    IDLE_TIME    = 1;   //в миллисекундах
    
    private static boolean running;

    private Thread gameThread;
    
    private MyTextureAtlas atlas;
    private Player player;
    private MyLevel lvl;
    
    GameWindow gameWindow;
    
    public MyGame () throws TankException {
        running = false;
        Keeper.getKeeper();
        
        gameWindow = new GameWindow();        
        Thread launchGameWindow = new Thread(gameWindow);
        launchGameWindow.start();
        
        int cycle = 0;
        long begin = System.nanoTime();
        while (!Keeper.getFlagOfStartGameWindow()) {            
            Information.sleepMilliseconds(100);
            cycle++;
            if (cycle==50) {
                long passed = System.nanoTime() - begin;
                throw new TankException("Слишком долгое ожидание при запуске GUI : " + passed + " (" + (passed/1000) + " sec.)" );
            }
        }
                
        atlas = new MyTextureAtlas(Keeper.getKeeper().getTextureAtlasImage());
        //(float x, float y, float scale, float speed, MyTextureAtlas atlas)
        player = new Player(300,300, 2, 3 ,atlas);
        lvl = new MyLevel(atlas);
    }
    
    
    public synchronized void startMyGame(){
        if (running) return;
        
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    
    public synchronized void stop(){
        if (!running) return;        
        running = false;        
        try {
            gameThread.join();
        } catch (InterruptedException ex) { ex.printStackTrace();    }        
        cleanUp();
    }
    
    private void update(){        
        player.update();
        //System.out.println("UPDATE");
        //lvl.update();        
    }

    private void render(){
        try {
            //Display.clear();
            lvl.render();            
            player.render();
            lvl.renderGrass();
            //Display.swapBuffers();
        } catch (TankException ex) { Logger.getLogger(MyGame.class.getName()).log(Level.SEVERE, null, ex);  }
    }

    @Override
    public void run() {
        
        int fps = 0;
        int upd = 0;
        int updl = 0;   //update loop
        
        long count = 0;
        
        float delta = 0;
        long lastTime = Time.get();
        while (running) { 
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;
            
            count += elapsedTime;
            
            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            
            while (delta > 1) {
                //System.out.println(" delta=" + delta + " ");
                update();
                upd++;
                delta--;
                if (render) { updl++;  } 
                else        { render = true; }
            }
            if (render)     { render(); fps++; }
            else            { Information.sleepMilliseconds(IDLE_TIME); }
            
            if (count >= Time.SECOND ) {
                gameWindow.setTitle(TITLE + " || FPS: " + fps + " || update: " + upd + " || update loop: " + updl);
                fps = 0;
                upd = 0;
                updl = 0;
                count = 0;
            }        
        }
    }
    
    private void cleanUp(){
        gameWindow.destroy();
    }    
    
    public static void setRunning(boolean running) {
        MyGame.running = running;
    }
}
