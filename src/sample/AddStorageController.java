package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Add Storage Page
 */
public class AddStorageController extends DBConnect {
    private Items item; // get the object of item and user data
    private User user;

    public String Pictures = ""; // initialize the picture path

    ObservableList<String> category = FXCollections.observableArrayList(); // the list in the choice box

    // the feature in javaFX
    @FXML
    private TextField Name;
    @FXML
    private TextField Location;
    @FXML
    private ChoiceBox<String> Category;
    @FXML
    private TextField Price;
    @FXML
    private TextField Description;
    @FXML
    private ImageView picture;

    @FXML
    public void initialize(){ // calls the
        Loadata();
    }

    // load data as the page is opened
    public void initdata(Items item , User user){
        this.user = user;
        this.item = item;
    }
    // the choice box
    public void Loadata(){
        category.removeAll(category); // remove the choice box and add something in the choice box
        String clothes = "Clothes";
        String books = "Books";
        String electronics = "Electronics";
        String toys = "Toys";
        String accessory = "Accessory";
        String watch = "Watch";
        String games = "Games";
        category.addAll(clothes , books , electronics , toys , accessory , watch , games);
        Category.getItems().addAll(category); // list the thing above in the box
    }
    // upload image to the item
    @FXML
    public void UploadImage(){
        FileChooser choose = new FileChooser(); // choose file
        File Select = choose.showOpenDialog(null); // specify the file chooser
        Pictures = Select.toURI().toString(); // get the file pathname
        Image Picture = new Image(Pictures); // get the image from the pathname
        picture.setImage(Picture); // set the picture

    }
    // ->> Go to storage
    @FXML
    public void BackToStorage(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Storage.fxml"));
        Parent MenuPage = loader.load();

        StorageController control = loader.getController(); // get the controller
        control.initdata(user); // load the page with initialize of the user's data

        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow(); // load page
        stage.setResizable(false); // can't resize
        stage.setScene(new Scene(MenuPage)); // set the new scene with the menupage parent
        stage.setTitle("Storage"); // title of the page
        stage.show(); // show the scene
    }
    // save added data to the database
    @FXML
    public void SaveData(ActionEvent event) throws IOException{
        String name = Name.getText(); // set the data
        String location = Location.getText();
        String price = Price.getText();
        String description = Description.getText();
        InsertItem(name , location , Category.getValue() , Integer.parseInt(price) , description , user.getName() , Pictures); // add the data in the database

        //and go to the storage page
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Storage.fxml"));
        Parent MenuPage = loader.load();

        StorageController control = loader.getController();
        control.initdata(user);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // load page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Shoproject");
        stage.show();
    }

}


