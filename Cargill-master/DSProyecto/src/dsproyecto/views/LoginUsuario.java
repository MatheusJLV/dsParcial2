/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto.views;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author medin
 */
public class LoginUsuario {
    private static Connection con;
    // Declaramos los datos de conexion de Usuarios
   
    private static final String user="jlmedina";
    private static final String pass="1234";
    HBox hb1 = new HBox(20);
    HBox hb2 = new HBox(20);
    HBox hb3 = new HBox(20);
    
    TextField txt1=new TextField(user);
    TextField txt2=new PasswordField();
    Button Conect= new Button("Ingresar");
    Label iuser= new Label("User:");
    Label ipass= new Label("Password:");
    VBox vb1 = new VBox(10);
    Pane root=new Pane();
    String cargo="";

    public LoginUsuario(Connection cone) {
        con = cone;
        build();
        
        Conect.setOnAction(e->{
            try ( ResultSet rs = con.createStatement().executeQuery("select cargo from usuario where username=\""+txt1.getText()+"\" and userpa"+"ssw"+"ord=\""+txt2.getText()+"\";");
){
                
                
                while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                
                    if(rs.getString(1)!=null){cargo=rs.getString(1);}
                

                }
                System.err.println(cargo);
                   
                    switch (cargo){
                            case "Vendedor":
                                
                                VendedorView b=new VendedorView(con);
                                Scene s= new Scene(b.getRoot(),800,400);
                                cargarScene(s);
                                break;
                            case "Gerente":
                                
                                menuGerente b2=new menuGerente(con);
                                Scene s2= new Scene(b2.getRoot(),800,400);
                                cargarScene(s2);
                                break;
                            case "Jefe":
                                JefeView jv = new JefeView(con);
                                Scene sceneJefe = new Scene(jv.getRoot(),800,400);
                                break;
                            default:
                                System.out.println(" Nada ");
                                break;
                    }
                      
                
            } catch (Exception x) {
                System.err.println(x.toString());
            }
                
            
            
            
        
        });
        
    }
    
    
    
    
    public void build(){
        hb1.getChildren().addAll(iuser,txt1);
        hb2.getChildren().addAll(ipass,txt2);
        iuser.setMinSize(70, 20);
        ipass.setMinSize(70, 20);
        
        
        vb1.getChildren().addAll(hb1,hb2,Conect);
        vb1.setPadding(new Insets(10, 10, 10, 10));
        vb1.setAlignment(Pos.CENTER);
        vb1.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        vb1.setMaxSize(400, 200);
        vb1.setMinSize(400, 200);
        txt1.setText(pass);
        root.getChildren().add(vb1);
        
        
    
    
    }
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
    public void cargarScene(Scene scene)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
}
