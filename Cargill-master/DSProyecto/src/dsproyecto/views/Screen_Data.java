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
public class Screen_Data {
    Label lbox1=new Label();
    Label lbox2=new Label();
    Label lbox3=new Label();
    Label lbox4=new Label();
    Label lbox5=new Label();
    Label lbox6=new Label();
    Label lbox7=new Label();
    Label lbox8=new Label();
    Label lbox9=new Label();
    Label lbox10=new Label();
    Label lbox11=new Label("");
    TextField tbox1=new TextField();
    TextField tbox2=new TextField();
    TextField tbox3=new TextField();
    TextField tbox4=new TextField();
    TextField tbox5=new TextField();
    TextField tbox6=new TextField();
    TextField tbox7=new TextField();
    TextField tbox8=new TextField();
    TextField tbox9=new TextField();
    TextField tbox10=new TextField();
    Button bt= new Button("Done");
    Pane root = new Pane();
    HBox fbox1=new HBox(10);
    HBox fbox2=new HBox(10);
    HBox fbox3=new HBox(10);
    HBox fbox4=new HBox(10);
    HBox fbox5=new HBox(10);
    VBox containerBox= new VBox(20);
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
    
    public Screen_Data(Connection con,String query,ObservableList rowList, int add0,String Updatequery) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.udatequery=Updatequery;
        this.rowList=rowList;
        
        list1.add(lbox1);list1.add(lbox2);list1.add(lbox3);list1.add(lbox4);list1.add(lbox5);list1.add(lbox6);list1.add(lbox7);list1.add(lbox8);list1.add(lbox9);list1.add(lbox10);
        list2.add(tbox1);list2.add(tbox2);list2.add(tbox3);list2.add(tbox4);list2.add(tbox5);list2.add(tbox6);list2.add(tbox7);list2.add(tbox8);list2.add(tbox9);list2.add(tbox10);
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
                executeQuery("UPDATE articulo SET nombre=\""+tbox2.getText()+"\", descripcion= \""+tbox3.getText()+"\", preciobase="+tbox4.getText()+" WHERE id="+tbox1.getText());
               
                
            } catch (Exception r) {
                System.err.println("Error con datos: "+ r.getMessage());
            }Stage s=((Stage) bt.getScene().getWindow());
            s.close();
            
            
        });
        
    }
    
    public Screen_Data(Connection con,String query,ObservableList rowList, int add0,String Updatequery,boolean b) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.udatequery=Updatequery;
        this.rowList=rowList;
        this.Pedido=b;
        
        list1.add(lbox1);list1.add(lbox2);list1.add(lbox3);list1.add(lbox4);list1.add(lbox5);list1.add(lbox6);list1.add(lbox7);list1.add(lbox8);list1.add(lbox9);list1.add(lbox10);
        list2.add(tbox1);list2.add(tbox2);list2.add(tbox3);list2.add(tbox4);list2.add(tbox5);list2.add(tbox6);list2.add(tbox7);list2.add(tbox8);list2.add(tbox9);list2.add(tbox10);
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
    
    fbox1.getChildren().addAll(lbox1,tbox1,lbox2,tbox2);
    fbox2.getChildren().addAll(lbox3,tbox3,lbox4,tbox4);
    fbox3.getChildren().addAll(lbox5,tbox5,lbox6,tbox6);
    fbox4.getChildren().addAll(lbox7,tbox7,lbox8,tbox8);
    fbox5.getChildren().addAll(lbox9,tbox9,lbox10,tbox10);
    containerBox.getChildren().addAll(fbox1,fbox2,fbox3,fbox4,fbox5,lbox11,bt);
    containerBox.setPadding(new Insets(20, 20, 20, 20));
    containerBox.setAlignment(Pos.CENTER);
    root.getChildren().add(containerBox);
    
    }
    

    public Pane getRoot() {
        return root;
    }
    public void SetValues(){
        
        try (ResultSet rs = con.createStatement().executeQuery(this.query);
                ResultSet rs2 = con.createStatement().executeQuery(this.query+" where "+lbox1.getText()+" = "+rowList.get(0));){
            
            
            
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                String ColumnName= rs.getMetaData().getColumnName(i + 1);
                list1.get(i).setText(ColumnName);
                list2.get(i).setDisable(false);
                
            }
            if(add0==1){
    
                
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
