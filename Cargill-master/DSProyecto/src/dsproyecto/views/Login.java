/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.geometry.Insets;
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
public class Login {

    private static Connection con;
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String USER="root";
    private static final String PASS="root";
    private static final String URL="jdbc:mysql://localhost:3306/disenosoft ?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&allowMultiQueries=TRUE";
   
    HBox Hb1 = new HBox(20);
    HBox Hb2 = new HBox(20);
    HBox Hb3 = new HBox(20);
    Label l1= new Label("Estado");
    TextField tEstado=new TextField("Server: 192.168.56.102");
    
    
    TextField txt1=new TextField(USER);
    TextField txt2=new PasswordField();
    Button connect= new Button("Conectar");
    Label iuser= new Label("User:");
    Label ipass= new Label("Password:");
    VBox vBox1 = new VBox(10);
    Pane root=new Pane();
    
    
    
    public Login() {
        Hb1.getChildren().addAll(iuser,txt1);
        Hb2.getChildren().addAll(ipass,txt2);
        Hb3.getChildren().addAll(l1,connect);
        iuser.setMinSize(70, 20);
        ipass.setMinSize(70, 20);
        l1.setMinSize(70, 20);
        
        vBox1.getChildren().addAll(Hb1,Hb2,Hb3,tEstado);
        vBox1.setPadding(new Insets(10, 10, 10, 10));
        vBox1.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        vBox1.setMaxSize(400, 200);
        vBox1.setMinSize(400, 200);
        
        
        txt2.setText(PASS);
        root.getChildren().add(vBox1);
        
        connect.setOnAction(e->{
        conectar();
        });
        
        
    }
    public  void conectar()  {
        con=null;
        try{
            Class.forName(DRIVER);
           
            con= DriverManager.getConnection(URL, txt1.getText(), txt2.getText());
          
            if (con!=null){
               tEstado.setText("Conexion establecida");
               LoginUsuario l=new LoginUsuario(con);
                 Stage st= (Stage)root.getScene().getWindow();
                 st.setScene(new Scene(l.getRoot(), 400, 400));
                 st.setTitle("User Login");
            }
        }
        catch (ClassNotFoundException | SQLException e){
            tEstado.setText("Error de conexion: " + e.getMessage());
        }
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    
    

}
