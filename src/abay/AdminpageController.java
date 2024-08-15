/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class AdminpageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button see;
    
    @FXML
    private Button logout;
    
    @FXML
    private TableColumn<customerData, String> columnfirstname;

    @FXML
    private TableColumn<customerData, String> columnid;

    @FXML
    private Button add;

    @FXML
    private Button addcustomer;

    @FXML
    private TextField kebele;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<customerData, String> columncity;

    @FXML
    private TableColumn<customerData, String> columnemail;

    @FXML
    private TableColumn<customerData, String> columnimage;

    @FXML
    private TableColumn<customerData, String> columnlastname;

    @FXML
    private TableColumn<customerData, String> columnpassword;

    @FXML
    private TableColumn<customerData, String> columnphone;

    @FXML
    private TextField customerid;

    @FXML
    private TableView<customerData> customertable;

    @FXML
    private Button delete;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private Button home;

    @FXML
    private AnchorPane imgcont;

    @FXML
    private Button importimg;

    @FXML
    private TextField lastname;

    @FXML
    private AnchorPane left;

    @FXML
    private AnchorPane mainform;

    @FXML
    private ImageView myimage;

    @FXML
    private TextField phone;

    @FXML
    private AnchorPane right;

    @FXML
    private TextField search;

    @FXML
    private AnchorPane table;

    @FXML
    private AnchorPane tablecover;

    @FXML
    private Button update;

    @FXML
    private Label user;

    @FXML
    private TextField password;
    /*
  @FXML
    void addCustomerAdd(ActionEvent event) {

    }
  
    @FXML
    void addCustomerInsertImage(MouseEvent event) {

    }

    @FXML
    void addCustomerSelect(MouseEvent event) {

    }

    @FXML
    void customorReset(ActionEvent event) {

    }
     */

 /* public static Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private Image image;

    public void addCustomerAdd() {
        String sql = "INSERT INTO customerdata"
                + "(customerid ,firstname, lastname ,email ,phone, kebele, image, password)"
                + "values(? ,?, ?, ?, ?, ?, ?, ?)";
        connect = FXMLDocumentController.connectDb();

        try {
            Alert alert;

            if (customerid.getText().isEmpty()
                    || firstname.getText().isEmpty()
                    || lastname.getText().isEmpty()
                    || email.getText().isEmpty()
                    || phone.getText().isEmpty()
                    || kebele.getText().isEmpty()
                    || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill Unfiled space");
                alert.showAndWait();
            } else {
                String check = "SELECT customerid FROM customerdata WHERE customerid=' "
                        + customerid.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("customer id:" + customerid.getText() + " " + "Already exist ");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, customerid.getText());
                    prepare.setString(2, firstname.getText());
                    prepare.setString(3, lastname.getText());
                    prepare.setString(4, email.getText());
                    prepare.setString(5, phone.getText());
                    prepare.setString(6, kebele.getText());
                    prepare.setString(7, password.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);
                    prepare.setString(8, password.getText());
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succes Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer added succesfully ");
                    alert.showAndWait();

                    addCustomerShowListData();
                    customorReset();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCustomerUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE customerdata SET firstName = '"
                + firstname.getText() + "', lastName = '"
                + lastname.getText() + "', email = '"
                + email.getText() + "', phone = '"
                // + addEmployee_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"

                + phone.getText() + "', kebele = '"
                + kebele.getText() + "', image = '"
                + uri + "', password = '"
                + password.getText() + "' WHERE customerid ='"
                + customerid.getText() + "'";

        connect = FXMLDocumentController.connectDb();

        try {
            Alert alert;
            if (customerid.getText().isEmpty()
                    || firstname.getText().isEmpty()
                    || lastname.getText().isEmpty()
                    || email.getText().isEmpty()
                    || phone.getText().isEmpty()
                    || kebele.getText().isEmpty()
                    || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + customerid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    String checkData = "SELECT * FROM customerdata WHERE customerid = '"
                            + customerid.getText() + "'";

                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();

                    String updateInfo = "UPDATE customerdata SET firstname = '"
                            + firstname.getText() + "', lastname = '"
                            + lastname.getText() + "', email = '"
                            + email.getText() + "', phone = '"
                            + phone.getText() + "', kebele = '"
                            + kebele.getText() + "', password = '"
                            + password.getText() + "' WHERE customerid = '"
                            
                            + customerid.getText() + "'";
                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addCustomerShowListData();
                    customorReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void customorReset() {
        customerid.setText("");
        firstname.setText("");
        lastname.setText("");
        email.setText("");
        phone.setText("");
        kebele.setText("");
        myimage.setImage(null);
        getData.path = "";
        password.setText("");
    }

    public void addCustomerDelete() {

        String sql = "DELETE FROM customerdata WHERE customerid = '"
                + customerid.getText() + "'";

        connect = FXMLDocumentController.connectDb();

        try {

            Alert alert;
            if (customerid.getText().isEmpty()
                    || firstname.getText().isEmpty()
                    || lastname.getText().isEmpty()
                    || email.getText().isEmpty()
                    || phone.getText().isEmpty()
                    || kebele.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("It is empity  please fill gap");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + customerid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    String deleteInfo = "DELETE FROM customerdata WHERE customerid = '"
                            + customerid.getText() + "'";

                    prepare = connect.prepareStatement(deleteInfo);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addCustomerShowListData();
                    customorReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addCustomerInsertImage() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(mainform.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 134, 102, false, true);

            myimage.setImage(image);
        }
    }

    public ObservableList<customerData> addCustomerListData() {
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT *FROM customerdata";

        connect = FXMLDocumentController.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            customerData customerC;

            while (result.next()) {
                customerC = new customerData(result.getInt("customerid"),
                                             result.getString("firstname"),
                                             result.getString("lastname"),
                                             result.getString("email"),
                                             result.getString("phone"),
                                             result.getString("kebele"),
                                             result.getString("image"));
                                             result.getString("password");
                listData.add(customerC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<customerData> addCustomerList;

    public void addCustomerShowListData() {
        addCustomerList = addCustomerListData();
        columnid.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        columnfirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        columnlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        columnemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columncity.setCellValueFactory(new PropertyValueFactory<>("kebele"));
        columnimage.setCellValueFactory(new PropertyValueFactory<>("image"));
      // columnpassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        customertable.setItems(addCustomerList);

    }

    public void addCustomerSelect() {
        customerData customerC = customertable.getSelectionModel().getSelectedItem();
        int num = customertable.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        customerid.setText(String.valueOf(customerC.getCustomerid()));
        firstname.setText(customerC.getFirstname());
        lastname.setText(customerC.getLastname());
        email.setText(customerC.getEmail());
        phone.setText(customerC.getPhone());
        kebele.setText(customerC.getKebele());

        String url = "file:" + customerC.getImage();
        Image image = new Image(url, 134, 102, false, true);

        myimage.setImage(image);
        password.setText(customerC.getPassword());
    }

    public void displayUsername() {
        user.setText(getData.user);
    }
    
      public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
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

             



               // stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

      public void paaa(){
          try{
             see.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("payinfo.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
          }catch(Exception e){
              e.printStackTrace();
          }
      }
    /*  public void addEmployeeSearch() {

        FilteredList<infoData> filter = new FilteredList<>(addCustomerList, e -> true);

        search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getEmployeeId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPhoneNum().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPosition().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<infoData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(customertable.comparatorProperty());
        customertable.setItems(sortList);
    }*/
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addCustomerShowListData();
        displayUsername();

    }

}
