package ivc.coffee.shop.tmtruc.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
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
    }
}
