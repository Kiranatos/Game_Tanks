package net.kiranatos.subsidiary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestHelp {
    
    private static WritableImage writableImage;
    
    public static void showImage(WritableImage writableImage) {
        TestHelp.writableImage = writableImage;
        Thread t = new Thread(new TestHelp().new A());
        t.start();
    }
    
    private class A extends Application implements Runnable{
        @Override
        public void start(Stage primaryStage) throws Exception {              
            PixelWriter pixelWriter = writableImage.getPixelWriter();
            
            ImageView imageView = new ImageView();
            
            imageView.setImage(writableImage);
            StackPane root = new StackPane();
            root.getChildren().add(imageView);
            Scene scene = new Scene(root, writableImage.getWidth() , writableImage.getHeight());
            primaryStage.setTitle("Show Image");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        
        
        @Override
        public void run() {
            launch("");
        }
    }
}


