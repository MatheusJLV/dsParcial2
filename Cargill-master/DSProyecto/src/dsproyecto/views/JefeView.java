/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto.views;

import java.sql.Connection;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 *
 * @author coloma
 */
class JefeView {
    Connection con;
    VBox root=new VBox();
    HBox arriba = new HBox();
    Pane abajo = new Pane();
    
    //botones
    Button btnsolicitarEnvio=new Button("Solicitar Envio");
    Button btnsolicitarAbastecimiento=new Button("Solicitar Abastecimiento");
    Button crearOrdenDeAbastecimiento=new Button("Crear orden de abastecimiento");
    Button btnPantallaEnvios=new Button("Envios");
    Button btnRegistrodeReporte=new Button("registro de reportes");
    Button btnPermisosExtra=new Button("permisos extras");
    
    public JefeView(Connection con){
        this.con=con;
        
        
        btnsolicitarEnvio.setOnAction(e->{
            seleccionarVenta();
        });
        
        btnsolicitarAbastecimiento.setOnAction(e->{
            seleccionarAbastecimiento();
        });
        
        crearOrdenDeAbastecimiento.setOnAction(e->{
            ingresarDatos();
        });
        
        btnPantallaEnvios.setOnAction(e->{
            datosEnvios();
        });
        
        btnRegistrodeReporte.setOnAction(e->{
            ingresarDatosExtra();
        });
        
        btnPermisosExtra.setOnAction(e->{
            System.out.print("este no se que hace :v");
        });
        
        arriba.getChildren().addAll(btnsolicitarEnvio,btnsolicitarAbastecimiento,crearOrdenDeAbastecimiento,
                btnPantallaEnvios,btnRegistrodeReporte,btnPermisosExtra);
        
        root.getChildren().addAll(arriba,abajo);
    }
    
    

    VBox getRoot() {
        return root;
    }

    private void seleccionarVenta() {
        abajo.getChildren().clear();
        abajo.getChildren().addAll(new Button("aqui deberian salir la lista de ventas o algo asi"));
    }

    private void seleccionarAbastecimiento() {
        abajo.getChildren().clear();
        abajo.getChildren().addAll(new Button("abastecimiento"));
    }

    private void ingresarDatos() {
        abajo.getChildren().clear();
        abajo.getChildren().addAll(new Button("IngresarDatos"));
    }

    private void datosEnvios() {
        abajo.getChildren().clear();
        abajo.getChildren().addAll(new Button("DatosEnvios"));
    }

    private void ingresarDatosExtra() {
        abajo.getChildren().clear();
        abajo.getChildren().addAll(new Button("IngresarDatosExtra"));
    }
}
