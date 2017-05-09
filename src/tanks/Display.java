package tanks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import javax.swing.JFrame;
import tanks.IO.Input;

public class Display {
    
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    
    private static BufferStrategy bufferStrategy;
    
    public static void create (int width, int height, String title, int _clearColor, int numBuffer) {
        if (created) return;
        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();
        
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setUndecorated(false); // при true - уберается рамка окна со "закрыть, свернуть, развернуть"
        //setUndecorated нужно вызывать до pack(). Иначе там создаются peer-объекты, а после их создания setUndecorated уже не вызвать.
        
        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack(); //Изменит размер окна, так чтобы он подходил под размер контента
        window.setLocationRelativeTo(null); //окно появляется по центру экрана
        window.setVisible(true);        
        
        
        //BufferedImage.TYPE_INT_ARGB - говорит буферу, что цвет будем хранить в масиве ARGB(прозрачность, красный, зел., голуб.) 
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();        
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Сглаживание
        clearColor = _clearColor;
        
        content.createBufferStrategy(numBuffer);
        bufferStrategy = content.getBufferStrategy();
        
        created = true;        
    }
        
    public static void clear(){        
        Arrays.fill(bufferData, clearColor);
    }    
   
    public static void swapBuffers() {
        //Graphics g = content.getGraphics();
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }
  
    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;       
    }
    public static void destroy() {
       if (created) return;
       window.dispose();
    }
    public static void setTitle(String title){
        window.setTitle(title);
    }
    
    public static void addInputListener(Input inputListener){
        window.add(inputListener);
    }
}
