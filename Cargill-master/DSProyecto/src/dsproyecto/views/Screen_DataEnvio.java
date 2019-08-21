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
public class Screen_DataEnvio {
    Label l1=new Label(),l2=new Label(),l3=new Label(),l4=new Label(),l5=new Label(),l6=new Label(),l7=new Label(),l8=new Label(),l9=new Label(),l10=new Label(),l11=new Label("");
    TextField t1=new TextField(),t2=new TextField(),t3=new TextField(),t4=new TextField(),t5=new TextField(),t6=new TextField(),t7=new TextField(),t8=new TextField(),t9=new TextField(),t10=new TextField();
    Button bt= new Button("Done");
    Pane root = new Pane();
    HBox f1=new HBox(10);
    HBox f2=new HBox(10);
    HBox f3=new HBox(10);
    HBox f4=new HBox(10);
    HBox f5=new HBox(10);
    VBox c1= new VBox(20);
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
    
    public Screen_DataEnvio(Connection con,String query,ObservableList rowList, int add0,String Updatequery) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.Udatequery=Updatequery;
        this.rowList=rowList;
        
        list1.add(l1);list1.add(l2);list1.add(l3);list1.add(l4);list1.add(l5);list1.add(l6);list1.add(l7);list1.add(l8);list1.add(l9);list1.add(l10);
        list2.add(t1);list2.add(t2);list2.add(t3);list2.add(t4);list2.add(t5);list2.add(t6);list2.add(t7);list2.add(t8);list2.add(t9);list2.add(t10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        Build();
        SetValues();
        
        bt.setOnAction(e->{
            getData();
            try {
                ExecuteQuery("UPDATE envio SET estado=\""+t1.getText()+"\" where id="+t2.getText());
                /*if(add0==1){
                    String d1=data.get(0);
                    data.remove(d1);
                    data.add(d1);
                    String str=String.format(Updatequery, data.toArray());
                    System.out.println(str);
                    ExecuteQuery(str);
                }else{
                    String str=String.format(Updatequery,data.toArray());
                    System.out.println(str);
                    ExecuteQuery(str);
                }
                */
                
            } catch (Exception r) {
                System.out.println("Error con datos: "+ r.getMessage());
            }Stage s=((Stage) bt.getScene().getWindow());
            s.close();
            
            
        });
        
    }
    //caso especial para pedidos pq requiere diferente formato de valores
    public Screen_DataEnvio(Connection con,String query,ObservableList rowList, int add0,String Updatequery,boolean b) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.Udatequery=Updatequery;
        this.rowList=rowList;
        this.Pedido=b;
        
        list1.add(l1);list1.add(l2);list1.add(l3);list1.add(l4);list1.add(l5);list1.add(l6);list1.add(l7);list1.add(l8);list1.add(l9);list1.add(l10);
        list2.add(t1);list2.add(t2);list2.add(t3);list2.add(t4);list2.add(t5);list2.add(t6);list2.add(t7);list2.add(t8);list2.add(t9);list2.add(t10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        Build();
        //SetValues();
        
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
                    
                    ExecuteQuery(str);
                    System.out.println(str);
                }else{
                    String str=String.format(Updatequery,data.toArray());
                    System.out.println(str);
                    ExecuteQuery(str);
                }
                
                
            } catch (Exception r) {
                System.out.println("Error con datos: "+ r.getMessage());
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
    
    
    
    public void Build(){
    
    f1.getChildren().addAll(l1,t1,l2,t2);
    f2.getChildren().addAll(l3,t3,l4,t4);
    f3.getChildren().addAll(l5,t5,l6,t6);
    f4.getChildren().addAll(l7,t7,l8,t8);
    f5.getChildren().addAll(l9,t9,l10,t10);
    c1.getChildren().addAll(f1,f2,f3,f4,f5,l11,bt);
    //c1.setMinSize(320, 400);
    c1.setPadding(new Insets(20, 20, 20, 20));
    c1.setAlignment(Pos.CENTER);
    root.getChildren().add(c1);
    //asignar nombres a los labels y tamaños en cada constructoro
    
    
    
    //root.getStylesheets().add("/estilo/estilos.css");
    
    }
    

    public Pane getRoot() {
        return root;
    }
    public void SetValues(){
        
        try (ResultSet rs = con.createStatement().executeQuery(this.query);
             ResultSet rs2 = con.createStatement().executeQuery(this.query+" where "+l1.getText()+" = "+rowList.get(0));

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
            System.out.println("Error: "+e.getMessage());
        }
    
        
        
        
    }
    
      public void ExecuteQuery(String query){
        try (Statement st = con.createStatement();){
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.out.println("Error in SQL code: "+ex.getMessage());
        }
        
    }  
    
        
    
    
    
    
    
    
    
}
