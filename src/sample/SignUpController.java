package sample;
import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;


public class SignUpController extends DBConnect {

    @FXML
    private TextField SignUpName;  //variable
    @FXML
    private TextField SignUpUsername;
    @FXML
    private PasswordField SignUpPassword;
    @FXML
    private TextField SignUpEmail;
    @FXML
    private TextField SignUpAddress;
    @FXML
    private Label PassWarning;
    @FXML
    private ChoiceBox<String> Status;

    ObservableList<String> stats = FXCollections.observableArrayList();

    public void initialize(){
        Loadata();
    }

    // fill the choice box
    public void Loadata(){ // fill the choice box
        stats.removeAll(stats);
        String seller = "Seller";
        String customer = "Customer";
        stats.addAll(seller,customer);
        Status.getItems().addAll(stats);
    }

    @FXML
    public void SignUpButtonClicked(ActionEvent event)throws IOException {
        String SignUpStatus = Status.getValue();
        int balance = 0;
        String name = SignUpName.getText(); // enter the name
        String username = SignUpUsername.getText(); // enter the username
        String password = SignUpPassword.getText(); // enter the password
        String email = SignUpEmail.getText(); // enter email
        String address = SignUpAddress.getText(); // enter address
        if (SignUpStatus.equals("Seller")) { // if sign up as seller
            InsertStaff(name,address,email,username, password); // add the data inside the database
        }else if(SignUpStatus.equals("Customer")){// if sign up as seller
            InsertCustomer(name,balance,address,email,username,password);
        }
//->> go to login button
        CancelButtonClicked(event);
    }

    //->> go to login button
    @FXML
    public void CancelButtonClicked(ActionEvent event) throws IOException{
        Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("LoginPage");
        stage.setScene(new Scene(LoginParent));
        stage.show();
    }
}
