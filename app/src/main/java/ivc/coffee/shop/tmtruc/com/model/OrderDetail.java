package ivc.coffee.shop.tmtruc.com.model;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class OrderDetail {
    private int _id;
    private int order_id;
    private int drink_id;
    private int quantity = 0;

    public OrderDetail(){
    }

    public OrderDetail(int _id, int order_id, int drink_id, int quantity) {
        this._id = _id;
        this.order_id = order_id;
        this.drink_id = drink_id;
        this.quantity = quantity;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && obj instanceof OrderDetail){
            isEqual = (this.getDrink_id() == ((OrderDetail) obj).getDrink_id());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.get_id();
    }
}
