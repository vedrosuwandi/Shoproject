package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GratitudeController extends DBConnect{
    private User user;
    public void initdata(User user){
        this.user = user;
    }

    @FXML
    public void BackToMenu(ActionEvent event) throws IOException{ // back to menu
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent MenuPage = loader.load();

        MenuController control = loader.getController();
        control.initdata(user);

        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Storage");
        stage.show();
    }
}
