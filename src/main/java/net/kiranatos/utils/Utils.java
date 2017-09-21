package net.kiranatos.utils;

import java.util.List;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class Utils {
    
    /**
     * Метод изменяющий размеры рисунка, например:
     * @param image - объект WritableImage, имеющий размеры [X : Y]
     * @param width - новая ширина
     * @param height - новая висота
     * @return - возвращает объект WritableImage, имеющий размеры [width : height]
     * реализован через костыль старого метода - WritableImage превращается в BufferedImage,
     * который изменяет размер, а потом обратно в новый WritableImage
     * Позже подумать об вариантах решения:
     * - либо из Deprecated-методов, описаных ниже - довести до идеала
     * - либо перестроить часть программы, сделав заготовки нужых матриц и объектов WritableImage, 
     * которые будут загружаться из сериализованных файлов, а не строится в runtime
     */
    public static WritableImage resize(WritableImage image, int width, int height) {
        BufferedImage biInput = SwingFXUtils.fromFXImage(image, null);
        
        BufferedImage biOutput = new BufferedImage(width, height, biInput.getType());
        biOutput.getGraphics().drawImage(biInput, 0, 0, width, height, null);
        
        WritableImage newImage = SwingFXUtils.toFXImage(biOutput, null);        
        return newImage;                
    }
    
    /**
     * 
     * @param sArr
     * @return 
     */
    public static final Integer[] str2int_arrays(String[] sArr) {
        Integer[] result = new Integer[sArr.length];
        for (int i = 0; i< sArr.length; i++)
            result[i] = Integer.parseInt(sArr[i]);
        return result;
    }
    
    /**
     * Парсер уровней, получает на вход файл уровня и возврашает массив Integer[][]
     * @param inputStreamFile
     * @return 
     */
    public static Integer[][] levelParser(InputStream inputStreamFile) {        
        Integer [][] result = null;        
        try ( BufferedReader reader = new BufferedReader( new InputStreamReader(inputStreamFile) ) ) {
            String line = null;
            
            List<Integer[]> lvlLines = new ArrayList<Integer[]>();
            while((line = reader.readLine()) != null) {
                lvlLines.add(str2int_arrays(line.split(" ")));
            }
            
            result = new Integer[lvlLines.size()][lvlLines.get(0).length];
            for (int i=0; i<lvlLines.size(); i++) {
                result[i] = lvlLines.get(i);
            }            
        } catch (IOException e) { e.printStackTrace();  }
        return result;        
    }
    
    
    
    
    
    
    
    
    // ------------------------------------------------------DEPRECATED METODS
    /**
     * Метод изменяющий размеры рисунка.
     * Старый, оригинальный метод из Youtube-урока, принимающий и возвращающий BufferedImage
     */
    @Deprecated    
    public static BufferedImage resize01(BufferedImage image, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        return newImage;        
    }
    
    /**
     * 
     * @param image
     * @param width
     * @param height
     * @return
     * @deprecated
     */
    @Deprecated
    public static WritableImage resize02(WritableImage image, int width, int height) {
        WritableImage newImage = new WritableImage(width, height);
        //BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //newImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        int hImg = (int)image.getHeight();
        int wImg = (int)image.getWidth();
        int[] buffer = new int[wImg * hImg * 3];
        PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();    
        newImage.getPixelWriter().setPixels(0, 0, wImg, hImg, pixelFormat, buffer, 0, wImg * 3);
        return newImage;        
    }
    
    /**
     * Попытка сделать изменение размеров WritableImage, через метод .snapshot(null, null)
     * объекта ImageView. Метод рабочий, но только в JavaFX Application Thread. Поэтому пришлось от него отказаться, так как
     * выбрасывает IllegalStateException (if this method is called on a thread other than the JavaFX Application Thread)
     * А создавать лишние JavaFX Application Thread не хотелось.
     */   
    @Deprecated
    public static WritableImage resize03(WritableImage image, int width, int height) {
        System.out.println("input size: " + image.getWidth() +" : "+ image.getHeight());
        System.out.println("output size: " +width+" : "+ height);
        ImageView i1 = new ImageView();
        i1.setImage(image);
        //i1.setViewport(new Rectangle2D(10, 10, 15, 15)); // - вот так можно вырезать
        i1.setFitHeight(height);
        i1.setFitWidth(width);
        i1.setSmooth(true);        
        WritableImage newImage = i1.snapshot(null, null);        
        //WritableImage newImage = new WritableImage(i1.getImage().getPixelReader(), (int)i1.getImage().getWidth(), (int)i1.getImage().getHeight());
        return newImage;    
    }
    
    /**
     * Попытка реализовать изменение размеров нпосредственно силами WritableImage, 
     * используя .getPixelReader().getPixels .getPixelWriter().setPixels .getPixelWriter().setPixels и др.
     * Неуспешно!     
     */   
    @Deprecated
    public static WritableImage resize04(WritableImage image, int width, int height) {
        int scanlineStride = 4; //есть подозрения, что значекние зависит от RGB - 3, ARGB - 4
        int[] ar = new int[(int)image.getWidth() * (int)image.getHeight() * scanlineStride];
        System.out.println(" ar= " + ar.length);
        
        image.getPixelReader().getPixels(0, 0, (int)image.getWidth(), (int)image.getHeight(), 
                PixelFormat.getIntArgbInstance(), 
                ar, 
                0, //Смещение начала матрицы
                (int)image.getWidth() * scanlineStride); //длина строки пикселов рисунка, т.к. матрица одномерная
        
        
        int[] ar2 = new int[2 * 2 * 120 * 120 * scanlineStride];
        int k=0;
        for (int i = 0; i < ar.length; i++) {
            ar2[k++] = ar[i];
            ar2[k++] = ar[i];
        }        
        
        WritableImage newImage = new WritableImage(240, 240);
        System.out.println(" newImage W: " + newImage.getWidth() + " H: " + newImage.getHeight() );
        //BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //newImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        //int hImg = (int)image.getHeight();  System.out.println("H: " + hImg); //120
        //int wImg = (int)image.getWidth();   System.out.println("W: " + wImg); //120
        //int[] buffer = new int[wImg * hImg * 3]; //120 *120 *3
        //PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();    
        
        //newImage.getPixelWriter().setPixels(0, 0, wImg, hImg, pixelFormat, buffer, 0, wImg * 3);
        newImage.getPixelWriter().setPixels(0, 0, 240, 240, 
                PixelFormat.getIntArgbInstance(), 
                ar2, 
                0, 
                240 * scanlineStride );
        
        return newImage;        
    }     
}


