package ivc.coffee.shop.tmtruc.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.adapter.OrderAdapter;
import ivc.coffee.shop.tmtruc.com.model.DrinkOrder;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.model.OrderDetail;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;
import ivc.coffee.shop.tmtruc.com.util.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


    public static ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<>();

    private ArrayList<Drinks> drinksArrayList;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        if (orderDetailArrayList.size() == 0) {
            Toast.makeText(getContext(), "Không có sản phẩm nào!", Toast.LENGTH_SHORT).show();
        } else {

            drinksArrayList = new ArrayList<>();

            final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

            OrderAdapter orderAdapter = new OrderAdapter(getContext(), R.layout.order_item_layout, orderDetailArrayList);

            ListView listView = (ListView) view.findViewById(R.id.lv_order);
            listView.setAdapter(orderAdapter);

            Button btnOrder = (Button) view.findViewById(R.id.btn_order);


            btnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double totalCost = 0;
                    String message = "";
                    for (int i = 0; i < orderDetailArrayList.size(); i++) {
                        OrderDetail orderDetail = orderDetailArrayList.get(i);
                        Drinks drinks = databaseHelper.getDrink(orderDetail.getDrink_id());
                        totalCost += drinks.getPrice() * orderDetail.getQuantity();
                        message += drinks.getDrink_name() + "\nSố lượng: " + orderDetail.getQuantity();
                        message += "\n--------------------------\n";
                    }

                    DrinkOrder drinkOrder = new DrinkOrder();
                    drinkOrder.setOrder_date(ActivityUtils.getDatetime());
                    drinkOrder.setTotal_cost(totalCost);

                    //databaseHelper.insertDrinkOrder(drinkOrder);

                    Log.d("Message", message);

                }
            });

        }

        return view;
    }

}
