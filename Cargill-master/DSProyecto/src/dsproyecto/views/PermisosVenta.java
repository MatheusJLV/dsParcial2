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
public class PermisosVenta {
    private static final Logger logger = Logger.getLogger(PermisosVenta.class.getName());

    Connection con;
    Pane root=new Pane();
    HBox head=new HBox(50);
    HBox opcional=new HBox();
    HBox bottom=new HBox(80);
    VBox vertBox=new VBox(4);
    Button menu= new Button(" Regresar");
    Button buscar= new Button(" Buscar Vendedor");
    Button buscar2= new Button(" Buscar Cliente");
    Label label=new Label("Busqueda de Venta:");
    TextField Tfield= new TextField();

    String viewquery;
    String delquery;
    
    
    private ObservableList<ObservableList> data;

    private TableView tableview;
    
    

    public PermisosVenta(Connection con) {
        this.con = con;
        
     
        
        tableview = new TableView();
        
        tableview.setMinSize(720, 200);
        tableview.setMaxSize(720, 200);
        head.getChildren().addAll(label,Tfield);
        head.setAlignment(Pos.CENTER);
        opcional.getChildren().addAll(buscar,buscar2);
        opcional.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(menu);
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
        
        
        
        
        
        viewquery="select u.nombre as vendedor, c.nombre as cliente, v.fecha, sum(d.cantidad*d.precio) as total from usuario u,venta v ,detalleventa d,cliente c where v.idCliente= c.id and v.idVendedor=u.id and d.idventa=v.id group by v.id";
        //                                   %s para int     \"%s\"   para varchar
        delquery="DELETE FROM cliente\n" +"WHERE id=";
        
        
        buildData(viewquery);
        buscar.setOnAction(e->{
            if (Tfield.getText().isEmpty()) {tableview.getColumns().clear();}
        else {
                
            String nombre=Tfield.getText();
            String query = "select u.nombre as vendedor, c.nombre as cliente, v.fecha, sum(d.cantidad*d.precio) as total from usuario u,venta v ,detalleventa d,cliente c where v.idCliente= c.id and v.idVendedor=u.id and d.idventa=v.id group by v.id and u.nombre like  \"%"+nombre+"%\"";
            
            buildData(query);
            
        }
        });


        menu.setOnAction(e->{
            MenuP m= new MenuP(con);
        cargarScene(new Scene(m.getRoot(), 800, 400));
        });

        buscar2.setOnAction(e->  {
            if (Tfield.getText().isEmpty()) {tableview.getColumns().clear();}
        else {
                
            String nombre=Tfield.getText();
            String query = "select u.nombre as vendedor, c.nombre as cliente, v.fecha, sum(d.cantidad*d.precio) as total from usuario u,venta v ,detalleventa d,cliente c where v.idCliente= c.id and v.idVendedor=u.id and d.idventa=v.id group by v.id and c.nombre like  \"%"+nombre+"%\"";
            
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
   
    public void BuscarEvent(){
        if (Tfield.getText().isEmpty()) {tableview.getColumns().clear();}
        else {
                
            String nombre=Tfield.getText();
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
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if(rs.getString(i)==null){row.add("null");}else{row.add(rs.getString(i));}
                    
                }
               
                data.add(row);

            }

            
            tableview.setItems(data);
        } catch (Exception e) {
            
logger.log(Level.SEVERE, e.getMessage(), e);        }
    }
    public void executeQuery(String query){
        try (Statement st = con.createStatement();){
            st.execute(query);
            buildData(this.viewquery);
        } catch (SQLException ex) {
logger.log(Level.SEVERE, ex.getMessage(), ex);        }
        
    }
    
    public void cargarScene(Scene scene)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
    
}
