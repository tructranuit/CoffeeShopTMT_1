package ivc.coffee.shop.tmtruc.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;
import ivc.coffee.shop.tmtruc.com.util.FormatNumberUtils;


public class DrinkAdapter extends ArrayAdapter<Drinks> {

    private ArrayList<Drinks> drinksArrayList;

    public DrinkAdapter(Context context, int resource, List<Drinks> drinksList) {
        super(context, resource, drinksList);
        drinksArrayList = new ArrayList<>();
        drinksArrayList.addAll(drinksList);
    }

    private class ViewHolder {
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

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_item_layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Drinks drinks = drinksArrayList.get(position);

        viewHolder.tv_drink_name.setText(drinks.getDrink_name());
        viewHolder.tv_price.setText(FormatNumberUtils.formatNumber(drinks.getPrice()) + "đ");

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        //get image of drink
        List<DrinkImage> drinkImageList = databaseHelper.getAllImageOfDrink(drinks.get_id());

        Picasso.with(getContext())
                .load(drinkImageList.get(0).getImage_url())
                .error(R.drawable.img_default_2)
                .placeholder(R.drawable.img_default_2)
                .into(viewHolder.img_drink_image);

        return view;
    }
}
