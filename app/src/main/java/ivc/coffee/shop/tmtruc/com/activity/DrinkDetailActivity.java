package ivc.coffee.shop.tmtruc.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.util.ActivityUtils;
import ivc.coffee.shop.tmtruc.com.util.FormatNumberUtils;

public class DrinkDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("PACKAGE");
        Drinks drinks = (Drinks) bundle.getSerializable("DATA");


        TextView tvDrinkName = (TextView) findViewById(R.id.tv_drink_name);
        tvDrinkName.setText(drinks.getDrink_name());
        TextView tvDrinkPrice = (TextView) findViewById(R.id.tv_drink_price);
        tvDrinkPrice.setText(FormatNumberUtils.formatNumber(drinks.getPrice()) + "đ");
        TextView tvDrinkDescription = (TextView) findViewById(R.id.tv_drink_description);
        tvDrinkDescription.setText(drinks.getDescription());

        //get all image of drink
        List<DrinkImage> drinkImageList = ActivityUtils.createDatabaseHelper(getApplicationContext())
                .getAllImageOfDrink(drinks.get_id());

        //create image slider
        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper_dink_image);
        for (int i = 0; i<drinkImageList.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setAdjustViewBounds(true);
            Picasso.with(getApplicationContext())
                    .load(drinkImageList.get(i).getImage_url())
                    .into(imageView);
            viewFlipper.addView(imageView);
        }

        //set animation for view flipper
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_out_right));

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(8000);

        ImageButton btnPrev = (ImageButton) findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });

        ImageButton btnNext = (ImageButton) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });
    }
}
