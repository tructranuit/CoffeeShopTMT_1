package ivc.coffee.shop.tmtruc.com.sqlhelper;

import android.provider.BaseColumns;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class CoffeeShopDatabase {

    public CoffeeShopDatabase() {
    }

    public static class CoffeeShopTable implements BaseColumns {
        public static final String TABLE_NAME = "coffee_shop";
        public static final String COLUMN_NAME_SHOP_NAME = "shop_name";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                 _ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
                 COLUMN_NAME_SHOP_NAME + " TEXT, " +
                 COLUMN_NAME_PHONE_NUMBER + " TEXT, " +
                 COLUMN_NAME_ADDRESS + " TEXT, " +
                 COLUMN_NAME_DESCRIPTION + " TEXT, " +
                 COLUMN_NAME_LATITUDE + " FLOAT, " +
                 COLUMN_NAME_LONGITUDE + " FLOAT ";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXIT " + TABLE_NAME;
    }

    public static class CoffeeShopImageTable implements BaseColumns {
        public static final String TABLE_NAME = "coffee_shop_image";
        public static final String COLUMN_NAME_SHOP_ID = "shop_id";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                 _ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
                 COLUMN_NAME_SHOP_ID + " INTEGER, " +
                 COLUMN_NAME_IMAGE_URL + " TEXT ";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXIT " + TABLE_NAME;
    }

    public static class DrinksTable implements BaseColumns {
        public static final String TABLE_NAME = "drinks";
        public static final String COLUMN_NAME_DRINK_NAME = "drink_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_PRICE = "price";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                 _ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
                 COLUMN_NAME_DRINK_NAME + " TEXT, " +
                 COLUMN_NAME_DESCRIPTION + " TEXT, " +
                 COLUMN_NAME_TYPE + " TEXT, " +
                 COLUMN_NAME_PRICE + " DOUBLE ";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXIT " + TABLE_NAME;
    }

    public static class DrinkImageTable implements BaseColumns {
        public static final String TABLE_NAME = "drink_image_table";
        public static final String COLUMN_NAME_DRINK_ID = "drink_id";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                 _ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
                 COLUMN_NAME_DRINK_ID + " INTEGER, " +
                 COLUMN_NAME_IMAGE_URL + " TEXT ";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXIT " + TABLE_NAME;
    }

    public static class DrinkOrderTable implements BaseColumns {
        public static final String TABLE_NAME = "drink_order";
        public static final String COLUMN_NAME_ORDER_DATE = "order_date";
        public static final String COLUMN_NAME_TOTAL_COST = "total_cost";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                 _ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
                 COLUMN_NAME_ORDER_DATE + " INTEGER, " +
                 COLUMN_NAME_TOTAL_COST + " DOUBLE ";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXIT " + TABLE_NAME;
    }

    public static class OrderDetailTable implements BaseColumns {
        public static final String TABLE_NAME = "order_detail";
        public static final String COLUMN_NAME_ORDER_ID = "order_id";
        public static final String COLUMN_NAME_DRINK_ID = "drink_id";
        public static final String COLUMN_NAME_QUANTITY = "quantity";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                 _ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
                 COLUMN_NAME_ORDER_ID + " INTEGER, " +
                 COLUMN_NAME_DRINK_ID + " INTEGER, " +
                 COLUMN_NAME_QUANTITY + " INTEGER ";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXIT " + TABLE_NAME;
    }

}
