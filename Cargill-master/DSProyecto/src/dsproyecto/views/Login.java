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
//    Menu Menu;
    
    private static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="root";
    private static final String url="jdbc:mysql://localhost:3306/disenosoft ?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&allowMultiQueries=TRUE";
    //192.168.56.102
    //remover permisos de modificar o borrar datos de la base de datos
    //"jdbc:mysql://localhost:3306/proyecto"
    //+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"
    
    //url="jdbc:mysql://localhost:3306/proyecto"
    HBox Hb1 = new HBox(20);
    HBox Hb2 = new HBox(20);
    HBox Hb3 = new HBox(20);
    Label l1= new Label("Estado");
    TextField T_Estado=new TextField("Server: 192.168.56.102");
    
    
    TextField t1=new TextField(user);
    TextField t2=new PasswordField();
    Button Conect= new Button("Conectar");
    Label l_user= new Label("User:");
    Label l_pass= new Label("Password:");
    VBox Vb1 = new VBox(10);
    Pane root=new Pane();
    
    
    
    public Login() {
        Hb1.getChildren().addAll(l_user,t1);
        Hb2.getChildren().addAll(l_pass,t2);
        Hb3.getChildren().addAll(l1,Conect);
        l_user.setMinSize(70, 20);
        l_pass.setMinSize(70, 20);
        l1.setMinSize(70, 20);
        
        Vb1.getChildren().addAll(Hb1,Hb2,Hb3,T_Estado);
        Vb1.setPadding(new Insets(10, 10, 10, 10));
        Vb1.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        Vb1.setMaxSize(400, 200);
        Vb1.setMinSize(400, 200);
        
        
        t2.setText(pass);
        root.getChildren().add(Vb1);
        
        Conect.setOnAction(e->{
        conectar();
        });
        
        
    }
    public void conectar()  {
        // Reseteamos a null la conexion a la bd
        con=null;
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (Connection) DriverManager.getConnection(url, t1.getText(), t2.getText());
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){
               T_Estado.setText("Conexion establecida");
               Cargar_Menu();
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            T_Estado.setText("Error de conexion: " + e.getMessage());
        }
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    
     public void Cargar_Menu()  {
         MenuP m=new MenuP(con);
         LoginUsuario l=new LoginUsuario(con);
         Stage st= (Stage)root.getScene().getWindow();
         st.setScene(new Scene(l.getRoot(), 400, 400));
         st.setTitle("User Login");
     
     
     }

}
