package ivc.coffee.shop.tmtruc.com.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import ivc.coffee.shop.tmtruc.com.util.FormatNumberUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


    public static ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<>();
    DatabaseHelper databaseHelper;
    OrderAdapter orderAdapter;


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

            databaseHelper = new DatabaseHelper(getContext());

            orderAdapter = new OrderAdapter(getContext(), R.layout.order_item_layout, orderDetailArrayList);

            final ListView listView = (ListView) view.findViewById(R.id.lv_order);
            listView.setAdapter(orderAdapter);

        }

        Button btnOrder = (Button) view.findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalCost = 0;
                String message = "";
                if (orderDetailArrayList.size() == 0) {
                    Toast.makeText(getContext(), "Không có sản phẩm nào!", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < orderDetailArrayList.size(); i++) {
                        OrderDetail orderDetail = orderDetailArrayList.get(i);
                        Drinks drinks = databaseHelper.getDrink(orderDetail.getDrink_id());
                        totalCost += drinks.getPrice() * orderDetail.getQuantity();
                        message += drinks.getDrink_name() + "\nSố lượng: " + orderDetail.getQuantity();
                        message += "\n--------------------------\n";
                    }
                    message += "\nTổng cộng: " + FormatNumberUtils.formatNumber(totalCost) + "đ";
                }

                DrinkOrder drinkOrder = new DrinkOrder();
                drinkOrder.setOrder_date(ActivityUtils.getDatetime());
                drinkOrder.setTotal_cost(totalCost);



                //databaseHelper.insertDrinkOrder(drinkOrder);

                Log.d("Message", message);

            }
        });

        return view;
    }
}
