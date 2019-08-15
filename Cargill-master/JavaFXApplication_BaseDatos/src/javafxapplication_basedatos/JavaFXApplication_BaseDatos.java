/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication_basedatos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 *
 * @author medin
 */
public class JavaFXApplication_BaseDatos extends Application {
    
    Pane root=new Pane();
    Scene scene ;
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        root= (new Login()).getRoot();
        
        scene = new Scene(root);
        
        primaryStage.setTitle("Cargill DataBase");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/Recursos/PicsArt_07-15-05.00.03.png").toString()));
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }

    
    // Funcion que va conectarse a mi bd de mysql
    
    
                                             
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
