package net.kiranatos.fx;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.kiranatos.MyGame;
import net.kiranatos.subsidiary.Information;
import net.kiranatos.subsidiary.Keeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameWindow extends Application implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(GameWindow.class);
    private Stage stage;
        
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        log.info("Starting Tanks");

        String fxmlFile = "/fxml/MainWindow.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 1100, 700);
        //scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Tanks");
        stage.setScene(scene);
        stage.show();    
        
        Information.println("\tIn the end of method start in GameWindow");
        Keeper.setFlagOfStartGameWindow(true);
    }    
    
    @Override
    public void run() {
        launch();
        if (stage==null) { 
            Information.println(" Stage Closed!");
            MyGame.setRunning(false);
        }
    }
    
    public boolean setTitle(String title) {
        if ( stage == null ) return false;
        stage.setTitle(title);
        return true;
    }
    
    public void destroy () {
        stage.close();
    }
}