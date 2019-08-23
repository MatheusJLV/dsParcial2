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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Screen_DataEnvio {
               private static final Logger logger = Logger.getLogger(Screen_DataArticulos.class.getName());

    Label lbl1=new Label();
    Label lbl2=new Label();
    Label lbl3=new Label();
    Label lbl4=new Label();
    Label lbl5=new Label();
    Label lbl6=new Label();
    Label lbl7=new Label();
    Label lbl8=new Label();
    Label lbl9=new Label();
    Label lbl10=new Label();
    Label lbl11=new Label("");
    TextField txt1=new TextField();
    TextField txt2=new TextField();
    TextField txt3=new TextField();
    TextField txt4=new TextField();
    TextField txt5=new TextField();
    TextField txt6=new TextField();
    TextField txt7=new TextField();
    TextField txt8=new TextField();
    TextField txt9=new TextField();
    TextField txt10=new TextField();
    Button bt= new Button("Done");
    Pane root = new Pane();
    HBox box1=new HBox(10);
    HBox box2=new HBox(10);
    HBox box3=new HBox(10);
    HBox box4=new HBox(10);
    HBox box5=new HBox(10);
    VBox contenedor= new VBox(20);
    ArrayList<Label> list1 =new ArrayList();
    ArrayList<TextField> list2 =new ArrayList();
    ArrayList<String> data =new ArrayList();
    ArrayList<String> subdata1 =new ArrayList();
    ArrayList<String> subdata2 =new ArrayList();
    
    Connection con;
    String query;
    String udatequery;
    int add0=1;
    ObservableList rowList;
    
    
    boolean Pedido=false;
    
    public Screen_DataEnvio(Connection con,String query,ObservableList rowList, int add0,String Updatequery) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.udatequery=Updatequery;
        this.rowList=rowList;
        
        list1.add(lbl1);list1.add(lbl2);list1.add(lbl3);list1.add(lbl4);list1.add(lbl5);list1.add(lbl6);list1.add(lbl7);list1.add(lbl8);list1.add(lbl9);list1.add(lbl10);
        list2.add(txt1);list2.add(txt2);list2.add(txt3);list2.add(txt4);list2.add(txt5);list2.add(txt6);list2.add(txt7);list2.add(txt8);list2.add(txt9);list2.add(txt10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        build();
        SetValues();
        
        bt.setOnAction(e->{
            getData();
            try {
                executeQuery("UPDATE envio SET estado=\""+txt1.getText()+"\" where id="+txt2.getText());

                
            } catch (Exception r) {
                System.err.println("Error con datos: "+ r.getMessage());
            }Stage s=((Stage) bt.getScene().getWindow());
            s.close();
            
            
        });
        
    }
    //caso especial para pedidos pq requiere diferente formato de valores
    public Screen_DataEnvio(Connection con,String query,ObservableList rowList, int add0,String Updatequery,boolean b) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.udatequery=Updatequery;
        this.rowList=rowList;
        this.Pedido=b;
        
        list1.add(lbl1);list1.add(lbl2);list1.add(lbl3);list1.add(lbl4);list1.add(lbl5);list1.add(lbl6);list1.add(lbl7);list1.add(lbl8);list1.add(lbl9);list1.add(lbl10);
        list2.add(txt1);list2.add(txt2);list2.add(txt3);list2.add(txt4);list2.add(txt5);list2.add(txt6);list2.add(txt7);list2.add(txt8);list2.add(txt9);list2.add(txt10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        build();
        
        bt.setOnAction(e->{
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
logger.log(Level.SEVERE, r.getMessage(), r);
            }Stage s=((Stage) bt.getScene().getWindow());
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
    
    box1.getChildren().addAll(lbl1,txt1,lbl2,txt2);
    box2.getChildren().addAll(lbl3,txt3,lbl4,txt4);
    box3.getChildren().addAll(lbl5,txt5,lbl6,txt6);
    box4.getChildren().addAll(lbl7,txt7,lbl8,txt8);
    box5.getChildren().addAll(lbl9,txt9,lbl10,txt10);
    contenedor.getChildren().addAll(box1,box2,box3,box4,box5,lbl11,bt);
    contenedor.setPadding(new Insets(20, 20, 20, 20));
    contenedor.setAlignment(Pos.CENTER);
    root.getChildren().add(contenedor);
    
    
    
    //root.getStylesheets().add("/estilo/estilos.css");
    
    }
    

    public Pane getRoot() {
        return root;
    }
    public void SetValues(){
        
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
                //System.out.println(this.query+" where "+l1.getText()+" = "+rowList.get(0));
                while (rs2.next()) {
                    //Iterate Row

                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        list2.get(i-1).setText(rs2.getString(i));
                        
                    }
                }
            }
        } catch (Exception e) {
logger.log(Level.SEVERE, e.getMessage(), e);
        }
    
        
        
        
    }
    
      public void executeQuery(String query){
        try (Statement st = con.createStatement();){
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }  
    
        
    
    
    
    
    
    
    
}
