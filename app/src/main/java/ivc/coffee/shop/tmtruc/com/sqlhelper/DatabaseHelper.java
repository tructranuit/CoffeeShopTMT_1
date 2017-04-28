package ivc.coffee.shop.tmtruc.com.sqlhelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create coffee shop table
        db.execSQL(CoffeeShopDatabase.CoffeeShopTable.SQL_CREATE_ENTRIES);

        // create coffee shop image table
        db.execSQL(CoffeeShopDatabase.CoffeeShopImageTable.SQL_CREATE_ENTRIES);

        // create drinks table
        db.execSQL(CoffeeShopDatabase.DrinksTable.SQL_CREATE_ENTRIES);

        // create drink image table
        db.execSQL(CoffeeShopDatabase.DrinkImageTable.SQL_CREATE_ENTRIES);

        // create drink order table
        db.execSQL(CoffeeShopDatabase.DrinkOrderTable.SQL_CREATE_ENTRIES);

        // create order detail table
        db.execSQL(CoffeeShopDatabase.OrderDetailTable.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop coffee shop table
        db.execSQL(CoffeeShopDatabase.CoffeeShopTable.SQL_DELETE_ENTRIES);

        //drop coffee shop image table
        db.execSQL(CoffeeShopDatabase.CoffeeShopImageTable.SQL_DELETE_ENTRIES);

        //drop drinks table
        db.execSQL(CoffeeShopDatabase.DrinksTable.SQL_DELETE_ENTRIES);

        //drop drink image table
        db.execSQL(CoffeeShopDatabase.DrinkImageTable.SQL_DELETE_ENTRIES);

        //drop drink order table
        db.execSQL(CoffeeShopDatabase.DrinkOrderTable.SQL_DELETE_ENTRIES);

        //drop order detail table
        db.execSQL(CoffeeShopDatabase.OrderDetailTable.SQL_DELETE_ENTRIES);

        //recreate coffee shop database
        onCreate(db);
    }

    //---------------------------- coffee shop table methods -------------------------------------//


}
