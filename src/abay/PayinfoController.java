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
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class PayinfoController implements Initializable {
    @FXML
    private TableColumn<infoData, String> amount;

    @FXML
    private TableColumn<infoData, String> date;

    @FXML
    private Button home;

    @FXML
    private TableColumn<infoData, String> id;

    @FXML
    private TableView<infoData> info_tableView;

    @FXML
    private AnchorPane left;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<infoData, String> method;

    @FXML
    private AnchorPane right;

    @FXML
    private TableColumn<infoData, String> unit;

    @FXML
    private Label user;
    /**
     * Initializes the controller class.
     */
    
     private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    
     public ObservableList<infoData> addinfoListData() {

        ObservableList<infoData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `payinfo`";

        connect = FXMLDocumentController.connectDb();

        try {
                     prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            infoData employeeD;

            while (result.next()) {
               
                listData.add(new infoData(
                                            result.getString("customerid"),
                                            result.getString("consumedunit"),
                                            result.getString("selected"),
                                            result.getString("payamount"),
                                            result.getDate("date")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       // info_tableView.setItems(listData);
        return listData;
    }
     
     
      private ObservableList<infoData> addinfoList;

    public void addEmployeeShowListData() {
        addinfoList = addinfoListData();

        id.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        unit.setCellValueFactory(new PropertyValueFactory<>("consumedunit"));
        method.setCellValueFactory(new PropertyValueFactory<>("selected"));
        amount.setCellValueFactory(new PropertyValueFactory<>("payamount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        info_tableView.setItems(addinfoList);

    }
    
    
       public void home() throws IOException {
        home.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
  public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
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
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       addEmployeeShowListData();
    }    
    
}
