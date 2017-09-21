package net.kiranatos.IO;

import java.util.Arrays;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import net.kiranatos.subsidiary.Information;

public class Input implements EventHandler<KeyEvent> {
    
    @Override
    public void handle(KeyEvent event) {
        //System.out.print(".key=");
        //System.out.println(event.getCode().ordinal() + ".");
        //System.out.println(event.getCode());
        map[event.getCode().ordinal()] = true;        
    }
    
    private boolean[] map;

    public Input() {
        map = new boolean[256];
        Information.println("In Constructor Input");
        for (int i = 0; i < map.length; i++) {
            map[i] = false;            
        }
    }
    
    public boolean[] getMap() {
        return Arrays.copyOf(map, map.length);
    }
    
    public boolean getKey(int keyCode) {
        boolean key = map[keyCode];
        map[keyCode] = false;
        return key;
    }
}
