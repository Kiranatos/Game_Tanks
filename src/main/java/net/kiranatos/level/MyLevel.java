package net.kiranatos.level;

import java.awt.Point;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.MyGame;
import net.kiranatos.graphics.MyTextureAtlas;
import net.kiranatos.subsidiary.Information;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;
import net.kiranatos.utils.Utils;

public class MyLevel {
    
    public static final int TILE_SCALE = 8;
    public static final int TILE_IN_GAME_SCALE = 2;
    public static final int SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int TILE_IN_WIDTH = MyGame.WIDTH / SCALED_TILE_SIZE; // 800/16 =50
    public static final int TILE_IN_HEIGHT = MyGame.HEIGHT / SCALED_TILE_SIZE; // 600/16 = 37.5
    
    public static final InputStream  LEVEL_ONE_FILE_NAME    = MyLevel.class.getClassLoader().getResourceAsStream("lvl/level1.lvl");
    
    private Integer[][] tileMap;
    private Map<TileType, MyTile> tiles;
    private List<Point> grassCords;

    public MyLevel(MyTextureAtlas atlas) {
        
        
        tileMap = new Integer[TILE_IN_WIDTH] [TILE_IN_HEIGHT];
        tiles = new HashMap<TileType, MyTile>();
        tiles.put(TileType.BRICK, new MyTile(atlas.cut(32 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new MyTile(atlas.cut(32 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.WATER, new MyTile(atlas.cut(32 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.GRASS, new MyTile(atlas.cut(34 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.ICE,   new MyTile(atlas.cut(36 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new MyTile(atlas.cut(36 * TILE_SCALE, 6 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.EMPTY));
        
        tileMap = Utils.levelParser(LEVEL_ONE_FILE_NAME);
        grassCords = new ArrayList<Point>();
        for (int i=0; i<tileMap.length; i++) {
            for (int j=0; j<tileMap[i].length; j++) {
                MyTile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.getType() == TileType.GRASS) 
                    grassCords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
            }
        }       
    }
    
    public void render() throws TankException {
        //System.out.print("*");        
        for (int i=0; i< tileMap.length; i++) {
            for (int j=0; j< tileMap[i].length; j++) {
                MyTile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.getType() != TileType.GRASS) 
                    tile.render(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE, Keeper.getBackGroundGraphicsContext());
            }
        }   
    }
    
    public void renderGrass() {
        //System.out.print("U");
        try {
            for (Point p : grassCords) {
                tiles.get(TileType.GRASS).render(p.x, p.y, Keeper.getWoodGraphicsContext());
            }   
        } catch (TankException ex) {
            Information.println("Error in render grass");
            Logger.getLogger(MyLevel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
