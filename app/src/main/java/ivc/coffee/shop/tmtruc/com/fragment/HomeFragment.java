package ivc.coffee.shop.tmtruc.com.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.activity.MainActivity;
import ivc.coffee.shop.tmtruc.com.activity.MapsActivity;
import ivc.coffee.shop.tmtruc.com.adapter.ViewPagerAdapter;
import ivc.coffee.shop.tmtruc.com.model.CoffeeShop;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<Integer> imageList;
    ViewFlipper viewFlipper;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        CoffeeShop coffeeShop = new CoffeeShop(2,
//                getResources().getString(R.string.coffee_shop_name),
//                getResources().getString(R.string.coffee_shop_phone_number) ,
//                getResources().getString(R.string.coffee_shop_address),
//                getResources().getString(R.string.coffee_shop_description),
//                10.801945,
//                106.640050
//        );
//
//        MainActivity.databaseHelper.insertCoffeeShop(coffeeShop);

        CoffeeShop coffeeShop = MainActivity.databaseHelper.getCoffeeShop(1);

        TextView tvCoffeeShopName = (TextView) view.findViewById(R.id.tv_coffee_shop_name);
        tvCoffeeShopName.setText(coffeeShop.getShop_name());
        TextView tvCoffeeShopPhoneNumber = (TextView) view.findViewById(R.id.tv_coffee_phone_number);
        tvCoffeeShopPhoneNumber.setText(coffeeShop.getPhone_number());
        TextView tvCoffeeShopAddress = (TextView) view.findViewById(R.id.tv_coffee_address);
        tvCoffeeShopAddress.setText(coffeeShop.getAddress());
        TextView tvCoffeeShopDescription = (TextView) view.findViewById(R.id.tv_description);
        tvCoffeeShopDescription.setText(coffeeShop.getDescription());


        imageList = new ArrayList<>();
        imageList.add(R.drawable.coffee_shop_img_1);
        imageList.add(R.drawable.coffee_shop_img_2);
        imageList.add(R.drawable.coffee_shop_img_3);
        imageList.add(R.drawable.coffee_shop_img_4);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        createImageSlider(viewFlipper);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_google_map);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // create image slider with viewflipper
    public void createImageSlider(ViewFlipper viewFlipper) {
        for (int i = 0; i < imageList.size(); i++) {
            setFlipperImage(i);
        }
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right));

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(8000);
    }

    //add image to viewflipper
    public void setFlipperImage(int position) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setAdjustViewBounds(true);
        Glide.with(getContext()).load(imageList.get(position)).into(imageView);
        viewFlipper.addView(imageView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
