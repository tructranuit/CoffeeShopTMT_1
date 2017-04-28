package ivc.coffee.shop.tmtruc.com.model;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class CoffeeShop {
    private int _id = -1;
    private String shop_name;
    private String phone_number;
    private String address;
    private String description;
    private float latitude;
    private float longitude;

    public CoffeeShop(int _id, String shop_name, String phone_number, String address, String description, float latitude, float longitude) {
        this._id = _id;
        this.shop_name = shop_name;
        this.phone_number = phone_number;
        this.address = address;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
