/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package ifood;


import com.sun.javafx.css.StyleManager;
import java.awt.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author José Victor
 */
public class Ifood extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        String stylesheet = getClass().getResource("atlantaFXStyleSheet.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(stylesheet);
        
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
