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
public class PermisosEnvios {
           private static final Logger logger = Logger.getLogger(PermisosEnvios.class.getName());

    Connection con;
    Pane root=new Pane();
    HBox head=new HBox(50);
    HBox opcional=new HBox();
    HBox bottom=new HBox(80);
    VBox vbox1=new VBox(4);
    Button nuevo= new Button(" Agregar ");
    Button modificar= new Button("Modificar");
    Button menu= new Button(" Regresar");
    Button busqueda= new Button(" Buscar Repartidor");
    Button busqueda2= new Button(" Buscar Jefe");
    Label label=new Label("Busqueda de Envio:");
    TextField textofield= new TextField();
    String viewquery;
    String addquery;
    String delquery;
    String modquery;
    
    
    private ObservableList<ObservableList> data;

    private TableView tableview;
    
    

    public PermisosEnvios(Connection con) {
        this.con = con;
        
     
        
        tableview = new TableView();
        
        tableview.setMinSize(720, 200);
        tableview.setMaxSize(720, 200);
        head.getChildren().addAll(label,textofield,nuevo);
        head.setAlignment(Pos.CENTER);
        opcional.getChildren().addAll(busqueda,busqueda2);
        opcional.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(modificar,menu);
        bottom.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(head,opcional,tableview,bottom);
        vbox1.setPadding(new Insets(30, 40, 20, 40));
        vbox1.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox1);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        
        
        
        viewquery="select j.nombre as Jefe, r.nombre as Repartidor, e.estado, e.detalles, l.direccion, e.id from envio e, usuario j, usuario r, establecimiento l where e.idJefe=j.id and e.idRepartidor = r.id and e.idEstablecimiento=l.id";
               
        buildData(viewquery);
        busqueda.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();

        });

        
        modificar.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
            if (rowList!=null){
            Screen_DataEnvio  sc=new Screen_DataEnvio(con, "select e.estado, e.id from envio e, usuario j, usuario r, establecimiento l where e.idJefe=j.id and e.idRepartidor = r.id and e.idEstablecimiento=l.id", rowList, 1,modquery);
            showWindow(new Scene(sc.getRoot()));
            }
        });
        
        
        nuevo.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
             
            Screen_Data  sc=new Screen_Data(con,viewquery, rowList, 0,addquery);
            showWindow(new Scene(sc.getRoot()));
             
            
        });
        menu.setOnAction(e->{
            MenuP m= new MenuP(con);
        Cargar_Scene(new Scene(m.getRoot(), 800, 400));
        });
        busqueda.setOnAction(e->  {
             if (textofield.getText().isEmpty()) {buildData(this.viewquery);}
        else {
                
            String nombre=textofield.getText();
            String query = "select j.nombre as Jefe, r.nombre as Repartidor, e.estado, e.detalles, l.direccion from envio e, usuario j, usuario r, establecimiento l where e.idJefe=j.id and e.idRepartidor = r.id and e.idEstablecimiento=l.id and r.nombre like  \"%"+nombre+"%\"";
            
            buildData(query);
            
        }
        });
        busqueda2.setOnAction(e->  {
            if (textofield.getText().isEmpty()) {buildData(this.viewquery);}
        else {
                
            String nombre=textofield.getText();
            String query = "select j.nombre as Jefe, r.nombre as Repartidor, e.estado, e.detalles, l.direccion from envio e, usuario j, usuario r, establecimiento l where e.idJefe=j.id and e.idRepartidor = r.id and e.idEstablecimiento=l.id and j.nombre like  \"%"+nombre+"%\"";
            
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
        if (textofield.getText().isEmpty()) {buildData(this.viewquery);}
        else {
                
            String nombre=textofield.getText();
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
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
            }


            while (rs.next()) {
                
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    
                    if(rs.getString(i)==null){row.add("null");}else{row.add(rs.getString(i));}
                    
                }
            
                data.add(row);

            }

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
    
    public void Cargar_Scene(Scene scene)  {
         
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
