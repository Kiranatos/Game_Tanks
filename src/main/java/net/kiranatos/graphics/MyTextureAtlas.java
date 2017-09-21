package net.kiranatos.graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.IntBuffer;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;
import net.kiranatos.subsidiary.Information;
import net.kiranatos.subsidiary.TestHelp;

public class MyTextureAtlas {
    
    PixelReader pixelReader;
    //int[] buffer = new int[256 * 400 * 3];
    
    /**
     * CONSTRUCTOR
     * Получает на вход Image @param image из которого устанавливает в приватное поле его PixelReader
     */
    public MyTextureAtlas(Image image) {  
        pixelReader = image.getPixelReader();
        Information.println(" Width: " + image.getWidth() + "Height: " + image.getHeight());        
        Information.println(" PixelFormat: " + image.getPixelReader().getPixelFormat());        
    }
    
    /**
     * Вырезает часть текстуры рисунка и возвращает WritableImage, - это некий аналог BufferedImage для FX
     * Использует PixelReader, взятый из Image
     */
    public WritableImage cut (int x, int y, int w, int h) {
        //PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();
        //WritablePixelFormat<IntBuffer> pixelFormat = WritablePixelFormat.getIntArgbInstance();        
        //pixelReader.getPixels(x, y, w, h, pixelFormat, buffer, 0, 400 * 3);
        //System.out.printf("%d %d %d %d ", x,y,w,h);   
        
        
        WritableImage wImage = new WritableImage(pixelReader, x, y, w, h);
        
        TestHelp.showImage(wImage);
        
        //WritableImage wImage = new WritableImage(w, h);
        PixelWriter pixelWriter = wImage.getPixelWriter();
        //PixelReader pixelReaderOut = new PixelReader();
        
        for (int i = x; i < w; i++) {
            for (int j = y; j < h; j++) {
                Color c = pixelReader.getColor(i, j);
                pixelWriter.setColor(i, j, c.grayscale());  
                //System.out.println(pixelWriter.getPixelFormat());
            }            
        }
        
        
        //WritableImage wImage = new WritableImage(pixelReader, x, y, w, h);
        return wImage; //image.getSubimage(x, y, w, h);
    }    

    
    
    
    
    
    // ------------------------------------------------------DEPRECATED METODS
    /**
     * Не понял, почему из Keeper-а не закружается потоr InputStream, хотя он же используется
     * для создания Image, который прекрасно закгружается. 
     */
    @Deprecated
    public MyTextureAtlas(InputStream inputStreamImage) {        
        Image imageIn = new Image(inputStreamImage);
        pixelReader = imageIn.getPixelReader();
        Information.println(" Width: " + imageIn.getWidth() + "Height: " + imageIn.getHeight());                
        /*   BufferedImage image = null;        
        try {
            image = ImageIO.read(inputStreamImage);
        } catch (IOException ex) { Information.println("------"); ex.printStackTrace(); }*/
    }
}
