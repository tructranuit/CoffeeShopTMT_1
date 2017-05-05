package ivc.coffee.shop.tmtruc.com.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.adapter.OrderAdapter;
import ivc.coffee.shop.tmtruc.com.model.CoffeeShop;
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
    String message;
    DrinkOrder drinkOrder;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        databaseHelper = new DatabaseHelper(getContext());


        if (orderDetailArrayList.size() == 0) {
            Toast.makeText(getContext(), "Không có sản phẩm nào!", Toast.LENGTH_SHORT).show();
        } else {

            orderAdapter = new OrderAdapter(getContext(), R.layout.order_item_layout, orderDetailArrayList);

            final ListView listView = (ListView) view.findViewById(R.id.lv_order);
            listView.setAdapter(orderAdapter);

        }

        Button btnOrder = (Button) view.findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "";
                double totalCost = 0;
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

                    drinkOrder = new DrinkOrder();
                    drinkOrder.setOrder_date(ActivityUtils.getDatetime());
                    drinkOrder.setTotal_cost(totalCost);

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                    createVerifyDialog(builder);
                }


                Log.d("Message", message);

            }
        });

        Button btnClear = (Button) view.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDetailArrayList.size() > 0) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                    builder.setIcon(R.drawable.ic_warning)
                            .setTitle("Delete all?")
                            .setMessage("Xóa toàn bộ giỏ hàng.")
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "Đã xóa toàn bộ sản phẩm", Toast.LENGTH_SHORT).show();
                                    orderDetailArrayList.clear();
                                    orderAdapter.notifyDataSetChanged();
                                }
                            });
                    android.app.AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        });

        return view;
    }


    //create dialog
    public void createVerifyDialog(android.app.AlertDialog.Builder builder) {

        builder.setIcon(R.drawable.ic_ok)
                .setTitle("Xác nhận order")
                .setMessage(message)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //insert data to database
                        int order_id = (int) databaseHelper.insertDrinkOrder(drinkOrder);

                        for (int i = 0; i < orderDetailArrayList.size(); i++) {
                            orderDetailArrayList.get(i).setOrder_id(order_id);
                            databaseHelper.insertOrderDetail(orderDetailArrayList.get(i));
                        }

                        //send message to order
                        sendMessage(message);
                        //clear data after order
                        message = null;
                        orderDetailArrayList.clear();
                        orderAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Xem lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    //send message to order
    public void sendMessage(String message) {
        CoffeeShop coffeeShop = databaseHelper.getCoffeeShop(1);
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + coffeeShop.getPhone_number()));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

}
