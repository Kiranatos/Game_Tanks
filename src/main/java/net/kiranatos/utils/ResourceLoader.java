package net.kiranatos.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ResourceLoader {
    public static final String PATH = "res/";
    
    public static BufferedImage loadImage(InputStream fileName){
        BufferedImage image = null;        
        try {
            //System.out.println(PATH + fileName);
            //image = ImageIO.read(new File(PATH + fileName));
            //image = ImageIO.read(new File("F:\\003_NetBeans\\MyProjects\\Game_Tanks\\src\\tanks\\utils\\res\\texture_atlas.png"));
            image = ImageIO.read(fileName);
        } catch (IOException ex) { ex.printStackTrace(); }
        
        return image;
    }
}
