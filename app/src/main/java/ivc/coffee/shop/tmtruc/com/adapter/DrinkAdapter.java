package ivc.coffee.shop.tmtruc.com.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;
import ivc.coffee.shop.tmtruc.com.util.ActivityUtils;
import ivc.coffee.shop.tmtruc.com.util.FormatNumberUtils;

/**
 * Created by tmt on 02/05/2017.
 */

public class DrinkAdapter extends ArrayAdapter<Drinks> {

    ArrayList<Drinks> drinksArrayList;
    Context context;

    public DrinkAdapter(Context context, int resource, List<Drinks> drinksList) {
        super(context, resource, drinksList);
        drinksArrayList = new ArrayList<>();
        drinksArrayList.addAll(drinksList);
        this.context = context;
    }

    public class ViewHolder {
        ImageView img_drink_image;
        TextView tv_drink_name;
        TextView tv_price;

        ViewHolder(View view) {
            img_drink_image = (ImageView) view.findViewById(R.id.img_drink_image);
            tv_drink_name = (TextView) view.findViewById(R.id.tv_drink_name);
            tv_price = (TextView) view.findViewById(R.id.tv_drink_price);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder = null;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_item_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Drinks drinks = drinksArrayList.get(position);

        viewHolder.tv_drink_name.setText(drinks.getDrink_name().toString());
        viewHolder.tv_price.setText(FormatNumberUtils.formatNumber(drinks.getPrice()) + "đ");

        DatabaseHelper databaseHelper = ActivityUtils.createDatabaseHelper(context);

        //get image of drink
        List<DrinkImage> drinkImageList = databaseHelper.getAllImageOfDrink(drinks.get_id());

        Picasso.with(context)
                .load(drinkImageList.get(0).getImage_url())
                .error(R.drawable.img_default)
                .into(viewHolder.img_drink_image);

        return view;
    }
}
