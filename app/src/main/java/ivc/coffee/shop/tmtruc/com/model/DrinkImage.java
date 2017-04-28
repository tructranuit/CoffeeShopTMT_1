package ivc.coffee.shop.tmtruc.com.model;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class DrinkImage {
    private int _id = -1;
    private int drink_id;
    private String image_url;

    public DrinkImage(int _id, int drink_id, String image_url) {
        this._id = _id;
        this.drink_id = drink_id;
        this.image_url = image_url;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
