/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto.views;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 *
 * @author MatheusJLV
 */
public class OtorgarPermisos {
     Connection con;
    Pane root=new Pane();
    HBox head=new HBox(50);
    HBox bottom2=new HBox();
    HBox bottom=new HBox(150);
    VBox Vb=new VBox(4);
    Button Enlistar= new Button(" Enlistar Todos ");
    Button inventario= new Button("  Inventario ");
    Button ventas= new Button(" Ventas");
    Button envios= new Button("  envios ");
    Button personal= new Button(" personal");
    Button Menu= new Button(" Regresar");
    Button Buscar= new Button("  Buscar ");
    Label label=new Label("Nombre del Usuario: ");
    TextField Tfield= new TextField();
    Label label2=new Label("Codigo del Usuario: ");
    TextField Tfield2= new TextField();
    Label label3=new Label("Modificar permisos");
    String Viewquery;
    String Addquery;
    String Delquery;
    String Modquery;
    
    
    private ObservableList<ObservableList> data;

    private TableView tableview;
    
    

    public OtorgarPermisos(Connection con) {
        this.con = con;
        
     
        
        tableview = new TableView();        
        tableview.setMinSize(720, 250);
        tableview.setMaxSize(720, 250);
        
        head.getChildren().addAll(label,Tfield,Buscar,Enlistar);
        head.setAlignment(Pos.CENTER);
        
        bottom.getChildren().addAll(label2,Tfield2,Menu);
        bottom.setAlignment(Pos.CENTER);
        
        bottom2.getChildren().addAll(label3,inventario,ventas,envios,personal);
        bottom2.setAlignment(Pos.CENTER);
        
        Vb.getChildren().addAll(head,tableview,bottom,bottom2);
        Vb.setPadding(new Insets(30, 40, 20, 40));
        Vb.setAlignment(Pos.CENTER);
        root.getChildren().add(Vb);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        
        
        
        Viewquery="select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso";
        //                                   %s para int     \"%s\"   para varchar
        Addquery="Insert into usuario values (%s,\"%s\", %s, \"%s\", \"%s\", \"%s\", \"%s\",\"%s\");";
        Delquery="DELETE FROM usuario\n" +"WHERE id=";
        
        
        
        //buildData(Viewquery);
        /*
        Del.setOnAction(e->{
           
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
            if (rowList!=null){
                String Cod= rowList.get(0).toString();
            
                String q=Delquery+Cod;
                ExecuteQuery(q);
            }
            
            
        });
        
        */
        
        inventario.setOnAction(e->{
           if(isNumeric(Tfield2.getText())){
    
            String codigo=Tfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"inventario\" and u.id ="+codigo;
            
            buildData(query); 
            try {
                            //ResultSet
                ResultSet rs = con.createStatement().executeQuery(query);
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=1 and idUsuario ="+Tfield2.getText());
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(1,"+Tfield2.getText()+")");
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            }
            } catch(Exception except){
              System.err.print("error");
            }

           } 
            
            
            
        });
        
        ventas.setOnAction(e->{
           if(isNumeric(Tfield2.getText())){
    
            String codigo=Tfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"Ventas\" and u.id ="+codigo;
            
            buildData(query); 
            try {
                            //ResultSet
                ResultSet rs = con.createStatement().executeQuery(query);
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=2 and idUsuario ="+Tfield2.getText());
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(2,"+Tfield2.getText()+")");
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            }
            } catch(Exception except){
              System.err.print("error");
            }

           } 
            
            
            
        });
        
        envios.setOnAction(e->{
           if(isNumeric(Tfield2.getText())){
    
            String codigo=Tfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"Envios\" and u.id ="+codigo;
            
            buildData(query); 
            try {
                            //ResultSet
                ResultSet rs = con.createStatement().executeQuery(query);
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=3 and idUsuario ="+Tfield2.getText());
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(3,"+Tfield2.getText()+")");
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            }
            } catch(Exception except){
              System.err.print("error");
            }

           } 
            
            
            
        });
        
        personal.setOnAction(e->{
           if(isNumeric(Tfield2.getText())){
    
            String codigo=Tfield2.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and permiso =\"personal\" and u.id ="+codigo;
            
            buildData(query); 
            try {
                            //ResultSet
                ResultSet rs = con.createStatement().executeQuery(query);
                if (rs.next()){
                try {
                    con.createStatement().execute("delete from tablapermiso where idPermiso=4 and idUsuario ="+Tfield2.getText());
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            } else{
                try {
                    con.createStatement().execute("insert into TablaPermiso(idPermiso, idUsuario) values(4,"+Tfield2.getText()+")");
                    buildData(this.Viewquery);
                } catch (SQLException ex) {
                    System.out.println("Error in SQL code: "+ex.getMessage());
                }
            }
            } catch(Exception except){
              System.err.print("error");
            }

           } 
            
            
            
        });
        
        //AQUI ESTABA AGREGAR "NEW"
        Enlistar.setOnAction(e->{
            buildData(Viewquery);
            /** para la creacion en menu flotante
            ObservableList rowList = (ObservableList) tableview.getSelectionModel().getSelectedItem();
             
            Screen_Data  sc=new Screen_Data(con,Viewquery, rowList, 0,Addquery);
            ShowWindow(new Scene(sc.getRoot()));
             */
            
        });
        Menu.setOnAction(e->{
            MenuP m= new MenuP(con);
        Cargar_Scene(new Scene(m.getRoot(), 800, 400),"Menu");
        });
        Buscar.setOnAction(e->  {
             BuscarEvent();
        });
        
    }
//580491
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
   
    public void BuscarEvent(){
        if (Tfield.getText().isEmpty()) {buildData(this.Viewquery);}
        else  {
                
            String nombre=Tfield.getText();
            String query = "select u.id, nombre, cargo, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso and u.nombre like \"%"+nombre+"%\"";
            
            buildData(query);
            
        }
    
            
            }
    public void ErrorAlert(String x){
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
            con.createStatement().execute(query);
            buildData(this.Viewquery);
        } catch (SQLException ex) {
            System.out.println("Error in SQL code: "+ex.getMessage());
        }
        
    }
    
    public void Cargar_Scene(Scene scene,String titulo)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
    private void ShowWindow(Scene scene) {
    
    Stage st= new Stage();
    st.setScene(scene);
    
    st.initModality(Modality.WINDOW_MODAL);
    st.initOwner(root.getScene().getWindow() );
    st.show();
}
}
