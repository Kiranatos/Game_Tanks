package net.kiranatos.graphics;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import net.kiranatos.utils.ResourceLoader;

public class TextureAtlas {
    
    BufferedImage image;

    public TextureAtlas(InputStream imageName) {
        image = ResourceLoader.loadImage(imageName);
    }
    
    public BufferedImage cut (int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }    
}
