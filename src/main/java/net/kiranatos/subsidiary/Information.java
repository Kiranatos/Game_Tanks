package net.kiranatos.subsidiary;

/**
 * Какой функционал ещё можно додать:
 * - Создать ещё принтовский метод, выводящий весь стек вызова метода
 * - (До)Запись/Вывод в файл
 * - Быстрое считывание из консоли
 */

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Information {    
    
    private static int def = 2;
    
    /**
     * Обёртка над System.out.print, которая добавляет к выводимой строке
     * информацию про класс и метод, где она была вызвана
     * @param str 
     */
    public static void print(String str){
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String sClass = ste[def].getClassName(); 
        String sMetod = ste[def].getMethodName();                                                                                     //String sMetod = Thread.currentThread().getStackTrace()[2].getMethodName(); 
                                                                                    //for (int i=0; i<ste.length; i++) System.out.println(ste[i].getClassName() + " ");
        System.out.print("[" + sClass + " : " + sMetod + "] " + str);        
                                                                                    //Thread.currentThread().getName().getClass()
                                                                                    //Thread.currentThread().getStackTrace()[2].getClassName()
        def = 2;
    }    
    /**
     * Обёртка над System.out.println, которая добавляет к выводимой строке
     * информацию про класс и метод, где она была вызвана
     * @param str 
     */
    public static void println(String str){
        def = 3;
        print(str+"\n");        
    }    
    /**
     * Обёртка над System.out.print, которая выводит весь стек и добавляет к выводимым строкам
     * информацию про класс и метод, где они были вызвана
     * @param str 
     */
    public static void printAllStack(){
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for(int i = 0; i< ste.length; i++)
            System.out.println("[" + ste[i].getClassName() + " : " + ste[i].getMethodName() + "]");
    }
    
    
    
    
    /**
     * Метод для копирования строки в системный буфер обмена.
     * нужно просмотреть и изучить след. доки:
     * http://docs.oracle.com/javase/7/docs/api/java/awt/datatransfer/Clipboard.html
     * http://docs.oracle.com/javase/1.5.0/docs/api/java/awt/Toolkit.html
     * @param str 
     */
    public static void setClipboard(String str) {                
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        System.out.println("Содержимое буфера обмена:" + str);
    }    
    /**
     * Метод для получения строки из системного буфера обмена.     
     */
    public static String getClipboard() {                        
        Transferable transfer = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        //StringSelection ss = (StringSelection)transfer;
        String data = null;
        try {
            if ( transfer.isDataFlavorSupported(DataFlavor.stringFlavor) )    {
                Object o = transfer.getTransferData( DataFlavor.stringFlavor );
                data = (String)transfer.getTransferData( DataFlavor.stringFlavor );
            }
        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(Information.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Information.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        System.out.println("Содержимое буфера обмена:" + data);
        
        return data;
    }
    
    
    
    /**
     * Усыпить поток на @milliseconds
     */
    public static void sleepMilliseconds(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) { Logger.getLogger(Information.class.getName()).log(Level.SEVERE, null, ex); }
    }
    /**
     * Усыпить поток на @seconds
     */
    public static void sleepSeconds(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) { Logger.getLogger(Information.class.getName()).log(Level.SEVERE, null, ex); }
    }
    /**
     * Усыпить поток на @minute
     */
    public static void sleepMinute(int minute){
        try {
            Thread.sleep(minute*60*1000);
        } catch (InterruptedException ex) { Logger.getLogger(Information.class.getName()).log(Level.SEVERE, null, ex); }
    }
}
