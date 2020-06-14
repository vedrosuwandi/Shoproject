package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends DBConnect implements Initializable {

    @FXML private TextField UsernameTextField;
    @FXML private PasswordField PasswordField;
    @FXML private Label WrongPassword;
    @FXML private Label StatusWarning;
    @FXML private ChoiceBox<String> Status;

    ObservableList<String> stats = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Loadata();
    }

// ->> Sign Up Page
    @FXML
    public void SignUpButtonClicked(ActionEvent event)throws IOException {
        Parent SignUpPageParent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("SignUpPage");
        stage.setScene(new Scene(SignUpPageParent));
        stage.show();
    }

    @FXML
    public void LoginButtonClicked(ActionEvent event)throws IOException {
        String stat = Status.getValue(); // get the value of the choice box
        boolean isEmpty = (Status.getValue() == null); // if nothing is chosen in the choice box
        if(!isEmpty){
            if(stat.equals("Seller")){ // if the user is seller
                if(checkstaff(UsernameTextField.getText() , PasswordField.getText())){ // check whether the username and password are correct or not
                    openProfile(checkaccountS(UsernameTextField.getText() , PasswordField.getText()),event);
                }else{
                    WrongPassword.setText("Wrong Username and Password !!");
                }
            }else if(stat.equals("Customer")){ // if the user is seller
                if(checkcustomer(UsernameTextField.getText() , PasswordField.getText())){
                    openMenu(checkaccountC(UsernameTextField.getText() , PasswordField.getText()),event);
                }else{
                    WrongPassword.setText("Wrong Username and Password !!");
                }
            }
        }else{
            StatusWarning.setText("Please choose one");
        }

    }

// fill the choice box
    public void Loadata(){
        stats.removeAll(stats);
        String seller = "Seller";
        String customer = "Customer";
        stats.addAll(seller,customer);
        Status.getItems().addAll(stats);
    }
// function of go to the menu page
    @FXML
    public void openMenu(User user, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent MenuPage = loader.load();

        // Passing object user to the MainPageController class
        MenuController control = loader.getController();
        control.initdata(user);

        // Gets stage's info and setting it up
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Shoproject");
        stage.show();
    }
    // function of go to the profile page
    @FXML
    public void openProfile(User user , ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Storage.fxml"));
        Parent MenuPage = loader.load();

        // Passing object user to the MainPageController class
        StorageController control = loader.getController();
        control.initdata(user);

        // Gets stage's info and setting it up
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Storage");
        stage.show();
    }
}
