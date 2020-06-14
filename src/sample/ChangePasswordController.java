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
 * Change Password page
 */

public class ChangePasswordController extends DBConnect {
    private User user;
    @FXML
    private TextField Old;
    @FXML
    private TextField New;
    @FXML
    private TextField ReNew;
    @FXML
    private Label Warning;

    // data initialization
    public void initdata(User user){
        this.user = user;
    }

    @FXML
    public void CancelButton(ActionEvent event)throws IOException {
        if(checkstaff(user.getUsername() , user.getPassword())){ // if user sign in as seller
            Sellerprofile(event); // go back to profile

        }else if(checkcustomer(user.getUsername() , user.getPassword())){// if user sign in as customer
            Customerprofile(event);
        }
    }

    @FXML
    public void ChangeButton(ActionEvent event) throws IOException{
        String old = Old.getText();
        String newPass = New.getText();
        String RenewPass = ReNew.getText();
        if(!old.equals(user.getPassword())){ // if the old password doesn't match with the user's current password
            Warning.setText("Old Password doesn't match");
        }
        if(newPass.equals(RenewPass)){ // if the new password match with Re-enter password
            if(newPass.length() < 8 && RenewPass.length() < 8){ // if the length of the new password is less than 8 letters
                Warning.setText("Password must be at least 8 characters");
            }else{
                if(checkcustomer(user.getUsername() , user.getPassword())){
                    ChangePasswordC(  user.getID(),newPass); // change the password in the database
                    Customerprofile(event); // back to profile
                }else if(checkstaff(user.getUsername() , user.getPassword())){
                    ChangePasswordS( user.getID(),newPass);
                    Sellerprofile(event);
                }
            }
        }else{  // if the new password doesn't match with Re-enter password
            Warning.setText("New Password doesn't match");
        }
    }
//->> function to back to profile
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
