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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.logging.*;

/**
 *
 * @author MatheusJLV
 */
public class PermisosInventario {
           private static final Logger logger = Logger.getLogger(PermisosInventario.class.getName());

Connection con;
    Pane root=new Pane();
    HBox head=new HBox(50);
    HBox opcional=new HBox();
    HBox bottom=new HBox(80);
    VBox vertBox=new VBox(4);
    Button botonn= new Button(" Agregar ");
    Button botond= new Button("  Borrar ");
    Button botonm= new Button("Modificar");
    Button botonmenu= new Button(" Regresar");
    Button botonbusc1= new Button(" Buscar Nombre");
    Button Buscar2= new Button(" Buscar Descripcion");
    Button botonbusc2= new Button(" Buscar Lugar");
    Label label=new Label("Busqueda de Producto:");
    Label label2=new Label("Codigo de Producto:");
    TextField t1field= new TextField();
    TextField t2field= new TextField();
    String viewquery;
    String addquery;
    String delquery;
    String modquery;
    
    
    private ObservableList<ObservableList> data;

    private TableView tableview;
    
    

    public PermisosInventario(Connection con) {
        this.con = con;
        
     
        
        tableview = new TableView();
        
        tableview.setMinSize(720, 200);
        tableview.setMaxSize(720, 200);
        head.getChildren().addAll(label,t1field,botonn);
        head.setAlignment(Pos.CENTER);
        opcional.getChildren().addAll(botonbusc1,Buscar2);
        opcional.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(botonm,botond,botonmenu);
        bottom.setAlignment(Pos.CENTER);
        vertBox.getChildren().addAll(head,opcional,tableview,bottom);
        vertBox.setPadding(new Insets(30, 40, 20, 40));
        vertBox.setAlignment(Pos.CENTER);
        root.getChildren().add(vertBox);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        
        
        
        viewquery="select * from Articulo ";
       
        
        buildData(viewquery);
       
        botond.setOnAction(e->{
           
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
            if (rowList!=null){
                String Cod= rowList.get(0).toString();
            
                String q=delquery+Cod;
                executeQuery(q);
            }
            
            
        });
        
        botonm.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
            if (rowList!=null){
            Screen_DataArticulos  sc=new Screen_DataArticulos(con, viewquery, rowList, 1,modquery);
            showWindow(new Scene(sc.getRoot()));
            }
        });
        
        
        botonn.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
             
            Screen_Data  sc=new Screen_Data(con,viewquery, rowList, 0,addquery);
            showWindow(new Scene(sc.getRoot()));
             
            
        });
        botonmenu.setOnAction(e->{
            MenuP m= new MenuP(con);
        cargarScene(new Scene(m.getRoot(), 800, 400));
        });
        botonbusc1.setOnAction(e->  {
             buscarEvent();
        });
        Buscar2.setOnAction(e->  {
            if (t1field.getText().isEmpty()) {buildData(this.viewquery);}
        else {
                
            String desc=t1field.getText();
            String query = "select * from Articulo where descripcion like  \"%"+desc+"%\"";
            
            buildData(query);
            
        }
       });
        
    }
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
   
    public void buscarEvent(){
        if (t1field.getText().isEmpty()) {buildData(this.viewquery);}
        else {
                
            String nombre=t1field.getText();
            String query = "select * from Articulo where nombre like  \"%"+nombre+"%\"";
            
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

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
                                 logger.log(Level.SEVERE, excepcion.getMessage(), excepcion);

            resultado = false;
        }

        return resultado;
    }
    
    public void buildData(String query) {
        
        data = FXCollections.observableArrayList();
        try (ResultSet rs = con.createStatement().executeQuery(query)){
            
            //SQL FOR SELECTING ALL OF CUSTOMER
            
            //ResultSet

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            tableview.getColumns().clear();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
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
    
    public void cargarScene(Scene scene)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
    private void showWindow(Scene scene) {
    
    Stage st= new Stage();
    st.setScene(scene);
    
    st.initModality(Modality.WINDOW_MODAL);
    st.initOwner(root.getScene().getWindow() );
    st.show();
}   
}
