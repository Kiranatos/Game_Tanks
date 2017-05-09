package tanks.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ResourceLoader {
    public static final String PATH = "res/";
    
    public static BufferedImage loadImage(String fileName){
        BufferedImage image = null;        
        try {
            //System.out.println(PATH + fileName);
            //image = ImageIO.read(new File(PATH + fileName));
            image = ImageIO.read(new File("F:\\003_NetBeans\\MyProjects\\Game_Tanks\\src\\tanks\\utils\\res\\texture_atlas.png"));
        } catch (IOException ex) { ex.printStackTrace(); }
        
        return image;
    }
}
