package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Top Up Page
 */

public class TopUpController extends DBConnect{
    private User user;
    @FXML
    private TextField Amount;
    @FXML
    private Label Warning;

    // load the data with user's data
    public void initdata(User user){
        this.user = user;
    }
    // cancel the top-up
    @FXML
    public void CancelButton(ActionEvent event) throws IOException { // Back to profile (Seller or Customer)
        if(checkcustomer(user.getUsername() , user.getPassword())){
            Customerprofile(event);
        }
    }
    // top up balance
    @FXML
    public void TopUpButton(ActionEvent event) throws IOException{
        String amount = Amount.getText(); // enter amount that needed to add
        if(Integer.parseInt(amount) <= 0){
            Warning.setText("Top-Up Invalid");
        }else{
            int money = UpdateBalanceC(user.getID()) + Integer.parseInt(amount); // add the money
            ChangeBalanceC(user.getID(),money);
            UpdateBalanceC(user.getID());
            Customerprofile(event);
        }
    }
    // function for ->> Profile (Customer)
    public void Customerprofile(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Profile.fxml"));
        Parent ProfilePage = loader.load();

        ProfileController control = loader.getController();
        control.initdata(user);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(ProfilePage));
        stage.setTitle("Profile");
        stage.show();
    }
}
