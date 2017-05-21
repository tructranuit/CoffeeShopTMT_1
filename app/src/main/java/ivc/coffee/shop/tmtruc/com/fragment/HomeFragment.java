package ivc.coffee.shop.tmtruc.com.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.activity.MapsActivity;
import ivc.coffee.shop.tmtruc.com.model.CoffeeShop;
import ivc.coffee.shop.tmtruc.com.model.CoffeeShopImage;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<CoffeeShopImage> coffeeShopImageList;
    DatabaseHelper databaseHelper;

    DatabaseReference database;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        database = FirebaseDatabase.getInstance().getReference();
//        writeNewCoffeeShop();

        //create database helper
        databaseHelper = new DatabaseHelper(getContext());
        //create data for coffee shop table
        createDataForCoffeeShopTable(databaseHelper);
        //create data for coffee shop image table
        createDataForCoffeeShopImageTable(databaseHelper);


        CoffeeShop coffeeShop = databaseHelper.getCoffeeShop(1);

        TextView tvCoffeeShopName = (TextView) view.findViewById(R.id.tv_coffee_shop_name);
        tvCoffeeShopName.setText(coffeeShop.getShop_name());
        final TextView tvCoffeeShopPhoneNumber = (TextView) view.findViewById(R.id.tv_coffee_phone_number);
        tvCoffeeShopPhoneNumber.setText(coffeeShop.getPhone_number());
        TextView tvCoffeeShopAddress = (TextView) view.findViewById(R.id.tv_coffee_address);
        tvCoffeeShopAddress.setText(coffeeShop.getAddress());
        TextView tvCoffeeShopDescription = (TextView) view.findViewById(R.id.tv_description);
        tvCoffeeShopDescription.setText(coffeeShop.getDescription());

        //get all image of coffee shop
        coffeeShopImageList = databaseHelper.getAllCoffeShopImageOfShop(coffeeShop.getShop_name());

        ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        //create image slider
        createImageSlider(viewFlipper);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_google_map);
        //click google maps icon
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        //click phone number
        tvCoffeeShopPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tvCoffeeShopPhoneNumber.getText()));
                startActivity(intent);
            }
        });


        return view;
    }

    // create image slider with viewflipper
    public void createImageSlider(ViewFlipper viewFlipper) {
        for (int i = 0; i < coffeeShopImageList.size(); i++) {
            addImageToViewFlipper(viewFlipper, i);
        }
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right));

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(8000);
    }

    //add image to viewflipper
    public void addImageToViewFlipper(ViewFlipper viewFlipper, int position) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setAdjustViewBounds(true);
        Picasso.with(getContext())
                .load(coffeeShopImageList.get(position).getImage_url())
                .placeholder(R.drawable.img_default_1)
                .error(R.drawable.img_default_1)
                .into(imageView);
        viewFlipper.addView(imageView);
    }

    //create data for coffee shop table
    public void createDataForCoffeeShopTable(DatabaseHelper databaseHelper) {
        CoffeeShop coffeeShop = new CoffeeShop(1,
                getResources().getString(R.string.coffee_shop_name),
                getResources().getString(R.string.coffee_shop_phone_number),
                getResources().getString(R.string.coffee_shop_address),
                getResources().getString(R.string.coffee_shop_description),
                Double.valueOf(getResources().getString(R.string.coffee_shop_latitude)),
                Double.valueOf(getResources().getString(R.string.coffee_shop_longitude))
        );
        databaseHelper.insertCoffeeShop(coffeeShop);
    }

    //create data for coffee shop image table
    public void createDataForCoffeeShopImageTable(DatabaseHelper databaseHelper) {
        List<CoffeeShopImage> coffeeShopImageList = new ArrayList<>();
        coffeeShopImageList.add(new CoffeeShopImage(1, 1, "http://i.imgur.com/dgr8YjI.jpg"));
        coffeeShopImageList.add(new CoffeeShopImage(2, 1, "http://i.imgur.com/8m7y4iZ.jpg"));
        coffeeShopImageList.add(new CoffeeShopImage(3, 1, "http://i.imgur.com/81apX9W.jpg"));
        for (int i = 0; i < coffeeShopImageList.size(); i++) {
            databaseHelper.insertCoffeeShopImage(coffeeShopImageList.get(i));
        }
    }

//    private void writeNewCoffeeShop() {
//        CoffeeShop coffeeShop = new CoffeeShop(1,
//                getResources().getString(R.string.coffee_shop_name),
//                getResources().getString(R.string.coffee_shop_phone_number),
//                getResources().getString(R.string.coffee_shop_address),
//                getResources().getString(R.string.coffee_shop_description),
//                Double.valueOf(getResources().getString(R.string.coffee_shop_latitude)),
//                Double.valueOf(getResources().getString(R.string.coffee_shop_longitude))
//        );
//        database.child("coffee_shop").push().setValue(coffeeShop);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
