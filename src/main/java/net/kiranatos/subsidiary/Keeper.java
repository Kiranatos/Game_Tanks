package net.kiranatos.subsidiary;

import java.io.InputStream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.kiranatos.IO.Input;

public class Keeper {
    
    private static Keeper iAmMasterKeeper;
    private final InputStream  TEXTURE_ATLAS_FILESTREAM;    
    private final Image TEXTURE_ATLAS_IMAGE;
    private static GraphicsContext backGroundGraphicsContext;
    private static GraphicsContext actionGraphicsContext;
    private static GraphicsContext woodGraphicsContext;
    private static Input inputCanvas;
    private static boolean flagOfStartinGameWindow = false;
    
    // ---------------------------- Constructor    
    private Keeper() {         
        TEXTURE_ATLAS_FILESTREAM = (InputStream)Keeper.class.getClassLoader().getResourceAsStream("atlas/texture_atlas.png");        
        TEXTURE_ATLAS_IMAGE = new Image(TEXTURE_ATLAS_FILESTREAM);
        //inputCanvas = new Input();
    }
    /**
     * Получить объект Keeper
     * @return 
     */
    public static Keeper getKeeper(){
        if (iAmMasterKeeper==null) {
            iAmMasterKeeper = new Keeper();
            Information.println("[Object Keeper was created]");
        }        
        return iAmMasterKeeper;
    }
 
    
    // ------------------------------------- GRAPHICS CONTEXT from CANVAS
    /**
     * Сеттер для нижнего Canvas-а Tanks. Это фон, где будут находится вечные, 
     * неразрушимые вещи: ТЬМА, ЛЁД
     * Идея: отрисовать один раз, а не постоянно рендерить в цикле
     * @param graphicsContext
     */
    public static void setBackGroundGraphicsContext(GraphicsContext graphicsContext) {
        Keeper.backGroundGraphicsContext = graphicsContext;   
        Information.println("GraphicsContext нижнего фонового слоя в Keeper установлен!");
    }
    /**
     * Геттер для нижнего Canvas-а Tanks. Это фон, где будут находится вечные, 
     * неразрушимые вещи: ТЬМА, ЛЁД
     * Идея: отрисовать один раз, а не постоянно рендерить в цикле
     */
    public static GraphicsContext getBackGroundGraphicsContext() throws TankException {
        long begin = System.nanoTime();
        int i = 1;
        while (backGroundGraphicsContext == null) {            
            Information.println("GraphicsContext not yet created!");
            Information.sleepMilliseconds(100);
            i++;
            if (i==50) {
                long passed = System.nanoTime() - begin;
                throw new TankException("Ошибка в геттере класса Keeper. NULL-значение BG GraphicsContext Canvas. Время ожидания: " + passed){};
            }
        }
        //Information.println("GraphicsContext был получен за " + (System.nanoTime() - begin) + " Milliseconds.");
        return backGroundGraphicsContext;
    }
    
    /**
     * Сеттер для среднего Canvas-а Tanks. Это непосредственно игровой слой, где будут проходить игра и битва
     * здесь изменяемые вещи: ТАНКИ, КИРПИЧИ, БРОНЯ, ШТАБ, ВОДА, БОНУСЫ
     * Идея: возможно в будущем, он разделится ещё на несколько разных слоев
     * @param graphicsContext
     */
    public static void setActionGraphicsContext(GraphicsContext graphicsContext) {
        Keeper.actionGraphicsContext = graphicsContext;   
        Information.println("GraphicsContext нижнего фонового слоя в Keeper установлен!");
    }
    /**
     * Геттер для среднего Canvas-а Tanks. Это непосредственно игровой слой, где будут проходить игра и битва
     * здесь изменяемые вещи: ТАНКИ, КИРПИЧИ, БРОНЯ, ШТАБ, ВОДА, БОНУСЫ
     * Идея: возможно в будущем, он разделится ещё на несколько разных слоев
     */
    public static GraphicsContext getActionGraphicsContext() throws TankException {
        long begin = System.nanoTime();
        int i = 1;
        
        while (actionGraphicsContext == null) {            
            Information.println("GraphicsContext not yet created!");
            Information.sleepMilliseconds(100);
            i++;
            if (i==50) {
                long passed = System.nanoTime() - begin;
                throw new TankException("Ошибка в геттере класса Keeper. NULL-значение Action GraphicsContext Canvas. Время ожидания: " + passed){};
            }
        }
        //Information.println("GraphicsContext был получен за " + (System.nanoTime() - begin) + " Milliseconds.");
        return actionGraphicsContext;
    }
    
    /**
     * Сеттер для верхнего Canvas-а Tanks. Пока это только лес, который 
     * должен находится над полем битвы.
     * Идея: также отрисовать один раз, как и фон и не рендерить в цикле
     * @param graphicsContext
     */
    public static void setWoodGraphicsContext(GraphicsContext graphicsContext) {
        Keeper.woodGraphicsContext = graphicsContext;   
        Information.println("GraphicsContext нижнего фонового слоя в Keeper установлен!");
    }
    /**
     * Геттер для верхнего Canvas-а Tanks. Пока это только лес, который 
     * должен находится над полем битвы.
     * Идея: также отрисовать один раз, как и фон и не рендерить в цикле
     */
    public static GraphicsContext getWoodGraphicsContext() throws TankException {
        long begin = System.nanoTime();
        int i = 1;
        while (woodGraphicsContext == null) {            
            Information.println("GraphicsContext not yet created!");
            Information.sleepMilliseconds(100);
            i++;
            if (i==50) {
                long passed = System.nanoTime() - begin;
                throw new TankException("Ошибка в геттере класса Keeper. NULL-значение Wood GraphicsContext Canvas. Время ожидания: " + passed){};
            }
        }
        //Information.println("GraphicsContext был получен за " + (System.nanoTime() - begin) + " Milliseconds.");
        return woodGraphicsContext;
    }

    
    // ---------------------------- Image: TEXTURE_ATLAS    
    public InputStream getTextureAtlasInputStream() {
        return TEXTURE_ATLAS_FILESTREAM;
    }
    public Image getTextureAtlasImage() {
        return TEXTURE_ATLAS_IMAGE;
    }
    
    
    // ----------------------------  Setters & Getters
    /**
     * Getter for Input
     */
    public Input getInputCanvas() {
        if ( inputCanvas == null ) {
            inputCanvas = new Input();
        }
        return inputCanvas;
    }
    
    /**
     * Сеттер для установки флага об запуске и готовности потока GUI, игрового окна 
     * и всех необходимых, созданных объектах. Нужен для ожидания в другом, системном, игровом потоке
     */
    public static void setFlagOfStartGameWindow(boolean areWeReady) {
        Keeper.flagOfStartinGameWindow = areWeReady;
    }
    
    /**
     * Геттер для получения флага об запуске и готовности потока GUI, игрового окна 
     * и всех необходимых, созданных объектах. Нужен для ожидания в другом, системном, игровом потоке
     */
    public static boolean getFlagOfStartGameWindow() {
        return Keeper.flagOfStartinGameWindow;
    }
}
