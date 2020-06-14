package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Profile (Seller) Page
 */
public class SellerProfileController extends DBConnect {
    private User seller;
    private Items item;

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

    // load the user data as the page is opened
    public void initdata(User user) {
        this.seller = user;
        ID.setText(Integer.toString(this.seller.getID()));
        Name.setText(this.seller.getName());
        Username.setText(this.seller.getUsername());
        Email.setText(this.seller.getEmail());
        Address.setText(this.seller.getAddress());
    }

    // Manage storage
    @FXML
    public void BackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Storage.fxml"));
        Parent MenuPage = loader.load();

        StorageController control = loader.getController();
        control.initdata(seller); // get the data of the seller

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Load page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Storage");
        stage.show();
    }

    // change password
    @FXML
    public void ChangePass(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChangePassword.fxml"));
        Parent MenuPage = loader.load();

        ChangePasswordController control = loader.getController();
        control.initdata(seller);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Change Password");
        stage.show();
    }

    // change location
    @FXML
    public void ChangeLocateSeller(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Locate.fxml"));
        Parent MenuPage = loader.load();

        LocateController control = loader.getController();
        control.initdata(seller);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Change Location");
        stage.show();
    }

}
