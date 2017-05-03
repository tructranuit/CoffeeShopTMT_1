package ivc.coffee.shop.tmtruc.com.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.sqlhelper.DatabaseHelper;

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

    //create database helper
    public static DatabaseHelper createDatabaseHelper(Context context){
        return new DatabaseHelper(context);
    }
}
