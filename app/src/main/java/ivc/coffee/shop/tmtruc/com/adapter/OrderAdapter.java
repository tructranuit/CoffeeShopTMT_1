package ivc.coffee.shop.tmtruc.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.DrinkOrder;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.model.OrderDetail;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;
import ivc.coffee.shop.tmtruc.com.util.FormatNumberUtils;

import static ivc.coffee.shop.tmtruc.com.activity.DrinkDetailActivity.MAX_QUANTITY;

/**
 * Created by tmtruc on 04/05/2017.
 */

public class OrderAdapter extends ArrayAdapter<OrderDetail> {
    ArrayList<OrderDetail> orderDetailArrayList;

    public OrderAdapter(Context context, int resource, List<OrderDetail> orderDetailList) {
        super(context, resource, orderDetailList);
        orderDetailArrayList = new ArrayList<>();
        orderDetailArrayList.addAll(orderDetailList);
    }

    public class ViewHolder {
        ImageView img_drink_image;
        TextView tv_drink_name;
        TextView tv_price;
        TextView tv_quantity;
        ImageButton btn_add;
        ImageButton btn_remove;

        ViewHolder(View view) {
            img_drink_image = (ImageView) view.findViewById(R.id.img_drink_image);
            tv_drink_name = (TextView) view.findViewById(R.id.tv_drink_name);
            tv_price = (TextView) view.findViewById(R.id.tv_drink_price);
            tv_quantity = (TextView) view.findViewById(R.id.tv_quantity);
            btn_add = (ImageButton) view.findViewById(R.id.btn_add);
            btn_remove = (ImageButton) view.findViewById(R.id.btn_remove);
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_item_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final OrderDetail orderDetail = orderDetailArrayList.get(position);

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        Drinks drinks = databaseHelper.getDrink(orderDetail.getDrink_id());

        viewHolder.tv_drink_name.setText(drinks.getDrink_name());
        viewHolder.tv_price.setText(FormatNumberUtils.formatNumber(drinks.getPrice()) + "đ");
        viewHolder.tv_quantity.setText(orderDetail.getQuantity() + "");

        List<DrinkImage> drinkImageList = databaseHelper.getAllImageOfDrink(drinks.get_id());

        Picasso.with(getContext())
                .load(drinkImageList.get(0).getImage_url())
                .error(R.drawable.img_default_2)
                .placeholder(R.drawable.img_default_2)
                .into(viewHolder.img_drink_image);

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDetail.getQuantity() < MAX_QUANTITY) {
                    orderDetail.setQuantity(orderDetail.getQuantity() + 1);
                    finalViewHolder.tv_quantity.setText(orderDetail.getQuantity() + "");
                }
            }
        });

        viewHolder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDetail.getQuantity() > 1) {
                    orderDetail.setQuantity(orderDetail.getQuantity() - 1);
                    finalViewHolder.tv_quantity.setText(orderDetail.getQuantity() + "");
                }
            }
        });
        return view;
    }
}
