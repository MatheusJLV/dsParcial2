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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author MatheusJLV
 */
public class MenuGerente {
    Connection con;
    VBox Col1=new VBox(20),Col2=new VBox(20),Col3=new VBox(),Col4=new VBox();
    
    
    Button Otorgar =new Button("Otorgar Permisos");
    Button ManageInventario=new Button("Manejar Inventario");
    Button ManageVentas=new Button("Manejar Ventas");
    Button ManageEnvios=new Button("Manejar Envios");
    Button ManagePersonal=new Button("Manejar Personal");
    Button ManageClientes=new Button("Manejar  Clientes");
    HBox Hb1= new HBox();
    Pane root=new Pane();
    
    public Pane getRoot() {
        return root;
    }
     public MenuGerente(Connection con) {
        this.con = con;
    
        Otorgar.setMinSize(150, 50);
        ManageInventario.setMinSize(150, 50);
        ManageVentas.setMinSize(150, 50);
        ManageEnvios.setMinSize(150, 50);
        ManagePersonal.setMinSize(150, 50);
        ManageClientes.setMinSize(150, 50);
        
        Col1.getChildren().addAll(Otorgar,ManageInventario);
        Col2.getChildren().addAll(ManageVentas,ManageEnvios);
        Col3.getChildren().addAll(ManagePersonal,ManageClientes);
        Col1.setAlignment(Pos.CENTER);
        Col2.setAlignment(Pos.CENTER);
        Col3.setAlignment(Pos.CENTER);
        Col4.setAlignment(Pos.CENTER);
        Col4.setPadding(new Insets(30));
        Col1.setPadding(new Insets(0, 0, 0, 20));
        
        Hb1.getChildren().addAll(Col1,Col2,Col3, Col4);
        Col1.setMinSize(200, 400);
        Col2.setMinSize(200, 400);
        Col3.setMinSize(200, 400);
        root.getChildren().add(Hb1);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        Otorgar.setOnAction(e->{
            OtorgarPermisos b=new OtorgarPermisos(con);
            Cargar_Scene(new Scene(b.getRoot(),800,400),"Otorgar");
        });
        
        ManageInventario.setOnAction(e->{
            PermisosInventario b=new PermisosInventario(con);
            Cargar_Scene(new Scene(b.getRoot(),1000,400),"PermisosInventario");
        });
        
        ManageVentas.setOnAction(e->{
            PermisosVenta b=new PermisosVenta(con);
            Cargar_Scene(new Scene(b.getRoot(),1000,400),"PermisosVenta");
        });
        
    }
    public void Cargar_Scene(Scene scene,String titulo)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
}
