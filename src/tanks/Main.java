package tanks;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import tanks.game.Game;

public class Main {   
    public static void main(String[] args) {
        Game tanks = new Game();
        tanks.start();
    }
}
    
    /*
Перечитать все комменты в последних двух видео №10 №11
https://www.youtube.com/playlist?list=PLwVvOVLAoKQl84bk2aCtkZgfb0qvq6ZHA
*/
    
    
    /*
    public static void main(String[] args) {
        
        Display.create(800, 600, "Tanks", 0xff00ff00, 3); //гексадецимальное значение
        
        Timer t = new Timer (1000/60, new AbstractAction(){ //60 рaz в сек интревал времени за которую вызывает некоторую функцию
            @Override
            public void actionPerformed(ActionEvent e) {                
                Display.clear();
                Display.render();
                Display.swapBuffers();
            }
        });
        
        t.setRepeats(true); //таймер должен повторятся иначе он пробежит только раз
        t.start();
        
    } */


/*
Найти таблицы
цвета в гексадецимальном формате
*/