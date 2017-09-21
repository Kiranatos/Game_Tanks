package net.kiranatos.utils;

public class Time {    
    public static final long SECOND = 10_000_000_00l;
    /**
     * Обычный System.nanoTime()
     * @return 
     */
    public static long get() {
        return System.nanoTime();        
    }    
}
