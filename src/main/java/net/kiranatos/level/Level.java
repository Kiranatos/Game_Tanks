package net.kiranatos.level;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kiranatos.game.Game;
import net.kiranatos.graphics.TextureAtlas;
import net.kiranatos.utils.Utils;

public class Level {
    
    public static final int TILE_SCALE = 8;
    public static final int TILE_IN_GAME_SCALE = 2;
    public static final int SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int TILE_IN_WIDTH = Game.WIDTH / SCALED_TILE_SIZE;
    public static final int TILE_IN_HEIGHT = Game.HEIGHT / SCALED_TILE_SIZE;
    
    public static final InputStream  LEVEL_ONE_FILE_NAME    = Level.class.getClassLoader().getResourceAsStream("lvl/level1.lvl");
    
    private Integer[][] tileMap;
    private Map<TileType, Tile> tiles;
    private List<Point> grassCords;

    public Level(TextureAtlas atlas) {
        
        
        tileMap = new Integer[TILE_IN_WIDTH] [TILE_IN_HEIGHT];
        tiles = new HashMap<TileType, Tile>();
        tiles.put(TileType.BRICK, new Tile(atlas.cut(32 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(atlas.cut(32 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.WATER, new Tile(atlas.cut(32 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.GRASS, new Tile(atlas.cut(34 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.ICE,   new Tile(atlas.cut(36 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(atlas.cut(36 * TILE_SCALE, 6 * TILE_SCALE, TILE_SCALE, TILE_SCALE), TILE_IN_GAME_SCALE, TileType.EMPTY));
        
        tileMap = Utils.levelParser(LEVEL_ONE_FILE_NAME);
        grassCords = new ArrayList<Point>();
        for (int i=0; i<tileMap.length; i++) {
            for (int j=0; j<tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() == TileType.GRASS) 
                    grassCords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
            }
        }
        //tileMap = new int[TILE_IN_WIDTH] [TILE_IN_HEIGHT];
        
        /*
        tileMap[10][10] = TileType.BRICK.numeric();
        tileMap[11][10] = TileType.METAL.numeric();
        tileMap[12][10] = TileType.WATER.numeric();
        tileMap[13][10] = TileType.GRASS.numeric();
        tileMap[14][10] = TileType.ICE.numeric();*/
       
    }
    
    public void update() {}
    
    public void render(Graphics2D g) {
        for (int i=0; i< tileMap.length; i++) {
            for (int j=0; j< tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() != TileType.GRASS) 
                    tile.render(g, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);
            }
        }        
    }
    
    public void renderGrass(Graphics2D g) {
        for (Point p : grassCords) {
            tiles.get(TileType.GRASS).render(g, p.x, p.y);
        }    
    }
}
