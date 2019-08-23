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
public class Screen_DataArticulos {
           private static final Logger logger = Logger.getLogger(Screen_DataArticulos.class.getName());

    Label label1=new Label();
    Label label2=new Label();
    Label label3=new Label();
    Label label4=new Label();
    Label label5=new Label();
    Label label6=new Label();
    Label label7=new Label();
    Label label8=new Label();
            Label label9=new Label();
    Label label10=new Label();
    Label label11=new Label("");
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
    Button btn= new Button("Done");
    Pane root = new Pane();
    HBox fila1=new HBox(10);
    HBox fila2=new HBox(10);
    HBox fila3=new HBox(10);
    HBox fila4=new HBox(10);
    HBox fila5=new HBox(10);
    VBox columna= new VBox(20);
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
    
    public Screen_DataArticulos(Connection con,String query,ObservableList rowList, int add0,String Updatequery) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.Udatequery=Updatequery;
        this.rowList=rowList;
        
        list1.add(label1);list1.add(label2);list1.add(label3);list1.add(label4);list1.add(label5);list1.add(label6);list1.add(label7);list1.add(label8);list1.add(label9);list1.add(label10);
        list2.add(tbox1);list2.add(tbox2);list2.add(tbox3);list2.add(tbox4);list2.add(tbox5);list2.add(tbox6);list2.add(tbox7);list2.add(tbox8);list2.add(tbox9);list2.add(tbox10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        build();
        setValues();
        
        btn.setOnAction(e->{
            getData();
            try {
                executeQuery("UPDATE articulo SET nombre=\""+tbox2.getText()+"\", descripcion= \""+tbox3.getText()+"\", preciobase="+tbox4.getText()+" WHERE id="+tbox1.getText());

                
            } catch (Exception r) {
logger.log(Level.SEVERE, r.getMessage(), r);
            }Stage s=((Stage) btn.getScene().getWindow());
            s.close();
            
            
        });
        
    }
    //caso especial para pedidos pq requiere diferente formato de valores
    public Screen_DataArticulos(Connection con,String query,ObservableList rowList, int add0,String Updatequery,boolean b) {
        this.con=con;
        this.query=query;
        this.add0=add0;
        this.Udatequery=Updatequery;
        this.rowList=rowList;
        this.Pedido=b;
        
        list1.add(label1);list1.add(label2);list1.add(label3);list1.add(label4);list1.add(label5);list1.add(label6);list1.add(label7);list1.add(label8);list1.add(label9);list1.add(label10);
        list2.add(tbox1);list2.add(tbox2);list2.add(tbox3);list2.add(tbox4);list2.add(tbox5);list2.add(tbox6);list2.add(tbox7);list2.add(tbox8);list2.add(tbox9);list2.add(tbox10);
        for (Label l : list1) {
            l.setMinSize(70, 30);
            
        }
        for (TextField t : list2) {
            t.setMinSize(70, 30);
            t.setDisable(true);
        }
        build();
        setValues();
        
        btn.setOnAction(e->{
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
            }Stage s=((Stage) btn.getScene().getWindow());
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
    
    fila1.getChildren().addAll(label1,tbox1,label2,tbox2);
    fila2.getChildren().addAll(label3,tbox3,label4,tbox4);
    fila3.getChildren().addAll(label5,tbox5,label6,tbox6);
    fila4.getChildren().addAll(label7,tbox7,label8,tbox8);
    fila5.getChildren().addAll(label9,tbox9,label10,tbox10);
    columna.getChildren().addAll(fila1,fila2,fila3,fila4,fila5,label11,btn);
    columna.setPadding(new Insets(20, 20, 20, 20));
    columna.setAlignment(Pos.CENTER);
    root.getChildren().add(columna);
    

    
    }
    

    public Pane getRoot() {
        return root;
    }
    public void setValues(){
        
        try (ResultSet rs = con.createStatement().executeQuery(this.query);
                ResultSet rs2 = con.createStatement().executeQuery(this.query+" where "+label1.getText()+" = "+rowList.get(0));
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
