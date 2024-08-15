/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import static abay.FXMLDocumentController.connectDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class ForgotController implements Initializable {

    @FXML
    private TextField answer1;

    @FXML
    private PasswordField pass;

    @FXML
    private Button back1;

    @FXML
    private ImageView imm1;

    @FXML
    private AnchorPane pann;

    @FXML
    private ComboBox<?> position1;

    @FXML
    private Button proceed1;

    @FXML
    private ComboBox<?> question1;

    @FXML
    private TextField username1;
    /**
     * Initializes the controller class.
     */

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private String[] positionList = {"Your favorite food", "your nickName", "Your favorite color"};

    public void addquestionlist() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        question1.setItems(listData);
    }

    private String[] positionList1 = {"Admin", "Customer"};

    public void addpositionlist() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList1) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        position1.setItems(listData);
    }
    Alert alert;

    public void proc() {

        if ((String) position1.getSelectionModel().getSelectedItem() == "Customer") {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("You can't forget your passswoord please contact your nearest office\n"
                    + "Thankyou");
            alert.showAndWait();

        } else if ((String) position1.getSelectionModel().getSelectedItem() == "Admin") {

            if (answer1.getText().isEmpty()
                    || username1.getText().isEmpty()
                    || question1.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("You can't forget your passswoord please contact your nearest office\n"
                        + "Thankyou");
                alert.showAndWait();

            } else {
              
                try {
                    String check = "SELECT username FROM log WHERE username='"
                            + username1.getText() + "'";
                    connect = FXMLDocumentController.connectDb();

                    statement = connect.createStatement();
                    result = statement.executeQuery(check);

                    // result = prepare.executeQuery();
                    if (result.next()) {
                        String update = "UPDATE log SET password = '"
                                + pass.getText() + "'";
                        prepare = connect.prepareStatement(update);
                        prepare.executeUpdate();
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("You succesfully changed yor password");
                        alert.showAndWait();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Your username is not correct");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select your position\n"
                    + "Thankyou");
            alert.showAndWait();
        }

    }

    public void back() throws IOException {
        back1.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addquestionlist();
        addpositionlist();
    }

}
