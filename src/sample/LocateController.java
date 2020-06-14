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

public class LocateController extends DBConnect{
    @FXML
    private TextField NewLocate;
    @FXML
    private Label Warning;
    private User user;

    public void initdata(User user){ // initialize of the page
        this.user = user;
        NewLocate.setText(user.getAddress());
    }

    @FXML
    public void Locate(ActionEvent event)throws IOException{
        String newlocation = NewLocate.getText();
        if(newlocation.equals(user.getAddress())){ // if the location is the same
            Warning.setText("Please Insert New Location");
        }else{
            if(checkstaff(user.getUsername() , user.getPassword())){ // change the location of the seller if user login as seller
                EditLocationS( user.getID(),newlocation);
                Sellerprofile(event);

            }else if(checkcustomer(user.getUsername() , user.getPassword())){ // change the location of the customer
                EditLocationC( user.getID(),newlocation);
                Customerprofile(event);
            }
        }
    }
    @FXML
    public void CanceLocate(ActionEvent event) throws IOException{ // Back To Profile
        if(checkstaff(user.getUsername() , user.getPassword())){
            Sellerprofile(event);

        }else if(checkcustomer(user.getUsername() , user.getPassword())){
            Customerprofile(event);
        }
    }
// ->> function of getting back to the profile page
    public void Sellerprofile(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SellerProfile.fxml"));
        Parent SellerProfilePage = loader.load();

        SellerProfileController control = loader.getController();
        control.initdata(user);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Load page
        stage.setResizable(false);
        stage.setScene(new Scene(SellerProfilePage));
        stage.setTitle("Seller Profile");
        stage.show();
    }

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
