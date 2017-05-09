package tanks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

public class DisplayOldWrongVersion01 {
    
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    
    public static void create (int width, int height, String title) {
        if (created) return;
        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas(){
            public void paint (Graphics g) {
                super.paint(g); //нужно запускать родительскую, чтобы не возникало артефактов
                render(g);
            }
        };
        
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);
        content.setBackground(Color.red);
        
        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack(); //Изменит размер окна, так чтобы он подходил под размер контента
        window.setLocationRelativeTo(null); //окно появляется по центру экрана
        window.setVisible(true);        
    }
    
    public static void render(){
        content.repaint();
    }
    public static void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(400-50, 300-50, 100, 100);
        
    }
    
}
