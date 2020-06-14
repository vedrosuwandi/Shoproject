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

/**
 * Cart page
 */
public class CartController extends DBConnect{
    private User user;
    private Cart cart;
    private Items item;
    @FXML
    private Label Balance;
    @FXML
    private Label Total;
    @FXML
    private Label Warning;
    @FXML
    private TableView<Cart> CartList;
    @FXML
    private TableColumn<Cart , Integer> ID;
    @FXML
    private TableColumn<Cart , String > Name;
    @FXML
    private TableColumn<Cart , String> Category;
    @FXML
    private TableColumn<Cart , Integer > Amount;
    @FXML
    private TableColumn<Cart , Integer > Price;

    public  int total = 0; // the total price initialization

    ObservableList<Cart> ListCart = FXCollections.observableArrayList(); // store the list inside the table

// load data
    @FXML
    public void initdata(User user,Cart cart){
        this.user = user;
        this.cart = cart;
        Total.setText(Integer.toString(total));
        Balance.setText(Integer.toString(UpdateBalanceC(user.getID())));
    }
// back to menu
    @FXML
    public void BackToMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent MenuPage = loader.load();

        MenuController control = loader.getController();
        control.initdata(user);

        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow(); // open new page
        stage.setResizable(false);
        stage.setScene(new Scene(MenuPage));
        stage.setTitle("Shoproject");
        stage.show();
    }
// make a purchase
    public void Purchase(ActionEvent event) throws  IOException{
        if (UpdateBalanceC(user.getID()) < total) { // if the money is less than the price
            Warning.setText("Insufficient Balance");
        } else {
            deletecart(); // delete the whole thing in the table
            ChangeBalanceC(user.getID(), UpdateBalanceC(user.getID()) - total); // decrease the money after making a purchase
            //->> go to gratitude page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Gratitude.fxml"));
            Parent MenuPage = loader.load();

            GratitudeController control = loader.getController();
            control.initdata(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Load page
            stage.setResizable(false);
            stage.setScene(new Scene(MenuPage));
            stage.setTitle("Thank You");
            stage.show();
        }
    }
// remove item from the cart
    public void Remove(){
        Cart cart =  CartList.getSelectionModel().getSelectedItem(); // selected item from the table
        if(cart == null){ // if nothing is selected
            Warning.setText("Please Select an Item");
        }else{
            total -= cart.getPrice(); // subtract the price (Incomplete can only the first one)
            Remove(cart.getID()); // remove the item from database
            Warning.setText("");
            Refreshsub();
        }
    }

    // refresh page
    public void Refresh(){
        ListCart.clear();
        total = 0; // so the total price doesn't keep increasing after refresh button is pressed
        try{
            String data = String.format("Select * from cart"); // get the whole thing from the cart table
            ResultSet rs = connect.createStatement().executeQuery(data); // the set of result shown in the database
            while(rs.next()){
                total += rs.getInt("Price"); // get the total price
                ListCart.add(new Cart(rs.getInt("ID") , rs.getString("Name") , rs.getString("Category") ,rs.getString("Notes") , rs.getInt("Amount") , rs.getInt("Price"))); // get the column name of the table

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        // To set the data inside the table
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory( new PropertyValueFactory<>("Name"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        CartList.setItems(ListCart); // set the data inside the table
        Total.setText(Integer.toString(total)); // get  the total price
        Balance.setText(Integer.toString(UpdateBalanceC(user.getID())));
        Warning.setText("");
    }

// auto refresh after removing the item so the total price auto decrement
    public void Refreshsub(){
        ListCart.clear();
        try{
            String data = String.format("Select * from cart"); // get the whole thing from the cart table
            ResultSet rs = connect.createStatement().executeQuery(data); // the set of result shown in the database
            while(rs.next()){
                ListCart.add(new Cart(rs.getInt("ID") , rs.getString("Name") , rs.getString("Category") ,rs.getString("Notes") , rs.getInt("Amount") , rs.getInt("Price"))); // get the column name of the table

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        // To set the data inside the table
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory( new PropertyValueFactory<>("Name"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        CartList.setItems(ListCart); // set the data inside the table
        Total.setText(Integer.toString(total)); // get  the total price
        Balance.setText(Integer.toString(UpdateBalanceC(user.getID())));
    }

}
