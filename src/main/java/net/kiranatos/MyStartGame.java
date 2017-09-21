package net.kiranatos;

import java.io.IOException;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;

public class MyStartGame {   
    public static void main(String[] args) throws IOException, TankException {
        
        
        
        MyGame tanks = new MyGame();
        tanks.startMyGame();
    }
} 
    