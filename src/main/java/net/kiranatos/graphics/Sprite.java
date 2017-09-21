package net.kiranatos.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.WritableImage;
import net.kiranatos.level.Tile;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;
import net.kiranatos.utils.Utils;

public class Sprite extends Tile {
    private SpriteSheet sheet;
    private float scale;
    //private BufferedImage image;
    //private WritableImage image;

    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
        image = sheet.getSprite(0);
        image = Utils.resize(image, (int)(image.getWidth() * scale), (int)(image.getHeight() * scale));
    }
    
    /*
    public void render (float x, float y){
        try {
            //BufferedImage image = sheet.getSprite(0);
            //g.drawImage(image, (int)(x), (int)(y), null);
            Keeper.getKeeper().getGraphicsContext().drawImage(image, x, y);
        } catch (TankException ex) { Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex); }
        
    }       */
}