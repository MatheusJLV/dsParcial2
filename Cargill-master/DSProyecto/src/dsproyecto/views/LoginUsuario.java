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
    HBox Hb1 = new HBox(20);
    HBox Hb2 = new HBox(20);
    HBox Hb3 = new HBox(20);
    
    TextField t1=new TextField(user);
    TextField t2=new PasswordField();
    Button Conect= new Button("Ingresar");
    Label l_user= new Label("User:");
    Label l_pass= new Label("Password:");
    VBox Vb1 = new VBox(10);
    Pane root=new Pane();
    String Cargo="";

    public LoginUsuario(Connection cone) {
        con = cone;
        Build();
        
        Conect.setOnAction(e->{
            try ( ResultSet rs = con.createStatement().executeQuery("select cargo from usuario where username=\""+t1.getText()+"\" and userpa"+"ssw"+"ord=\""+t2.getText()+"\";");
){
                
                
                while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                
                    if(rs.getString(1)!=null){Cargo=rs.getString(1);}
                

                }
                System.err.println(Cargo);
                   
                    switch (Cargo){
                            case "Vendedor":
                                
                                VendedorView b=new VendedorView(con);
                                Scene s= new Scene(b.getRoot(),800,400);
                                Cargar_Scene(s);
                                break;
                            case "Gerente":
                                
                                MenuGerente b2=new MenuGerente(con);
                                Scene s2= new Scene(b2.getRoot(),800,400);
                                Cargar_Scene(s2);
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
    
    
    
    
    public void Build(){
        Hb1.getChildren().addAll(l_user,t1);
        Hb2.getChildren().addAll(l_pass,t2);
        l_user.setMinSize(70, 20);
        l_pass.setMinSize(70, 20);
        
        
        Vb1.getChildren().addAll(Hb1,Hb2,Conect);
        Vb1.setPadding(new Insets(10, 10, 10, 10));
        Vb1.setAlignment(Pos.CENTER);
        Vb1.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        Vb1.setMaxSize(400, 200);
        Vb1.setMinSize(400, 200);
        t2.setText(pass);
        root.getChildren().add(Vb1);
        
        
    
    
    }
    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
    public void Cargar_Scene(Scene scene)  {
         
         Stage st= (Stage)root.getScene().getWindow();
         
         st.setScene(scene);
     
     
     }
}
