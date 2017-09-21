package net.kiranatos.game;
public enum EntityType {    
    
    EMPTY(0), PLAYER(1), ENEMY(2), ALLY(3);    

    private int n;

    private EntityType(int n) {
        this.n = n;
    }
    
    public int numeric() { return n; }
    
    public static EntityType fromNumeric(int n) {
        switch (n) {
            case 1: return PLAYER;
            case 2: return ENEMY;
            case 3: return ALLY;            
            default: return EMPTY;
        }
    }
}
