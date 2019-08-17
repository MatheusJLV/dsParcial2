/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto.views;

import java.sql.Connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author medin
 */
public class MenuP {
    Connection con;
    VBox Col1=new VBox(20),Col2=new VBox(20),Col3=new VBox();
    
    
    Button Usuarios=new Button("Usuario"),Cotizacion=new Button("Cotizacion"),Venta=new Button("Venta"),Productos=new Button("Producto");
    HBox Hb1= new HBox();
    Pane root=new Pane();
    
    public Pane getRoot() {
        return root;
    }
     public MenuP(Connection con) {
        this.con = con;
    
        Usuarios.setMinSize(150, 50);
        Cotizacion.setMinSize(150, 50);
        Venta.setMinSize(150, 50);
        Productos.setMinSize(150, 50);
        
        Col1.getChildren().addAll(Cotizacion,Usuarios);
        Col2.getChildren().addAll(Venta,Productos);
        Col1.setAlignment(Pos.CENTER);
        Col2.setAlignment(Pos.CENTER);
        Col3.setAlignment(Pos.CENTER);
        Col3.setPadding(new Insets(30));
        Col1.setPadding(new Insets(0, 0, 0, 20));
        
        Hb1.getChildren().addAll(Col1,Col2,Col3);
        Col1.setMinSize(200, 400);
        Col2.setMinSize(200, 400);
        root.getChildren().add(Hb1);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        Usuarios.setOnAction(e->{
            BuscarUsuarios b=new BuscarUsuarios(con);
            Cargar_Scene(new Scene(b.getRoot(),800,400),"Usuarios");
        });
        
        
        
    }
    public void Cargar_Scene(Scene scene,String titulo)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
}

