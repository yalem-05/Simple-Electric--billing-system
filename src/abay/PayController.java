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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class PayController implements Initializable {

    @FXML
    private ImageView imgg;

    @FXML
    private TextField amount;

    @FXML
    private Button back;

    @FXML
    private Button calculate;

    @FXML
    private TextField consumed;

    @FXML
    private AnchorPane left;

    @FXML
    private Button pay;

    @FXML
    private Button print;

    @FXML
    private AnchorPane right;

    @FXML
    private AnchorPane upper;

    @FXML
    private Label user;

    @FXML
    private TextField customerid;

    @FXML
    private TextArea txtarea;

    @FXML
    private ComboBox<?> payselect;

    @FXML
    private Button paym;

    @FXML
    private ImageView ccc;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    Alert alert;

    private String[] positionList = {"telebirr", "CBE"};

    public void addCustomerList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        payselect.setItems(listData);
    }

    public void calculate() {
        double birr = 5;
        if (consumed.getText().isEmpty()
                || customerid.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("It is empity  please fill gap");
            alert.showAndWait();
        } else {
            double num1 = Double.parseDouble(consumed.getText());
            double num2 = num1 * birr;
            amount.setText(Double.toString(num2));
        }
    }

    public void payWithTele() throws SQLException {
        if ((String) payselect.getSelectionModel().getSelectedItem() == "telebirr") {

            connect = FXMLDocumentController.connectDb();

            if (consumed.getText().isEmpty()
                    || customerid.getText().isEmpty()
                    || amount.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();
            } else {
                String check = "SELECT customerid FROM customerdata WHERE customerid=' "
                        + customerid.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    try {
                      

                      
                        /*if (result.next()) {
                            getData.name = result.getString("firstname");
                        }*/

                        getData.consumed = consumed.getText();
                        getData.amount = amount.getText();
                        getData.selected = "telebir";
                        getData.customerid = customerid.getText();

                        //   TelebirrController.send(getData.consumed, getData.amount,getData.selected,   getData.customerid);
                        paym.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("telebirr.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.setTitle("wellcome");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer id not found");
                    alert.showAndWait();
                }
            }

        } else if (payselect.getSelectionModel().getSelectedItem() == "CBE") {
            connect = FXMLDocumentController.connectDb();

            if (consumed.getText().isEmpty()
                    || customerid.getText().isEmpty()
                    || amount.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();
            } else {
                String check = "SELECT customerid FROM customerdata WHERE customerid=' "
                        + customerid.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    try {
                        getData.consumed = consumed.getText();
                        getData.amount = amount.getText();
                        getData.selected = "CBE";
                        getData.customerid = customerid.getText();;
                        paym.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("cbe.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.setTitle("wellcome");
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer id not found");
                    alert.showAndWait();
                }

            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select your payment method");
            alert.showAndWait();
        }
    }

    public void display1() {
        txtarea.setText("\n"
                + "--------------------------------------------------"
                + "\n" + "--------------------------------------------------"
                + "\n"
                + "        You id is: " + customerid.getText() + " " + "\n"
                + "        You consumed of:  " + consumed.getText() + "KB" + "\n"
                + "        you total paid amount is:  " + amount.getText() + " ETB"
        );
    }

    /* public void payment() throws IOException {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO payment"
                + "(consumed ,customerid, balance,date )"
                + "values(? ,?,?, ?)";
        connect = FXMLDocumentController.connectDb();
        try {

            if (consumed.getText().isEmpty()
                    || customerid.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();
            } else {
                String check = "SELECT customerid FROM customerdata WHERE customerid=' "
                        + customerid.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, consumed.getText());
                    prepare.setString(2, customerid.getText());
                    prepare.setString(3, amount.getText());
                    prepare.setString(4, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    display1();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer id not found");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    public void back() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                back.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayUsername() {

        //  String check = "SELECT firstname FROM customerdata WHERE username= getData.user";
        //   + customerid.getText() + "'";
        // statement = connect.createStatement();
        //  result = statement.executeQuery(check);
        // String nam=   result.getString("firstname");
        user.setText(getData.user);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        displayUsername();
        addCustomerList();

    }

}
