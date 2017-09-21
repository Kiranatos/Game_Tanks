package net.kiranatos.subsidiary;

public class TankException extends Exception {
    /**
     * Обычный System.err.println(message)
     * и наследование от Exception
     * позже в планах сделаю по-умнее
     * @param message 
     */
    public TankException(String message) {
        System.err.println(message);
    }    
}
