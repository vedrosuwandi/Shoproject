package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StorageController extends DBConnect {
    private User seller;
    private Items item;

    @FXML
    private Label Warning;
    @FXML
    private TableView<Items> ShopList;
    @FXML
    private TableColumn<Items , Integer > ID;
    @FXML
    private TableColumn<Items , String > Name ;
    @FXML
    private TableColumn<Items , String > Location;
    @FXML
    private TableColumn<Items , String > Category;
    @FXML
    private TableColumn<Items , Integer > Price;
    @FXML
    private TableColumn<Items , String > Description;
    @FXML
    private TableColumn<Items , User> Seller;

    private ObservableList<Items> ListofItems = FXCollections.observableArrayList();

    public void initdata(User user) {
        this.seller= user;
    }

    @FXML
    public void AddItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddStorage.fxml"));
        Parent StoragePage = loader.load();

        AddStorageController control = loader.getController();
        control.initdata(item,seller);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(StoragePage));
        stage.setTitle("Storage");
        stage.show();
    }

    @FXML
    public void Delete(){
        Items item = ShopList.getSelectionModel().getSelectedItem();
        if(item != null){
            DeleteItem(item.getID());
            Warning.setText("");
            Refresh();
        }else{
            Warning.setText("Please Select an Item");
        }
    }

    @FXML
    public void EditItem(ActionEvent event) throws IOException {
        Items items = ShopList.getSelectionModel().getSelectedItem(); // selected item in the table
        if(items != null){ // if choose something
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditStorage.fxml"));
            Parent StoragePage = loader.load();

            EditStorageController control = loader.getController();
            control.initdata(items);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(new Scene(StoragePage));
            stage.setTitle("Storage");
            stage.show();
        }else{
            Warning.setText("Please Select Data!!");
        }
    }

//->> Go to Profile
    public void BackToProfile(ActionEvent event) throws  IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SellerProfile.fxml"));
        Parent ProfilePage = loader.load();

        SellerProfileController control = loader.getController();
        control.initdata(seller);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(ProfilePage));
        stage.setTitle("Seller Profile");
        stage.show();
    }
// ->> Sign Out
    @FXML
    public void SignOut(ActionEvent event) throws IOException{
        Parent LoginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("LoginPage");
        stage.setScene(new Scene(LoginParent));
        stage.show();
    }
    // Refresh page
    @FXML
    public void Refresh(){
        // clear the data first and replace it with the new one
        ListofItems.clear();
        try{
            String data = String.format("Select * from shop");
            ResultSet rs = connect.createStatement().executeQuery(data);
            while(rs.next()){
                ListofItems.add(new Items(rs.getInt("ID") , rs.getString("Name") ,rs.getString("Location") , rs.getString("Category")  , rs.getInt("Price") , rs.getString("Description") , rs.getString("Seller") , rs.getString("Picture")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        // To set the data inside the table
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory( new PropertyValueFactory<>("Name"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Seller.setCellValueFactory(new PropertyValueFactory<>("Seller"));

        ShopList.setItems(ListofItems);
        Warning.setText("");


    }
}