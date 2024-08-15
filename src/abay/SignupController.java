/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class SignupController implements Initializable {

    
    @FXML
    private ComboBox<?> combo;
 

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private AnchorPane left_form;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField cpassword;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private TextField phone;

    @FXML
    private AnchorPane right_form;
    
    
    @FXML
    private TextField priviledge;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    /* public Connection connectDb(){
       
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     */
    public Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    Alert alert;

    public void signup() throws SQLException {
        if (firstname.getText().isEmpty() || password.getText().isEmpty() || cpassword.getText().isEmpty()
                || lastname.getText().isEmpty() || username.getText().isEmpty() || email.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("It is empity  please fill gap");
            alert.showAndWait();
        } else if (password.getText() != cpassword.getText()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("password not match");
            alert.showAndWait();
        } else {
            String checkusername = "SELECT *FROM log WHERE username='" + username.getText() + "'";
             connect=connectDb();
            try {
                // prepare = connect.prepareStatement(sql);
                statement = connect.createStatement();
                result = statement.executeQuery(checkusername);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(username.getText() + "Already exist");
                    alert.showAndWait();
                } else {
                   
                   String insert="INSERT INTO log" +"(username, firstname, lastname, password, priviledge, email. date, phone)"+ "values(? ,?, ?,?,?, ?,?, ?)";
                   prepare=connect.prepareStatement(insert);
                   prepare.setString(1, username.getText());
                   prepare.setString(2, firstname.getText());
                   prepare.setString(3, lastname.getText());
                   prepare.setString(4, password.getText());
                   prepare.setString(5, priviledge.getText());
                   prepare.setString(6, email.getText());
                   
                  // Date date=new Date();
                 //  java.sql.Date sqlDate=new java.sql.Date(date.getTime());
               //  prepare.setString(7, date.getDateTime());
                   prepare.setString(8, phone.getText());
                   
                   prepare.executeUpdate();
                   
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("SUCCESS Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successsfully registered");
                    alert.showAndWait();
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    private String[] selectlist={"Admin", "Customer"};
    
    public void select(){
        List<String> list= new ArrayList<>();
        
        for(String data: selectlist){
            list.add(data);
        }
        
        ObservableList olist;
        olist = FXCollections.observableArrayList(list);
        combo.setItems(olist);
        
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        select();
    }

}
