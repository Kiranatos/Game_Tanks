package net.kiranatos.level;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;

public abstract class Tile {
    
   public WritableImage image;  
   
   public synchronized void render(int x, int y, GraphicsContext graphicsContext) throws TankException{
        graphicsContext.drawImage(image, x, y);       
        
        //Keeper.getKeeper().getGraphicsContext().getCanvas().getGraphicsContext2D().drawImage(image, x, y);
        //Keeper.getKeeper().getGraphicsContext().drawImage(Keeper.getKeeper().getTextureAtlasImage(), x, y);
        /* try {  
            Keeper.getKeeper().getGraphicsContext().drawImage( 
                    Keeper.getKeeper().getTextureAtlasImage(), 0, 0, 800, 600
            );
        } catch (TankException ex) { }  */
    }    
}
