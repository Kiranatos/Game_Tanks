package net.kiranatos.game;

import java.awt.Graphics2D;
import java.io.InputStream;
import net.kiranatos.Display;
import net.kiranatos.IO.Input;
import net.kiranatos.graphics.TextureAtlas;
import net.kiranatos.level.Level;
import net.kiranatos.utils.Time;
        
public class Game implements Runnable {
    
    public static final int     WIDTH    = 800;
    public static final int     HEIGHT    = 600;
    public static final String  TITLE    = "Tanks";
    public static final int     CLEAR_COLOR    = 0xff000000;
    public static final int     NUM_BUFFERS    = 3;
    
    public static final float   UPDATE_RATE   = 60.0f;
    public static final float   UPDATE_INTERVAL   = Time.SECOND / UPDATE_RATE;
    public static final long    IDLE_TIME    = 1;   //в миллисекундах
    
    //public static final String  ATLAS_FILE_NAME    = "texture_atlas.png";
    public static final InputStream  ATLAS_FILE_NAME    = Game.class.getClassLoader().getResourceAsStream("atlas/texture_atlas.png");
    
    
    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private Player player;
    private Level lvl;
/*    private SpriteSheet sheet;
    private Sprite sprite;
    
    // temp
    float x = 350;
    float y = 250;
    float delta = 0;
    float radius = 50;
    float speed = 3;
    // temp*/
    
    public Game (){
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        player = new Player(300,300,2,3,atlas);
        lvl = new Level(atlas);
   //     sheet = new SpriteSheet(atlas.cut(1*16, 9*16, 16, 16), 2, 16);
     //   sprite = new Sprite(sheet, 1);
    }
    
    public synchronized void start(){
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
        } catch (InterruptedException ex) {
            ex.printStackTrace();            
        }
        
        cleanUp();
    }
    
    private void update(){
        
        player.update(input);
        lvl.update();
        
       /* if (input.getKey(KeyEvent.VK_UP)) y -= speed;
        if (input.getKey(KeyEvent.VK_DOWN)) y += speed;
        if (input.getKey(KeyEvent.VK_LEFT)) x -= speed;
        if (input.getKey(KeyEvent.VK_RIGHT)) x += speed;*/
    }

    private void render(){
        Display.clear();
        lvl.render(graphics);
        player.render(graphics);
        lvl.renderGrass(graphics);
  //      sprite.render(graphics, x, y);
        Display.swapBuffers();
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
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }
            if (render) { render(); fps++; }
            else { 
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
            if (count >= Time.SECOND ) {
                Display.setTitle(TITLE + " || FPS: " + fps + " || update: " + upd + " || update loop: " + updl);
                fps = 0;
                upd = 0;
                updl = 0;
                count = 0;
            }        
        }
    }
    
    private void cleanUp(){
        Display.destroy();
    }    
}
