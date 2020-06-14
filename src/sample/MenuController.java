package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Menu page
 */
public class MenuController extends DBConnect {
    private User user;
    private Cart cart;
    private Items item;
    // Table to store the item
    @FXML
    private TableView<Items> Shop;
    @FXML
    private TableColumn<Items , Integer> itemID;
    @FXML
    private TableColumn<Items , String> itemName;
    @FXML
    private TableColumn<Items , String> itemLocation;
    @FXML
    private TableColumn<Items , String> itemCategory;
    @FXML
    private TableColumn<Items , Integer> itemPrice;
    @FXML
    private TableColumn<Items , String> Seller;
    // list that store inside the table
    ObservableList<Items> ListofStore = FXCollections.observableArrayList();

    @FXML
    private Text Description;
    @FXML
    private Label FullName ;
    @FXML
    private Text BalanceField;
    @FXML
    private ChoiceBox<String> Category;
    @FXML
    private Label Warning;
    @FXML
    private ImageView Picture;

    ObservableList<String> category = FXCollections.observableArrayList();

    // initialize the window
    @FXML
    public void initialize(){
        Loadata();
    }
    // load the window the user data
    public void initdata(User user){
        this.user = user;
        FullName.setText(user.getName());
        BalanceField.setText(Integer.toString(UpdateBalanceC(user.getID()))); // keep update the price
    }

// fill the choice box
    public void Loadata(){
        category.removeAll(category);
        String clothes = "Clothes";
        String books = "Books";
        String electronics = "Electronics";
        String toys = "Toys";
        String accessory = "Accessory";
        String watch = "Watch";
        String games = "Games";
        category.addAll(clothes , books , electronics , toys , accessory , watch , games);
        Category.getItems().addAll(category);

    }

    // ->> go to profile
    @FXML
    private void ProfileButtonClicked(ActionEvent event)throws IOException{
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
    // add item to the cart
    @FXML
    public void AddToCart(){
        Items item = Shop.getSelectionModel().getSelectedItem(); // to get a selected item in the table
        if(item == null){ // if nothing is chosen
            Warning.setText("Please Insert a Data");
        }else{
            Cart(item.getName(),item.getCategory() ,item.getDescription(), 1 , item.getPrice());
            Warning.setText("");
            // add data in the cart table
        }
    }
    // ->> go to cart page
    @FXML
    public void GoToCart(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Cart.fxml"));
        Parent ProfilePage = loader.load();

        CartController control = loader.getController();
        control.initdata(user , cart);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(ProfilePage));
        stage.setTitle("Cart");
        stage.show();

    }
    //->> sign out
    @FXML
    public void SignOut(ActionEvent event) throws IOException{
        deletecart();
        Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("LoginPage");
        stage.setScene(new Scene(LoginParent));
        stage.show();
    }
    // ->> refresh the data
    @FXML
    public void Refresh(){ // the list in the table will show after the refresh button is clicked
        ListofStore.clear();

        String data = String.format("Select * from shop"); // select the whole data in the shop table
        try{
            ResultSet rs = connect.createStatement().executeQuery(data);
            while(rs.next()){
                ListofStore.add(new Items(rs.getInt("ID") , rs.getString("Name") , rs.getString("Location") , rs.getString("Category")  , rs.getInt("Price") ,rs.getString("Description"), rs.getString("Seller") , rs.getString("Picture")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        itemID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        itemName.setCellValueFactory( new PropertyValueFactory<>("Name"));
        itemLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Seller.setCellValueFactory(new PropertyValueFactory<>("Seller"));
        Shop.setItems(ListofStore); // set the data in the table
        Description.setText("");
        Picture.setImage(null);
        Warning.setText("");
    }

    // ->> view description
    public void viewdescription(){
        if(Shop.getSelectionModel().getSelectedIndex() < 0) {
            Warning.setText("Please Select an Item");
        }else{
            Description.setText(ListofStore.get(Shop.getSelectionModel().getSelectedIndex()).getDescription()); // view the description
            String picture = ListofStore.get(Shop.getSelectionModel().getSelectedIndex()).getImage(); // to view the selected item's picture
            Image pics = new Image(picture);
            if(picture.equals(null)){
                Picture.setImage(null);
            }else{
                Picture.setImage(pics);
            }
            Warning.setText("");
        }

    }

    public void refreshCategory(){
        ListofStore.clear();

        String data = String.format("Select * from shop where Category = '%s' ",Category.getValue()); //  get the category
        try{
            ResultSet rs = connect.createStatement().executeQuery(data);
            while(rs.next()){
                ListofStore.add(new Items(rs.getInt("ID") , rs.getString("Name") , rs.getString("Location") , rs.getString("Category") , rs.getInt("Price") ,rs.getString("Description"), rs.getString("Seller") , rs.getString("Picture")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        itemID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        itemName.setCellValueFactory( new PropertyValueFactory<>("Name"));
        itemLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Seller.setCellValueFactory(new PropertyValueFactory<>("Seller"));

        Warning.setText("");  // set all label to be empty
        Description.setText("");
        Picture.setImage(null);

        Shop.setItems(ListofStore);


    }

}
