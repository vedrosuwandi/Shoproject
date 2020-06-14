package sample;

/**
 * Stores the Item
 */
public class Items {
    private int ID;
    private String Name;
    private String Location;
    private String Category;
    private String Description;
    private int price;
    private String Seller;
    private String Image;

    public Items(int ID, String name,String location,String category, int price ,String description , String seller , String image) {
        this.ID = ID;
        this.Name = name;
        this.Location = location;
        this.Category = category;
        this.Description = description;
        this.price = price;
        this.Seller = seller;
        this.Image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {

        Name = name;
    }

    public String getDescription() {

        return Description;
    }

    public void setImage(String image){
        Image = image;
    }
    public String getImage(){
        return Image;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return price;
    }
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }


}
