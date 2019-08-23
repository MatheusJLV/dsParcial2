/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto.views;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.logging.*;
/**
 *
 * @author MatheusJLV
 */
public class OtorgarPermisos {
       private static final Logger logger = Logger.getLogger(OtorgarPermisos.class.getName());

     Connection con;
    Pane root=new Pane();
    HBox head=new HBox(50);
    HBox bottom2=new HBox();
    HBox bottom=new HBox(150);
    VBox vertical=new VBox(4);
    Button enlistar= new Button(" Enlistar Todos ");
    Button inventario= new Button("  Inventario ");
    Button ventas= new Button(" Ventas");
    Button envios= new Button("  envios ");
    Button personal= new Button(" personal");
    Button regresar= new Button(" Regresar");
    Button buscar= new Button("  Buscar ");
    Label label=new Label("Nombre del Usuario: ");
    TextField lblfield= new TextField();
    Label label2=new Label("Codigo del Usuario: ");
    TextField lblfield2= new TextField();
    Label label3=new Label("Modificar permisos");
    String viewquery;
    String addquery;
    String delquery;
    String modquery;
    
    
    private ObservableList<ObservableList> data;

    private TableView tableview;
    
    

    public OtorgarPermisos(Connection con) {
        this.con = con;
        
     
        
        tableview = new TableView();        
        tableview.setMinSize(720, 250);
        tableview.setMaxSize(720, 250);
        
        head.getChildren().addAll(label,lblfield,buscar,enlistar);
        head.setAlignment(Pos.CENTER);
        
        bottom.getChildren().addAll(label2,lblfield2,regresar);
        bottom.setAlignment(Pos.CENTER);
        
        bottom2.getChildren().addAll(label3,inventario,ventas,envios,personal);
        bottom2.setAlignment(Pos.CENTER);
        
        vertical.getChildren().addAll(head,tableview,bottom,bottom2);
        vertical.setPadding(new Insets(30, 40, 20, 40));
        vertical.setAlignment(Pos.CENTER);
        root.getChildren().add(vertical);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        

        
        viewquery="select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso";

        
        inventario.setOnAction(e->{
           if(isNumeric(lblfield2.getText())){
    
            String codigo=lblfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"inventario\" and u.id ="+codigo;
            
            buildData(query); 
            try (ResultSet rs = con.createStatement().executeQuery(query);){
                            //ResultSet
                
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=1 and idUsuario ="+lblfield2.getText());
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(1,"+lblfield2.getText()+")");
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            } catch(Exception except){
         logger.log(Level.SEVERE, except.getMessage(), except);
            }

           } 
            
            
            
        });
        
        ventas.setOnAction(e->{
           if(isNumeric(lblfield2.getText())){
    
            String codigo=lblfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"Ventas\" and u.id ="+codigo;
            
            buildData(query); 
            try (ResultSet rs = con.createStatement().executeQuery(query);){
                
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=2 and idUsuario ="+lblfield2.getText());
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(2,"+lblfield2.getText()+")");
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            } catch(Exception except){
         logger.log(Level.SEVERE, except.getMessage(), except);
            }

           } 
            
            
            
        });
        
        envios.setOnAction(e->{
           if(isNumeric(lblfield2.getText())){
    
            String codigo=lblfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"Envios\" and u.id ="+codigo;
            
            buildData(query); 
            try (ResultSet rs = con.createStatement().executeQuery(query);
){
                            //ResultSet
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=3 and idUsuario ="+lblfield2.getText());
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(3,"+lblfield2.getText()+")");
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            } catch(Exception except){
         logger.log(Level.SEVERE, except.getMessage(), except);
            }

           } 
            
            
            
        });
        
        personal.setOnAction(e->{
           if(isNumeric(lblfield2.getText())){
    
            String codigo=lblfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"personal\" and u.id ="+codigo;
            
            buildData(query); 
            try {

                ResultSet rs = con.createStatement().executeQuery(query);
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=4 and idUsuario ="+lblfield2.getText());
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(4,"+lblfield2.getText()+")");
                    buildData(this.viewquery);
                } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            } catch(Exception except){
         logger.log(Level.SEVERE, except.getMessage(), except);
            }

           } 
            
            
            
        });
        
        enlistar.setOnAction(e->{
            buildData(viewquery);

            
        });
        regresar.setOnAction(e->{
            MenuP m= new MenuP(con);
        cargarScene(new Scene(m.getRoot(), 800, 400),"Menu");
        });
        buscar.setOnAction(e->  {
             buscarEvent();
        });
        
    }
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
   
    public void buscarEvent(){
        if (lblfield.getText().isEmpty()) {buildData(this.viewquery);}
        else  {
                
            String nombre=lblfield.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and u.nombre like \"%"+nombre+"%\"";
            
            buildData(query);
            
        }
    
            
            }
    public void errorAlert(String x){
        Alert alert = new Alert(Alert.AlertType.ERROR);
 
        alert.setTitle("Error alert");
        alert.setHeaderText("");
        alert.setContentText(x);
 
        alert.showAndWait();
    }
    public boolean isNumeric(String cadena) {

        boolean resultado=false;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
         logger.log(Level.SEVERE, excepcion.getMessage(), excepcion);
        }

        return resultado;
    }
    
    public void buildData(String query) {
        
        data = FXCollections.observableArrayList();
        try (ResultSet rs = con.createStatement().executeQuery(query);){
            
         
            tableview.getColumns().clear();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    if(rs.getString(i)==null){row.add("null");}else{row.add(rs.getString(i));}
                    
                }
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
         logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    public void executeQuery(String query){
        try (Statement st = con.createStatement();){
            st.execute(query);
            buildData(this.viewquery);
        } catch (SQLException ex) {
         logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }
    
    public void cargarScene(Scene scene,String titulo)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
    
}
