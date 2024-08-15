/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yalem
 */
public class PrintController implements Initializable {

     @FXML
    private Button back;
        
    @FXML
    private TextArea textarea;
    
    @FXML
    private ImageView printimg;
    
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button printinfo;
    /**
     * Initializes the controller class.
     */
    
    
   public void disp(){
            String name=getData.name;
       String customerid=getData.customerid;
    
    
       
       
       
       Double payamount =getData.amou;
       textarea.setText("       \n\nDear cudtomer"+"\n "
                        +"      Your id is"+customerid+"\n"
                        +"      you succesfuly paid"+" "+payamount+"\n"
                        +"      this is your reset \n"
                        +"      please print this page\n"
                         +"     Thank you "
       
                        );
      textarea.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
       
       
   }
    
   
   public void print(){
       textarea.textProperty().unbind();
       PrinterJob job=PrinterJob.createPrinterJob();
       if(job!=null){
         if (job != null) {
      System.out.println(job.jobStatusProperty().asString());

      boolean printed = job.printPage(pane);
      if (printed) {
        job.endJob();
      } else {
        System.out.println("Printing failed.");
      }
    } else {
      System.out.println("Could not create a printer job.");
    }
  }
          
     
   }
   
    
   
     public void backkk() throws IOException{
            back.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("pay.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      disp();  // TODO
    }    
    
}
