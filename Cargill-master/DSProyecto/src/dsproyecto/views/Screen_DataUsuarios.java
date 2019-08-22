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
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author medin
 */
public class Screen_DataUsuarios {
    Label lbl1=new Label();
    Label l2=new Label();
    Label l3=new Label();
    Label l4=new Label();
    Label l5=new Label();
    Label l6=new Label();
    Label l7=new Label();
    Label l8=new Label();
    Label l9=new Label();
            Label l10=new Label();
            Label l11=new Label("");
    TextField txt1=new TextField(),t2=new TextField(),t3=new TextField(),t4=new TextField(),t5=new TextField(),t6=new TextField(),t7=new TextField(),t8=new TextField(),t9=new TextField(),t10=new TextField();
    Button btn1= new Button("Done");
    Pane root = new Pane();
    HBox fila1=new HBox(10);
    HBox fila2=new HBox(10);
    HBox fila3=new HBox(10);
    HBox fila4=new HBox(10);
    HBox fila6=new HBox(10);
    VBox contenedor1= new VBox(20);
    ArrayList<Label> list1 =new ArrayList();
    ArrayList<TextField> list2 =new ArrayList();
    ArrayList<String> data =new ArrayList();
    ArrayList<String> subdata1 =new ArrayList();
    ArrayList<String> subdata2 =new ArrayList();
    
    Connection con;
    String query;
    String Udatequery;
    int add0=1;
    ObservableList rowList;
    
    
    boolean Pedido=false;
    
    public Screen_DataUsuarios(Connection con,String query,ObservableList rowList, int add0,String Updatequery) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.Udatequery=Updatequery;
        this.rowList=rowList;
        
        list1.add(lbl1);list1.add(l2);list1.add(l3);list1.add(l4);list1.add(l5);list1.add(l6);list1.add(l7);list1.add(l8);list1.add(l9);list1.add(l10);
        list2.add(txt1);list2.add(t2);list2.add(t3);list2.add(t4);list2.add(t5);list2.add(t6);list2.add(t7);list2.add(t8);list2.add(t9);list2.add(t10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        build();
        setValues();
        
        btn1.setOnAction(e->{
            getData();
            try {
                if(this.add0==1){
                                    executeQuery("UPDATE Usuario SET nombre=\""+t2.getText()+"\", telefono="+t3.getText()+"\", direccion= \""+t4.getText()+"\", mail= \""+t5.getText()+"\", username= \""+t6.getText()+"\", userpassword= \""+t7.getText()+"\", cargo= \""+t8.getText()+" WHERE id="+txt1.getText());

                } else{
                                    con.createStatement().execute("Insert into Usuario values("
                                            +txt1.getText()+",\""+t2.getText()+"\","+t3.getText()+",\""+t4.getText()+"\",\""+t5.getText()+"\", \""
                                            +t6.getText()+"\",\""+t7.getText()+"\",\""+t8.getText()+"\"");

                }

                
            } catch (Exception r) {
                System.err.println("Error con datos: "+ r.getMessage());
            }Stage s=((Stage) btn1.getScene().getWindow());
            s.close();
            
            
        });
        
    }
    //caso especial para pedidos pq requiere diferente formato de valores
    public Screen_DataUsuarios(Connection con,String query,ObservableList rowList, int add0,String Updatequery,boolean b) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.Udatequery=Updatequery;
        this.rowList=rowList;
        this.Pedido=b;
        
        list1.add(lbl1);list1.add(l2);list1.add(l3);list1.add(l4);list1.add(l5);list1.add(l6);list1.add(l7);list1.add(l8);list1.add(l9);list1.add(l10);
        list2.add(txt1);list2.add(t2);list2.add(t3);list2.add(t4);list2.add(t5);list2.add(t6);list2.add(t7);list2.add(t8);list2.add(t9);list2.add(t10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        build();
        setValues();
        
        btn1.setOnAction(e->{
            getData();
            try {
                if(add0==1){
                    String d1=subdata1.get(0);
                    subdata1.remove(d1);
                    subdata1.add(d1);
                    String d2=subdata2.get(0);
                    subdata2.remove(d2);
                    subdata2.add(d2);
                    data.clear();data.addAll(subdata2);data.addAll(subdata1);
                    String str=String.format(Updatequery, data.toArray());
                    
                    executeQuery(str);
                    System.out.println(str);
                }else{
                    String str=String.format(Updatequery,data.toArray());
                    System.out.println(str);
                    executeQuery(str);
                }
                
                
            } catch (Exception r) {
                System.err.println("Error con datos: "+ r.getMessage());
            }Stage s=((Stage) btn1.getScene().getWindow());
            s.close();
            
            
        });
        
    }

    public void getData() {
        data.clear();
         for (TextField t : list2) {
            if(!t.isDisabled()){
                if(t.getText()==null){
                    t.setText("null");
                }if(t.getText().equals("")){
                    t.setText("null");
                }
                data.add(t.getText());}
         }
        if(Pedido){
            int count=0;
            for (String s : data) {
                if(count<=3){subdata1.add(s);}else if(count<=8){subdata2.add(s);} count+=1;
                
            }data.clear();data.addAll(subdata2);data.addAll(subdata1);
       
        
        }
        
        
        
    }
    
    
    
    public void build(){
    
    fila1.getChildren().addAll(lbl1,txt1,l2,t2);
    fila2.getChildren().addAll(l3,t3,l4,t4);
    fila3.getChildren().addAll(l5,t5,l6,t6);
    fila4.getChildren().addAll(l7,t7,l8,t8);
    fila6.getChildren().addAll(l9,t9,l10,t10);
    contenedor1.getChildren().addAll(fila1,fila2,fila3,fila4,fila6,l11,btn1);
    //c1.setMinSize(320, 400);
    contenedor1.setPadding(new Insets(20, 20, 20, 20));
    contenedor1.setAlignment(Pos.CENTER);
    root.getChildren().add(contenedor1);
    //asignar nombres a los labels y tamaños en cada constructoro
    
    
    
    //root.getStylesheets().add("/estilo/estilos.css");
    
    }
    

    public Pane getRoot() {
        return root;
    }
    public void setValues(){
        
        try (ResultSet rs = con.createStatement().executeQuery(this.query);
                ResultSet rs2 = con.createStatement().executeQuery(this.query+" where "+lbl1.getText()+" = "+rowList.get(0));

){
            
            
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                String ColumnName= rs.getMetaData().getColumnName(i + 1);
                list1.get(i).setText(ColumnName);
                list2.get(i).setDisable(false);
                
            }
            if(add0==1){
                //System.out.println(this.query+" where "+lbl1.getText()+" = "+rowList.get(0));
                while (rs2.next()) {
                    //Iterate Row

                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        list2.get(i-1).setText(rs2.getString(i));
                        
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
    
        
        
        
    }
    
      public void executeQuery(String query){
        try (Statement st = con.createStatement();){
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.err.println("Error in SQL code: "+ex.getMessage());
        }
        
    }  
    
        
    
    
    
    
    
    
    
}
