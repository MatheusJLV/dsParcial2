/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication_basedatos;

import com.sun.org.apache.bcel.internal.generic.AASTORE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

/**
 *
 * @author medin
 */
public class Buscar_ {
    Connection con;
    Pane root=new Pane();
    HBox head=new HBox(50);
    HBox opcional=new HBox();
    HBox bottom=new HBox(150);
    VBox Vb=new VBox(4);
    Button New= new Button(" Agregar ");
    Button Del= new Button("  Borrar ");
    Button Mod= new Button("Modificar");
    Button Menu= new Button(" Regresar");
    Button Buscar= new Button("  Buscar ");
    Label label=new Label("Numero de ¿?");
    TextField Tfield= new TextField();
    String Viewquery;
    String Addquery;
    String Delquery;
    String Modquery;
    
    
    
    private ObservableList<ObservableList> data;

    private TableView tableview;
    
    

    public Buscar_(Connection con) {
        this.con = con;
        
        
        
        
        tableview = new TableView();
        
        tableview.setMinSize(720, 280);
        tableview.setMaxSize(720, 280);
        head.getChildren().addAll(label,Tfield,Buscar,New);
        head.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(Mod,Del,Menu);
        bottom.setAlignment(Pos.CENTER);
        Vb.getChildren().addAll(head,tableview,bottom);
        Vb.setPadding(new Insets(30, 40, 20, 40));
        Vb.setAlignment(Pos.CENTER);
        root.getChildren().add(Vb);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        
        
        
        Viewquery="";
        Addquery="";
        Delquery="";
        Modquery="";
        buildData(Viewquery);
        
        Del.setOnAction(e->{
           
                ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
                if (rowList!=null){
                String Cod= rowList.get(0).toString();
            
                String q=Delquery+Cod;
                ExecuteQuery(q);
            } 
        });
        
        Mod.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
             if (rowList!=null){
            Screen_Data  sc=new Screen_Data(con, "select * from *****", rowList, 1,Modquery);
            ShowWindow(new Scene(sc.getRoot()));
             }
        });
        
        
        New.setOnAction(e->{
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
            
            Screen_Data  sc=new Screen_Data(con, "select * from ****", rowList, 0,Addquery);
            ShowWindow(new Scene(sc.getRoot()));
            
            
        });
        Menu.setOnAction(e->{
            Menu m= new Menu(con);
            Cargar_Scene(new Scene(m.getRoot(), 800, 400),"");
        });
        Buscar.setOnAction(e->  {
             BuscarEvent();
        });
        
    }

    public Pane getRoot() {
        return root;
    }

   
    public void Cargar_Scene(Scene scene,String titulo)  {
         
         
         Stage st= (Stage)root.getScene().getWindow();
         st.setTitle("Cargill DataBase-"+titulo);
         st.setScene(scene);
     
     
     }
    private void ShowWindow(Scene scene) {
    
    Stage st= new Stage();
    st.setScene(scene);
    
    st.initModality(Modality.WINDOW_MODAL);
    st.initOwner(root.getScene().getWindow() );
    st.show();
}
    public void BuscarEvent(){
        if (Tfield.getText().isEmpty()) {buildData(this.Viewquery);}
        else if (isNumeric(Tfield.getText())) {
                
            String numC=Tfield.getText();
            String query =Viewquery+" where ***.id_***= " +numC;
            buildData(query);
            
        }
        else  {ErrorAlert(" El Campo Tiene Letras o Simbolos ");}
    
            
            }
    
    public void ErrorAlert(String x){
        Alert alert = new Alert(AlertType.ERROR);
 
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
            resultado = false;
        }

        return resultado;
    }
    
    public void buildData(String query) {
        
        data = FXCollections.observableArrayList();
        try {
            
            //SQL FOR SELECTING ALL OF CUSTOMER
            
            //ResultSet
            ResultSet rs = con.createStatement().executeQuery(query);

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
                //System.out.println("Column [" + i + "] ");
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
                //System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error on Building Data: "+e.getMessage());
        }
    }
    public void ExecuteQuery(String query){
        try {
            con.createStatement().executeUpdate(query);
            buildData(this.Viewquery);
        } catch (SQLException ex) {
            System.out.println("Error in SQL code: "+ex.getMessage());
        }
        
    }
    
}