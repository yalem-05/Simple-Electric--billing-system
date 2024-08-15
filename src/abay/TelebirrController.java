/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class TelebirrController implements Initializable {
   @FXML
    private TextField teleamount;

    @FXML
    private AnchorPane teleanchor;

    @FXML
    private TextField teleid;

    @FXML
    private ImageView teleimg1;

    @FXML
    private ImageView teleimg2;

    @FXML
    private ImageView teleimg3;

    @FXML
    private Button telepay;
    
    @FXML
    private Label l1;

    @FXML
    private Label l11;

    @FXML
    private Label l111;
 

    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     * 
     */
       
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    
     Alert alert;
     
   
     
     
    public void telepay() throws SQLException, IOException{
        
        if(teleid.getText().isEmpty()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("It is empity  please fill gap");
                    alert.showAndWait();
        }
        else{
              String check = "SELECT paymentid FROM telebirr WHERE paymentid='"
                        + teleid.getText() +"'";
              connect = FXMLDocumentController.connectDb();

                statement = connect.createStatement();
                result = statement.executeQuery(check);
                if(result.next()){
             
                    String paych = "SELECT balance FROM telebirr WHERE paymentid='"    + teleid.getText() +"'";
                    connect = FXMLDocumentController.connectDb();
                      statement = connect.createStatement();
                result = statement.executeQuery(paych);
                 if(result.next()){
                   String res1=result.getString("balance"); 
                   double d=Double.parseDouble(res1);
                   System.out.println(d);
                   String res2=teleamount.getText();
                    double d2=Double.parseDouble(res2);
                 
                    if(d<d2){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You have no sufficient money to pay this amount"+"\n"+"please recharge your account and try again!");
                    alert.showAndWait();
                    }else{
                     double res=d-d2;
                     getData.amou=d2;
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("you succesfully paid:"+d2+"Birr"+"\n"+"Your current balance is:"+res+"Birr"+"Thank you for choosing as!");
                    alert.showAndWait();
                    String update="UPDATE telebirr SET balance = '"
                + res + "'";
                    prepare = connect.prepareStatement(update);
                    prepare.executeUpdate();
                    
                    
                       database();
                    
                      //  getData.consumed = consumed.getText();
                       // getData.amount = amount.getText();
                        getData.selected = "telebir";
                    
                back.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("print.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
                    
                    
                    
                    }
                 }
                
               
                  // System.out.println(d);
                }else{
                    
                 alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your telebir id is not found");
                    alert.showAndWait();   
                }
          
        }
    }
    
    
   public void back() throws IOException{
            back.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("pay.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
    }
   public void database() throws SQLException{
           Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
       String consumedunit=getData.consumed;
       String customerid=getData.customerid;
       String selected=getData.selected;
       String payamount =getData.amount;
      String sql = "INSERT INTO payinfo"
                + "(customerid ,consumedunit,selected ,payamount, date)"
                + "values(? ,?, ?, ?,?)";
       connect = FXMLDocumentController.connectDb();
         prepare = connect.prepareStatement(sql);
                    prepare.setString(1,customerid );
                    prepare.setString(2, consumedunit);
                    prepare.setString(3, selected);
                     prepare.setString(4, payamount);
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.executeUpdate();
       
       
   } 
   
   
     public void displayUsername() {
        teleamount.setText(getData.amount);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayUsername();
     
    }    
    
}
