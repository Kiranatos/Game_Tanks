package net.kiranatos.level;


import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import net.kiranatos.subsidiary.Information;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;
import net.kiranatos.utils.Utils;

public class MyTile  extends Tile {
    
    private TileType myType;
    private int atlasWidth;
    private int atlasHeight;
    private int resizedWidth;
    private int resizedHeight;
    
    /**
     * CONSTRUCTOR
     * @param image
     * @param scale - в сколько раз увеличить (в 1, в 2, в 3, в 4)
     * @param myType 
     */
    public MyTile(WritableImage image, int scale, TileType myType) {        
        this.myType = myType;
        atlasWidth = (int) image.getWidth();
        atlasHeight = (int) image.getHeight();
        resizedWidth = atlasWidth * scale;
        resizedHeight = atlasHeight * scale;
        Information.println("Resizing... " + myType + "\tfrom [" + image.getWidth() + " : " + image.getHeight() +
                "] to [" + (int)image.getWidth() * scale + " : " + (int)image.getHeight() * scale + "]");
        this.image = Utils.resize(image, (int)image.getWidth() * scale, (int)image.getHeight() * scale);
    }

    //--------------------------------------------------------- GETTERS & SETTERS
    /**
     * Геттер возвращающий тип: EMPTY(0), BRICK(1), METAL(2), WATER(3), GRASS(4), ICE(5)
     * @return 
     */
    protected TileType getType() { return myType; }
    
    public int getAtlasWidth() {
        return atlasWidth;
    }

    public int getAtlasHeight() {
        return atlasHeight;
    }

    public int getResizedWidth() {
        return resizedWidth;
    }

    public int getResizedHeight() {
        return resizedHeight;
    }
    
    
    
    
    
    
    
    
    
    
    
//private Image image;
    //private WritableImage image;    
        
    /*
    private BufferedImage bufferedImage;
    @Deprecated
    public MyTile(BufferedImage image, int scale, MyTileType myType) {        
        this.myType = myType;
        this.bufferedImage = Utils.resize(image, (int)image.getWidth() * scale, (int)image.getHeight() * scale);
    }*/ 
    
    
}
