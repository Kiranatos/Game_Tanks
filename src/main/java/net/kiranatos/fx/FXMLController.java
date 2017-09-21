package net.kiranatos.fx;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import net.kiranatos.IO.Input;
import net.kiranatos.subsidiary.Information;
import net.kiranatos.subsidiary.Keeper;
import net.kiranatos.subsidiary.TankException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FXMLController implements Initializable {
    
    private static final Logger log = LoggerFactory.getLogger(FXMLController.class);

    @FXML private Canvas backgroundGameCanvas;
    @FXML private Canvas actionLayoutGameCanvas;
    @FXML private Canvas woodGameCanvas;
    @FXML private TextField textFieldMyMassage;
    @FXML private TextArea textAreaChat;
    //@FXML private Label messageLabel;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {   
        Keeper.getKeeper().setBackGroundGraphicsContext(backgroundGameCanvas.getGraphicsContext2D());     //GraphicsContext gc = canvasGame.getGraphicsContext2D();
        Keeper.getKeeper().setActionGraphicsContext(actionLayoutGameCanvas.getGraphicsContext2D());
        Keeper.getKeeper().setWoodGraphicsContext(woodGameCanvas.getGraphicsContext2D());
        backgroundGameCanvas.toBack();
        actionLayoutGameCanvas.toFront();
        woodGameCanvas.toFront();
        
        /*
        try {  
            Keeper.getKeeper().getGraphicsContext().drawImage( 
                    Keeper.getKeeper().getTextureAtlasImage(), 0, 0, 800, 600
            );
        } catch (TankException ex) { }
        */            
        
        woodGameCanvas.setFocusTraversable(true); //устанавливает фокус на канвасе
        //действия над канвасом:
        woodGameCanvas.setOnMouseEntered((a) -> System.out.println("hi"));
        woodGameCanvas.setOnMousePressed((a) -> System.out.println("focus"));
        woodGameCanvas.setOnKeyReleased( Keeper.getKeeper().getInputCanvas() );
        woodGameCanvas.setOnKeyPressed( Keeper.getKeeper().getInputCanvas() );
        woodGameCanvas.setOnKeyTyped( Keeper.getKeeper().getInputCanvas() );
        //любые действия мышей приводят к фокусировке на канвасе
        woodGameCanvas.addEventFilter(MouseEvent.ANY, (e) -> woodGameCanvas.requestFocus());
        
        Information.println("\tIn the end of method initialize in FXMLController class");
    }
    
    /*
    public void canvasOnInputMethodTextChanger(ActionEvent e) {
        System.out.println("canvasOnInputMethodTextChanger");        
        System.out.println( e.getEventType() );        
    }
    public void canvasOnKeyPressed(ActionEvent e) {
        System.out.println("canvasOnKeyPressed");        
    }
    public void canvasOnKeyReleased(ActionEvent e) {
        System.out.println("canvasOnKeyReleased");        
    }
    public void canvasOnKeyTyped (ActionEvent e) {
        System.out.println("canvasOnKeyTyped");
    }*/

}
