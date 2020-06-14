package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class EditStorageController extends DBConnect {
    private Items item;
    private User user;
    @FXML
    private TextField Name;
    @FXML
    private TextField Location;
    @FXML
    private TextField Price;
    @FXML
    private TextField Description;
    @FXML
    private ImageView Image;


    @FXML
    public void initdata(Items item){// initialize data with the user's data and the item's data
        Name.setText(item.getName());
        Location.setText(item.getLocation());
        Price.setText(Integer.toString(item.getPrice()));
        Description.setText(item.getDescription());
        this.item = item;
    }
    @FXML
    public void SaveData(){ // save the data edited
        String name = Name.getText();
        String location = Location.getText();
        int price = Integer.parseInt(Price.getText());
        String description = Description.getText();

        EditItemName(item.getID() , name);
        EditItemLocation(item.getID() , location);
        EditItemPrice(item.getID() , price);
        EditItemDescription(item.getID() , description);
    }

    public void BackToStorage(ActionEvent event) throws IOException{ // cancel
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Storage.fxml"));
        Parent MenuPage = loader.load();

        StorageController control = loader.getController();
        control.initdata(user);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // load the page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Storage");
        stage.show();
    }

}
