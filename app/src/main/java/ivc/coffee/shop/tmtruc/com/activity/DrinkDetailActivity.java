package ivc.coffee.shop.tmtruc.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.fragment.OrderFragment;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.model.OrderDetail;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;
import ivc.coffee.shop.tmtruc.com.util.ActivityUtils;
import ivc.coffee.shop.tmtruc.com.util.FormatNumberUtils;

public class DrinkDetailActivity extends AppCompatActivity {

    private TextView tvDrinkName;
    private TextView tvDrinkPrice;
    private TextView tvDrinkDescription;
    private TextView tvQuantity;
    private ViewFlipper viewFlipper;
    private ImageButton btnPrev;
    private ImageButton btnNext;
    private ImageButton btnAdd;
    private ImageButton btnRemove;
    private ImageButton btnAddToCart;


    public static int MAX_QUANTITY = 20;

    int quantity;
    OrderDetail orderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        getControls();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("PACKAGE");
        Drinks drinks = (Drinks) bundle.getSerializable("DATA");

        tvDrinkName.setText(drinks.getDrink_name());
        tvDrinkPrice.setText(FormatNumberUtils.formatNumber(drinks.getPrice()) + "đ");
        tvDrinkDescription.setText(drinks.getDescription());

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        //get all image of drink
        List<DrinkImage> drinkImageList = databaseHelper.getAllImageOfDrink(drinks.get_id());

        //create image slider
        for (int i = 0; i < drinkImageList.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setAdjustViewBounds(true);
            Picasso.with(getApplicationContext())
                    .load(drinkImageList.get(i).getImage_url())
                    .placeholder(R.drawable.img_default_2)
                    .error(R.drawable.img_default_2)
                    .into(imageView);
            viewFlipper.addView(imageView);
        }

        //set animation for viewflipper
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_out_right));

        // set viewflipper auto start
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(8000);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });


        //handle order event

        orderDetail = new OrderDetail();
        orderDetail.setDrink_id(drinks.get_id());
        if (OrderFragment.orderDetailArrayList.contains(orderDetail)) {
            orderDetail = OrderFragment.orderDetailArrayList
                    .get(OrderFragment.orderDetailArrayList.indexOf(orderDetail));
            quantity = orderDetail.getQuantity();
        } else {
            quantity = 0;
        }

        tvQuantity.setText(quantity + "");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity < MAX_QUANTITY) {
                    quantity += 1;
                    tvQuantity.setText(quantity + "");
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    quantity -= 1;
                    tvQuantity.setText(quantity + "");
                }
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity != 0) {
                    if (OrderFragment.orderDetailArrayList.contains(orderDetail)) {
                        int index = OrderFragment.orderDetailArrayList.indexOf(orderDetail);
                        // if drink is exists in cart
                        // update quantity
                         OrderFragment.orderDetailArrayList.get(index).setQuantity(quantity);
                    } else {
                        // if drink is not exists in cart
                        // add new detail
                        orderDetail.setQuantity(quantity);
                        OrderFragment.orderDetailArrayList.add(orderDetail);
                    }
                    Toast.makeText(getApplicationContext(), "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn số lượng trước khi thêm vào giở hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getControls(){
        tvDrinkName = (TextView) findViewById(R.id.tv_drink_name);
        tvDrinkPrice = (TextView) findViewById(R.id.tv_drink_price);
        tvDrinkDescription = (TextView) findViewById(R.id.tv_drink_description);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper_dink_image);
        btnPrev = (ImageButton) findViewById(R.id.btn_prev);
        btnNext = (ImageButton) findViewById(R.id.btn_next);
        btnRemove = (ImageButton) findViewById(R.id.btn_remove);
        btnAdd = (ImageButton) findViewById(R.id.btn_add);
        tvQuantity = (TextView) findViewById(R.id.tv_quantity);
        btnAddToCart = (ImageButton) findViewById(R.id.btn_add_to_cart);
    }
}