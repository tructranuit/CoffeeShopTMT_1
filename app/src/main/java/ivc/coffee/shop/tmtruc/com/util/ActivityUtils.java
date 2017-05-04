package ivc.coffee.shop.tmtruc.com.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;

import static ivc.coffee.shop.tmtruc.com.sqlhelper.CoffeeShopDatabase.DATABASE_NAME;

/**
 * Created by tmt on 02/05/2017.
 */

public class ActivityUtils {

    //load fragment to activity
    public static void loadFragmentToActivity(android.support.v4.app.FragmentManager fragmentManager,
                                              android.support.v4.app.Fragment fragment, int fragment_id) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(fragment_id, fragment);
        fragmentTransaction.commit();
    }

    //get datetime
    public static String getDatetime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
