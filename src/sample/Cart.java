package sample;

public class Cart {
    // the object of cart and it's attribute

    private int ID;
    private String Name;
    private String Category;
    private String Notes;
    private int Amount;
    private int Price;

    //the constructor
    public Cart(int ID, String name, String category, String notes, int amount, int price) {
        this.ID = ID;
        this.Name = name;
        this.Category = category;
        this.Notes = notes;
        this.Amount = amount;
        this.Price = price;
    }

    // setter and getter
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }



}
