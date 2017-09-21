package net.kiranatos.game;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.kiranatos.IO.Input;
import net.kiranatos.MyGame;
import net.kiranatos.graphics.Sprite;
import net.kiranatos.graphics.SpriteSheet;
import net.kiranatos.graphics.MyTextureAtlas;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;

public class Player extends Entity {
    
    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_PER_HEADING = 1;    
    
    private enum Heading {
        NORTH(0*SPRITE_SCALE, 0*SPRITE_SCALE, 1*SPRITE_SCALE, 1*SPRITE_SCALE),
        EAST (6*SPRITE_SCALE, 0*SPRITE_SCALE, 1*SPRITE_SCALE, 1*SPRITE_SCALE),
        SOUTH(4*SPRITE_SCALE, 0*SPRITE_SCALE, 1*SPRITE_SCALE, 1*SPRITE_SCALE),
        WEST (2*SPRITE_SCALE, 0*SPRITE_SCALE, 1*SPRITE_SCALE, 1*SPRITE_SCALE),;
        
        private int x,y,h,w;

        private Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }
        
        protected WritableImage texture(MyTextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }
    
    private Heading heading;
    private Map<Heading, Sprite> spriteMap;
    private float scale;
    private float speed;

    // 300, 300, 2, 3,atlas = new MyTextureAtlas(Keeper.getKeeper().getTextureAtlasImage());
    public Player(float x, float y, float scale, float speed, MyTextureAtlas atlas) {
        super(EntityType.PLAYER, x, y);
        
        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale;
        this.speed = speed;
        
        for(Heading h : Heading.values()){
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(h, sprite);
        }
    }

    @Override
    public void update() {
        Input input = Keeper.getKeeper().getInputCanvas();
        float newX = x;
        float newY = y;
        
        if (input.getKey(17)) {   // UP = 17  KeyCode.KP_UP.ordinal() = 114
            System.out.println("UP");
            newY -= speed;
            heading = Heading.NORTH;
        } else if (input.getKey(18)) { // RIGHT = 18
            newX += speed;
            heading = Heading.EAST;
        } else if (input.getKey(19)) { // DOWN = 19
            newY += speed;
            heading = Heading.SOUTH;
        } else if (input.getKey(16)) { // LEFT = 16
            newX -= speed;
            heading = Heading.WEST;
        }
        
        if (newX < 0) { newX = 0; }
        else if (newX >= MyGame.WIDTH - SPRITE_SCALE * scale ) {
            newX = MyGame.WIDTH - SPRITE_SCALE * scale;
        }
        
        if (newY < 0) { newY = 0; }
        else if (newY >= MyGame.HEIGHT - SPRITE_SCALE * scale ) {
            newY = MyGame.HEIGHT - SPRITE_SCALE * scale;
        }
        
        x = newX;
        y = newY;
    }

    @Override
    public void render() {
        try {            
            //System.out.println("render player " + heading.toString() + "[" + x +":"+y+"]");
            //System.out.println((int)x + " : " + (int)y + " " + Keeper.getActionGraphicsContext());
            spriteMap.get(heading).render((int)x, (int)y , Keeper.getActionGraphicsContext());
        } catch (TankException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}