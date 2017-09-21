package net.kiranatos.graphics;

import java.awt.image.BufferedImage;
import javafx.scene.image.WritableImage;

public class SpriteSheet {
    private WritableImage sheet;
    private int spriteCount;
    private int scale;
    private int spritesInWidth;

    public SpriteSheet(WritableImage sheet, int spriteCount, int scale) { //1 16
        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.scale = scale;
        this.spritesInWidth = (int) sheet.getWidth() / scale;
    }
    
    public WritableImage getSprite(int index) {
        index = index % spriteCount;
        int x = index % spritesInWidth * scale;
        int y = index / spritesInWidth * scale;
        
        //return sheet.getSubimage(x, y, scale, scale);
        return new WritableImage(sheet.getPixelReader(), x, y, scale, scale);
    }
}
