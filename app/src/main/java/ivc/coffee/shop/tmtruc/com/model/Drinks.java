package ivc.coffee.shop.tmtruc.com.model;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class Drinks {
    private int _id = -1;
    private String drink_name;
    private String description;
    private String type;
    private double price;

    public Drinks(int _id, String drink_name, String description, String type, double price) {
        this._id = _id;
        this.drink_name = drink_name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
