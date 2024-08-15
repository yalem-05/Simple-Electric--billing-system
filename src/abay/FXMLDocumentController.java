/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Yalem FORLOGIN
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ComboBox<?> addcustomerposition;
    
    

    @FXML
    private Button fort;

    @FXML
    private TextField showpassword;

    @FXML
    private AnchorPane left_form;

    @FXML
    private CheckBox checkpassword;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane right_form;

    @FXML
    private TextField username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public static Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] positionList = {"Admin", "Customer"};

    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        addcustomerposition.setItems(listData);
    }

    Alert alert;

    public void login() throws SQLException {

        if ((String) addcustomerposition.getSelectionModel().getSelectedItem() == "Admin") {

            if (username.getText().isEmpty() || password.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();

            } else {
                String sql = "SELECT *FROM log WHERE username=? and password= ?";
                connect = connectDb();
                try {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, username.getText());
                    prepare.setString(2, password.getText());
                    result = prepare.executeQuery();

                    /*  statement = connect.createStatement();
                    result = statement.executeQuery(sql);*/
                    if (result.next()) {
                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle(" Message");
                        alert.setHeaderText(null);
                        alert.setContentText("succesfully login");
                        alert.showAndWait();
                        getData.user = username.getText();

                        loginbtn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.setTitle("wellcome");
                        stage.show();

                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("wrong username or password  please try again");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /* else{
               alert=new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("wrong username or password  please try again"); 
               alert.showAndWait();
                }*/
            }

        } else if ((String) addcustomerposition.getSelectionModel().getSelectedItem() == "Customer") {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();

            } else {
                String sql = "SELECT *FROM customerdata WHERE customerid=? and password= ?";
                connect = connectDb();
                try {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, username.getText());
                    prepare.setString(2, password.getText());
                    result = prepare.executeQuery();

                    /*  statement = connect.createStatement();
                    result = statement.executeQuery(sql);*/
                    if (result.next()) {
                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle(" Message");
                        alert.setHeaderText(null);
                        alert.setContentText("succesfully login");
                        alert.showAndWait();
                        getData.user = username.getText();

                        loginbtn.getScene().getWindow().hide();

                        Parent root = FXMLLoader.load(getClass().getResource("pay.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(true);
                        stage.setTitle("wellcome");
                        stage.show();

                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("wrong username or password  please try again");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select your position");
            alert.showAndWait();
        }
    }

    public void forgott(){
                try{       
                      fort.getScene().getWindow().hide();

                        Parent root = FXMLLoader.load(getClass().getResource("forgot.fxml"));
                        
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.setTitle("wellcome");
                        stage.show();
                }catch(Exception e){
                    e.printStackTrace();
                }
        
    }
    
    
    
    public void showpass() {
        if (checkpassword.isSelected()) {
            showpassword.setText(password.getText());
            showpassword.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(showpassword.getText());
            password.setVisible(true);
            showpassword.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addEmployeePositionList();
    }

}
