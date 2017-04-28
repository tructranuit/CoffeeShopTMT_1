package ivc.coffee.shop.tmtruc.com.model;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class CoffeeShopImage {
    private int _id = -1;
    private int shop_id;
    private String image_url;

    public CoffeeShopImage(int _id, int shop_id, String image_url) {
        this._id = _id;
        this.shop_id = shop_id;
        this.image_url = image_url;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
