/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication_basedatos;


import java.io.InputStream;
import java.sql.Connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author medin
 */
public class Menu {
    Connection con;
    VBox Col1=new VBox(20),Col2=new VBox(20),Col3=new VBox();
    ImageView imageview;
    Image image;
    
    
    Button PNatural=new Button("PNatural"),Factura=new Button("Factura"),Pedido=new Button("Pedido"),Producto=new Button("Producto"),Button1=new Button("Pensar q hacer aqui"),Juridico=new Button("Juridico");
    HBox Hb1= new HBox();
    Pane root=new Pane();
    //nuevas pantallas
    Buscar_ B_;
    Buscar_PNatural B_PNatural;
    Buscar_Juridico B_Juridico;
    Buscar_Factura B_Factura;
    Buscar_DetallePed B_detxped;
    Buscar_Producto B_Producto;

    public Menu(Connection con) {
        this.con = con;
    
        
        
      
        image=new Image("/Recursos/Cargill_logo.png");
        imageview=new ImageView(image);
        imageview.setFitHeight(200);
        imageview.setFitWidth(300);
        PNatural.setMinSize(150, 50);
        Factura.setMinSize(150, 50);
        Pedido.setMinSize(150, 50);
        Producto.setMinSize(150, 50);
        Button1.setMinSize(150, 50);
        Juridico.setMinSize(150, 50);
        
        Col1.getChildren().addAll(Button1,Factura,PNatural);
        Col2.getChildren().addAll(Pedido,Producto,Juridico);
        Col3.getChildren().add(imageview);
        Col1.setAlignment(Pos.CENTER);
        Col2.setAlignment(Pos.CENTER);
        Col3.setAlignment(Pos.CENTER);
        Col3.setPadding(new Insets(30));
        Col1.setPadding(new Insets(0, 0, 0, 20));
        
        Hb1.getChildren().addAll(Col1,Col2,Col3);
        Col1.setMinSize(200, 400);
        Col2.setMinSize(200, 400);
        root.getChildren().add(Hb1);
        root.setMinSize(800, 400);
        root.setMaxSize(800, 400);
        root.setStyle("-fx-border-style: solid inside;"
        + "-fx-border-width: 3;" + "-fx-border-insets: 20;"
        + "-fx-border-radius: 5;" + "-fx-border-color: grey;");
        
        
        
        Producto.setOnAction(e->{
            B_Producto=new Buscar_Producto(con);
            Cargar_Scene(new Scene(B_Producto.getRoot(),800,400),"Productos");
        });
        
        Pedido.setOnAction(e->{
            B_detxped=new Buscar_DetallePed(con);
            Cargar_Scene(new Scene(B_detxped.getRoot(),800,400),"Pedidos");
        
        });
        Factura.setOnAction(e->{
            B_Factura=new  Buscar_Factura(con);
            Cargar_Scene(new Scene(B_Factura.getRoot(), 800, 400),"Facturas");
        });
        Juridico.setOnAction(e->{
            B_Juridico=new Buscar_Juridico(con);
            Cargar_Scene(new Scene(B_Juridico.getRoot(), 800, 400),"Clientes Juridicos");
        });
        PNatural.setOnAction(e->{
            B_PNatural=new Buscar_PNatural(con);
            Cargar_Scene(new Scene(B_PNatural.getRoot(), 800, 400),"Clientes Naturales");
        });
        //por cambiar
        Button1.setDisable(true);
        Button1.setOnAction(e->{
            B_=new Buscar_(con);
            Cargar_Scene(new Scene(B_.getRoot(), 800, 400),"");
        });
        
        
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public void Cargar_Scene(Scene scene,String titulo)  {
         
         
         Stage st= (Stage)root.getScene().getWindow();
         st.setTitle("Cargill DataBase-"+titulo);
         st.setScene(scene);
     
     
     }
    
    public void ErrorAlert(String x){
        Alert alert = new Alert(Alert.AlertType.ERROR);
 
        alert.setTitle("Error alert");
        alert.setHeaderText("");
        alert.setContentText(x);
 
        alert.showAndWait();
    }
    
}
