package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Profile (Customer) page
 */
public class ProfileController extends DBConnect{
    private User customer;
    @FXML
    private Label ID;
    @FXML
    private Label Name;
    @FXML
    private Label Username;
    @FXML
    private Label Email;
    @FXML
    private Label Address;


    // load the data as the page is opened
    public void initdata(User user){
        this.customer = user;
        ID.setText(Integer.toString(this.customer.getID()));
        Name.setText(this.customer.getName());
        Username.setText(this.customer.getUsername());
        Email.setText(this.customer.getEmail());
        Address.setText(this.customer.getAddress());
    }
    // back to menu
    @FXML
    public void BackToMenu(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent MenuPage = loader.load();

        MenuController control = loader.getController();
        control.initdata(customer);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Shoproject");
        stage.show();
    }
    // ->Change Password
    @FXML
    public void ChangePass(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChangePassword.fxml"));
        Parent MenuPage = loader.load();

        ChangePasswordController control = loader.getController();
        control.initdata(customer);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Change Password");
        stage.show();
    }
    // -> change the Address
    @FXML
    public void ChangeLocate(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Locate.fxml"));
        Parent MenuPage = loader.load();

        LocateController control = loader.getController();
        control.initdata(customer);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Change Location");
        stage.show();
    }
    // top up
    @FXML
    public void TopUpButtonClicked(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TopUp.fxml"));
        Parent TopUpPage = loader.load();

        TopUpController control = loader.getController();
        control.initdata(customer);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(TopUpPage));
        stage.setTitle("Profile");
        stage.show();

    }
}
