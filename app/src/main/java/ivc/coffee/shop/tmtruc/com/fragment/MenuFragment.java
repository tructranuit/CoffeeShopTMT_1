package ivc.coffee.shop.tmtruc.com.fragment;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.activity.DrinkDetailActivity;
import ivc.coffee.shop.tmtruc.com.adapter.DrinkAdapter;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;
import ivc.coffee.shop.tmtruc.com.util.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        //create database helper
        DatabaseHelper databaseHelper = ActivityUtils.createDatabaseHelper(getContext());

        createDataForDrinksTable(databaseHelper);
        createDataForDinkImageTable(databaseHelper);

        GridView grvMenu = (GridView) view.findViewById(R.id.grid_menu);

        final List<Drinks> drinksList = databaseHelper.getAllDrink();
        DrinkAdapter adapter = new DrinkAdapter(getContext(), R.layout.menu_item_layout, drinksList);
        grvMenu.setAdapter(adapter);

        grvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //send data to drink detail activity
                Intent intent = new Intent(getContext(), DrinkDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", drinksList.get(position));
                intent.putExtra("PACKAGE", bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    //create data for drinks table
    public void createDataForDrinksTable(DatabaseHelper databaseHelper){
        List<Drinks> drinksList = new ArrayList<>();
        drinksList.add(new Drinks(1, "Cafe Đen", "Cafe Đen", "Cafe", 30000));
        drinksList.add(new Drinks(2, "Cafe Sữa", "Cafe Sữa Nóng", "Cafe", 30000));
        drinksList.add(new Drinks(3, "Cafe Cappuccino", "Cafe Cappuccino", "Cafe", 50000));
        drinksList.add(new Drinks(4, "Cafe Latte Macchiato", "Cafe Latte Macchiato", "Cafe", 50000));
        drinksList.add(new Drinks(5, "Cafe Latte", "Cafe Latte", "Cafe", 50000));
        drinksList.add(new Drinks(6, "Sinh tố Xoài", "Sinh tố Xoài", "Sinh tố", 30000));
        drinksList.add(new Drinks(7, "Sinh tố Cà rốt", "Sinh tố Cà rốt", "Sinh tố", 30000));
        drinksList.add(new Drinks(8, "Sinh tố Đu đủ", "Sinh tố Đu đủ", "Sinh tố", 30000));
        drinksList.add(new Drinks(9, "Sinh tố Dâu", "Sinh tố Dâu", "Sinh tố", 30000));
        drinksList.add(new Drinks(10, "Nước ép Cà chua", "Nước ép Cà chua", "Nước ép", 30000));
        drinksList.add(new Drinks(11, "Nước ép Táo", "Nước ép Táo", "Nước ép", 30000));
        drinksList.add(new Drinks(12, "Nước ép Cam", "Nước ép Cam", "Nước ép", 30000));
        drinksList.add(new Drinks(13, "Trà Lipton chanh", "Trà Lipton chanh", "Trà", 30000));
        drinksList.add(new Drinks(14, "Trà táo bạc hà", "Trà táo bạc hà", "Trà", 30000));
        drinksList.add(new Drinks(15, "Trà đào", "Trà đào", "Trà", 30000));
        drinksList.add(new Drinks(16, "Trà xoài", "Trà xoài", "Trà", 30000));
        for (int i = 0; i<drinksList.size(); i++){
            databaseHelper.insertDrink(drinksList.get(i));
        }
    }

    //create data for drink image table
    public void createDataForDinkImageTable(DatabaseHelper databaseHelper){
        List<DrinkImage> drinkImageList = new ArrayList<>();
        drinkImageList.add(new DrinkImage(1, 1,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_cafe_den.jpg"));
        drinkImageList.add(new DrinkImage(2, 2,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_cafe_sua.png"));
        drinkImageList.add(new DrinkImage(3, 3,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_cafe_cappuccino.jpg"));
        drinkImageList.add(new DrinkImage(4, 4,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_cafe_latte_macchiato.jpg"));
        drinkImageList.add(new DrinkImage(5, 5,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_cafe_latte.jpg"));
        drinkImageList.add(new DrinkImage(6, 6,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_sinh_to_xoai.jpg"));
        drinkImageList.add(new DrinkImage(7, 7,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_sinh_to_ca_rot.jpg"));
        drinkImageList.add(new DrinkImage(8, 8,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_sinh_to_du_du.jpg"));
        drinkImageList.add(new DrinkImage(9, 9,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_sinh_to_dau.jpg"));
        drinkImageList.add(new DrinkImage(10, 10,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_nuoc_ep_ca_chua.jpg"));
        drinkImageList.add(new DrinkImage(11, 11,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_nuoc_ep_tao.jpg"));
        drinkImageList.add(new DrinkImage(12, 12,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_nuoc_ep_cam.jpg"));
        drinkImageList.add(new DrinkImage(13, 13,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_tra_lipton_chanh.jpg"));
        drinkImageList.add(new DrinkImage(14, 14,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_tra_tao_bac_ha.jpg"));
        drinkImageList.add(new DrinkImage(15, 15,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_tra_dao.jpg"));
        drinkImageList.add(new DrinkImage(16, 16,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Images/drink_img_tra_xoai.jpg"));
        //insert to database
        for (int i = 0; i<drinkImageList.size(); i++){
            databaseHelper.insertDrinkImage(drinkImageList.get(i));
        }
    }
}
