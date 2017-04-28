package ivc.coffee.shop.tmtruc.com.model;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class DrinkOrder {

    private int _id;
    private String order_date;
    private double total_cost;

    public DrinkOrder(int _id, String order_date, double total_cost) {
        this._id = _id;
        this.order_date = order_date;
        this.total_cost = total_cost;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }
}
