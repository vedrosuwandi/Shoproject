package sample;
import java.sql.*;

public class DBConnect {
    public Connection connect;
    public Statement state;

    public DBConnect() { // constructor
        String Database_name = "Shoproject";
        String Username = "root";
        String Password = "";
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database_name, Username, Password); //get the connection to the database
            state = connect.createStatement(); // create statement
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    // STAFF
    public void InsertStaff(String Name,  String Address, String email, String Username, String Password) { // create data in the staff table
        String data = String.format("Insert into staff(Name,Address,Email,Username,Password) VALUES( '%s' ,'%s' , '%s', '%s', '%s')", Name, Address, email, Username, Password); // insert the data in the table
        try {
            state = connect.createStatement(); // create statement
            state.executeUpdate(data); // to get a number of rows affected  (apply for  INSERT, UPDATE, or DELETE statement)
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void EditLocationS(int ID,String Address ) { // edit location in the table
        String data = String.format("Update staff set Address = '%s' where ID = '%s'" , Address , ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ChangePasswordS( int ID,String Password ) { // edit password in the table
        String data = String.format("update staff set Password ='%s' where ID = '%s'" , Password , ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    //  CUSTOMER
    public void InsertCustomer(String Name, int Balance, String Address, String email, String Username, String Password) {// create data in the customer table
        String data = String.format("Insert into customer(Name,Balance,Address,Email,Username,Password) VALUES('%s', '%s' ,'%s' , '%s', '%s' , '%s')", Name, Balance, Address, email, Username, Password); // insert the data in the table
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Data Inserted");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ChangePasswordC(  int ID,String Password ) {  // edit password in the table
        String data = String.format("Update customer set Password = '%s' where ID = '%s'" , Password , ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Password has been changed");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void ChangeBalanceC( int ID ,int Balance ) {  // edit balance in the table
        String data = String.format("Update customer set Balance = '%s' where ID = '%s'" ,Balance , ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public int UpdateBalanceC(int ID ){ // update the balance as the user top up
        String data = String.format("select Balance from customer where ID = '%s'" , ID);
        try {
            state = connect.createStatement();
            ResultSet rs = state.executeQuery(data); // to get return of result set this statement is essential
            if (rs.next()) {
                return rs.getInt(1); // update the balance of one row affected which is the one chosen by ID
            }
            else {
                return -1;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    public void EditLocationC( int ID,String Address) {  // edit location in the table
        String data = String.format("Update customer set Address = '%s' where ID = '%s'" , Address , ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Location has been successfully changed");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Shop
    public void InsertItem(String Name, String Location,String Category , int Price, String Description, String Seller , String Picture) { // create data in the shop table
        String data = String.format("Insert into shop(Name,Location,Category,Price,Description,Seller,Picture) VALUES('%s','%s','%s','%s','%s','%s','%s')", Name,Location,Category,Price,Description,Seller,Picture); // insert the data in the table
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Data Inserted");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteItem(int ID) { // delete item from table
        String data = String.format("delete from shop where ID = '%s'", ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Item has been removed from shop");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * edit the item attribute
     *
     */
    public void EditItemName(int ID, String Name) {
        String data = String.format("Update from shop set %h where Name = '%s'", ID, Name);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Name Edited");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void EditItemLocation(int ID, String Location) {
        String data = String.format("Update from shop set %h where Location = '%s'", ID, Location);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Name Edited");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void EditItemPrice(int ID, int Price) {
        String data = String.format("Update from shop set %h where Price = '%s'", ID, Price);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Description Edited");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void EditItemDescription(int ID, String Description) {
        String data = String.format("Update from shop set %h where Name = '%s'", ID, Description);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Description Edited");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    // CART
    public void Cart(String Name, String Category,String Notes, int Amount , int Price) { // add something in the cart table
        String data = String.format("Insert into cart(Name,Category,Notes,Amount,price) VALUES('%s','%s' ,'%s' , '%s', '%s')", Name,Category ,Notes, Amount , Price); // insert the data in the table
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Data Inserted");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Remove(int ID) { // delete an item from the cart table
        String data = String.format("delete from cart where ID = '%s'", ID);
        try {
            state = connect.createStatement();
            state.executeUpdate(data);
            System.out.println("Item Removed");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deletecart(){ // deleting data after purchase
        String data = String.format("truncate cart");
        try{
            state = connect.createStatement();
            state.executeUpdate(data);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    // check User
    public boolean checkstaff(String Username,String Password){ // to check the username and the password matches based on the database(Seller)
        String data = String.format("Select * from staff where Username='%s' and Password = '%s'" , Username , Password);
        try{
            state = connect.createStatement();
            ResultSet rs = state.executeQuery(data);
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }


    public boolean checkcustomer(String Username ,String Password){// to check the username and the password matches based on the database(Customer)
        String data = String.format("Select * from customer where Username='%s' and Password = '%s'" , Username , Password);
        try{
            state = connect.createStatement();
            ResultSet rs = state.executeQuery(data);
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public User checkaccountC(String Username , String Password) { // check account whether the customer's username and password correct or not
        User tempUser = new User(); // create the variable with an object as it's type to be returned
        String data = "";
        if (checkcustomer(Username, Password)) {
            data = String.format("Select * from customer where Username='%s' and Password = '%s'", Username, Password);
        }
        try {
            state = connect.createStatement();
            ResultSet rs = state.executeQuery(data);
            rs.next();
            tempUser.setID(rs.getInt("ID"));
            tempUser.setName(rs.getString("Name"));
            tempUser.setBalance(rs.getInt("Balance")); // with balance
            tempUser.setAddress(rs.getString("Address"));
            tempUser.setEmail(rs.getString("Email"));
            tempUser.setUsername(rs.getString("Username"));
            tempUser.setPassword(rs.getString("Password"));
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return tempUser; // return the object
    }
    public User checkaccountS(String Username , String Password) { // check account whether the seller's username and password correct or not
        User tempUser = new User();
        String data = "";
        if (checkstaff(Username, Password)) {
            data = String.format("Select * from staff where Username='%s' and Password = '%s'", Username, Password);
        }
        try {
            state = connect.createStatement();
            ResultSet rs = state.executeQuery(data);
            rs.next();
            tempUser.setID(rs.getInt("ID"));
            tempUser.setName(rs.getString("Name"));
            // without balance
            tempUser.setAddress(rs.getString("Address"));
            tempUser.setEmail(rs.getString("Email"));
            tempUser.setUsername(rs.getString("Username"));
            tempUser.setPassword(rs.getString("Password"));
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return tempUser;
    }

}

