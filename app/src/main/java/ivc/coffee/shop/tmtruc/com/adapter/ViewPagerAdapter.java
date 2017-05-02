package ivc.coffee.shop.tmtruc.com.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ivc.coffee.shop.tmtruc.com.R;

/**
 * Created by tmtruc on 26/04/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Integer> imagerList;

    public ViewPagerAdapter(Context context, ArrayList<Integer> imagerList) {
        this.context = context;
        this.imagerList = imagerList;
    }

    @Override
    public int getCount() {
        return imagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_image_item_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(context).load(imagerList.get(position)).into(imageView);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
