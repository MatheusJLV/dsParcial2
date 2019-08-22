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
public class menuGerente {
    Connection con;
    VBox col1=new VBox(20);
    VBox col2=new VBox(20);
    VBox col3=new VBox();
    VBox col4=new VBox();
    
    
    Button otorgar =new Button("Otorgar Permisos");
    Button manageInventario=new Button("Manejar Inventario");
    Button manageVentas=new Button("Manejar Ventas");
    Button manageEnvios=new Button("Manejar Envios");
    Button managePersonal=new Button("Manejar Personal");
    Button manageClientes=new Button("Manejar  Clientes");
    HBox hb1= new HBox();
    Pane root=new Pane();
    
    public Pane getRoot() {
        return root;
    }
     public menuGerente(Connection con) {
        this.con = con;
    
        otorgar.setMinSize(150, 50);
        manageInventario.setMinSize(150, 50);
        manageVentas.setMinSize(150, 50);
        manageEnvios.setMinSize(150, 50);
        managePersonal.setMinSize(150, 50);
        manageClientes.setMinSize(150, 50);
        
        col1.getChildren().addAll(otorgar,manageInventario);
        col2.getChildren().addAll(manageVentas,manageEnvios);
        col3.getChildren().addAll(managePersonal,manageClientes);
        col1.setAlignment(Pos.CENTER);
        col2.setAlignment(Pos.CENTER);
        col3.setAlignment(Pos.CENTER);
        col4.setAlignment(Pos.CENTER);
        col4.setPadding(new Insets(30));
        col1.setPadding(new Insets(0, 0, 0, 20));
        
        hb1.getChildren().addAll(col1,col2,col3, col4);
        col1.setMinSize(200, 400);
        col2.setMinSize(200, 400);
        col3.setMinSize(200, 400);
        root.getChildren().add(hb1);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        otorgar.setOnAction(e->{
            OtorgarPermisos b=new OtorgarPermisos(con);
            cargarScene(new Scene(b.getRoot(),800,400));
        });
        
        manageInventario.setOnAction(e->{
            PermisosInventario b=new PermisosInventario(con);
            cargarScene(new Scene(b.getRoot(),1000,400));
        });
        
        manageVentas.setOnAction(e->{
            PermisosVenta b=new PermisosVenta(con);
            cargarScene(new Scene(b.getRoot(),1000,400));
        });
        manageEnvios.setOnAction(e->{
            PermisosEnvios b=new PermisosEnvios(con);
            cargarScene(new Scene(b.getRoot(),1000,400));
        });
        managePersonal.setOnAction(e->{
            PermisosUsuarios b=new PermisosUsuarios(con);
            cargarScene(new Scene(b.getRoot(),1000,400));
        });
        
    }
    public void cargarScene(Scene scene)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
}
