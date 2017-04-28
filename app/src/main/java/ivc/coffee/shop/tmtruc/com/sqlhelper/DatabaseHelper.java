package ivc.coffee.shop.tmtruc.com.sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.model.CoffeeShop;
import ivc.coffee.shop.tmtruc.com.model.CoffeeShopImage;
import ivc.coffee.shop.tmtruc.com.model.Drinks;

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

    //---------------------------- COFFEE SHOP TABLE METHODS -------------------------------------//

    /**
     * insert new coffee shop
     */
    public void insertCoffeeShop(CoffeeShop coffeeShop) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable._ID, coffeeShop.get_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_SHOP_NAME, coffeeShop.getShop_name());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_PHONE_NUMBER, coffeeShop.getPhone_number());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_ADDRESS, coffeeShop.getAddress());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_DESCRIPTION, coffeeShop.getDescription());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LATITUDE, coffeeShop.getLatitude());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LONGITUDE, coffeeShop.getLongitude());

        // insert row
        db.insert(CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * get single coffee shop
     **/
    public CoffeeShop getCoffeeShop(int coffee_shop_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME +
                " WHERE " + CoffeeShopDatabase.CoffeeShopTable._ID + " = " + coffee_shop_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        CoffeeShop coffeeShop = new CoffeeShop(
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable._ID)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_PHONE_NUMBER)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_DESCRIPTION)),
                cursor.getFloat(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LATITUDE)),
                cursor.getFloat(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LONGITUDE))
        );
        return coffeeShop;
    }

    /**
     * get all coffee shop
     */
    public List<CoffeeShop> getAllCoffeeShop() {
        List<CoffeeShop> coffeeShopList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CoffeeShop coffeeShop = new CoffeeShop(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable._ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_SHOP_NAME)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_DESCRIPTION)),
                        cursor.getFloat(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LATITUDE)),
                        cursor.getFloat(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LONGITUDE))
                );
                coffeeShopList.add(coffeeShop);
            } while (cursor.moveToNext());
        }
        return coffeeShopList;
    }

    /**
     * getting coffee shop count
     **/
    public int getCoffeeShopCount(){
        String sqlCountQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlCountQuery, null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }

    /**
     * update cofee shop
     **/
    public int updateCoffeeShop(CoffeeShop coffeeShop){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_SHOP_NAME, coffeeShop.getShop_name());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_PHONE_NUMBER, coffeeShop.getPhone_number());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_ADDRESS, coffeeShop.getAddress());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_DESCRIPTION, coffeeShop.getDescription());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LATITUDE, coffeeShop.getLatitude());
        contentValues.put(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LONGITUDE, coffeeShop.getLongitude());

        return db.update(CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.CoffeeShopTable._ID + " = ? ", new String[]{String.valueOf(coffeeShop.get_id())});
    }

    /**
     * delete coffe shop
     */
    public void deleteCoffeeShop(CoffeeShop coffeeShop){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME, CoffeeShopDatabase.CoffeeShopTable._ID + " = ? ",
                new String[]{String.valueOf(coffeeShop.get_id())});
    }

    //-------------------------- COFFEE SHOP IMAGE TABLE METHOD ----------------------------------//
    /**
     * insert new coffee shop image
     */
    public void insertCoffeeShopImage(CoffeeShopImage coffeeShopImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable._ID, coffeeShopImage.get_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID, coffeeShopImage.getShop_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL, coffeeShopImage.getImage_url());

        // insert row
        db.insert(CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * get single coffee shop image
     **/
    public CoffeeShopImage getCoffeeShopImage(int coffee_shop_image_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME +
                " WHERE " + CoffeeShopDatabase.CoffeeShopImageTable._ID + " = " + coffee_shop_image_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        CoffeeShopImage coffeeShopImage = new CoffeeShopImage(
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable._ID)),
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL))
        );
        return coffeeShopImage;
    }

    /**
     * get all coffee shop image
     */
    public List<CoffeeShopImage> getAllCoffeeShopImage() {
        List<CoffeeShopImage> coffeeShopImageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CoffeeShopImage coffeeShopImage = new CoffeeShopImage(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable._ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL))

                );
                coffeeShopImageList.add(coffeeShopImage);
            } while (cursor.moveToNext());
        }
        return coffeeShopImageList;
    }

    /*
    * getting all image of shop
    * */
    public List<CoffeeShopImage> getAllCoffeShopImageOfShop(){
        List<CoffeeShopImage> coffeeShopImageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME + " cs, "
                + CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME + " csi WHERE cs."
                + CoffeeShopDatabase.CoffeeShopTable._ID + " = csi."
                + CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CoffeeShopImage coffeeShopImage = new CoffeeShopImage(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable._ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL))

                );
                coffeeShopImageList.add(coffeeShopImage);
            } while (cursor.moveToNext());
        }
        return coffeeShopImageList;
    }

    /**
     * getting coffee shop image count
     **/
    public int getCoffeeShopImageCount(){
        String sqlCountQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlCountQuery, null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }

    /**
     * update cofee shop image
     **/
    public int updateCoffeeShopImage(CoffeeShopImage coffeeShopImage){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID, coffeeShopImage.getShop_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL, coffeeShopImage.getImage_url());

        return db.update(CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.CoffeeShopImageTable._ID + " = ? ", new String[]{String.valueOf(coffeeShopImage.get_id())});
    }

    /**
     * delete coffe shop
     */
    public void deleteCoffeeShopImage(CoffeeShopImage coffeeShopImage){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME, CoffeeShopDatabase.CoffeeShopImageTable._ID + " = ? ",
                new String[]{String.valueOf(coffeeShopImage.get_id())});
    }


    //---------------------------- COFFEE DRINKS TABLE METHODS -------------------------------------//

    /**
     * insert new drink
     */
    public void insertDrink(Drinks drinks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.DrinksTable._ID, drinks.get_id());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DRINK_NAME, drinks.getDrink_name());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DESCRIPTION, drinks.getDescription());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_TYPE, drinks.getType());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_PRICE, drinks.getPrice());

        // insert row
        db.insert(CoffeeShopDatabase.DrinksTable.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * get single drink
     **/
    public Drinks getDrink(int drink_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinksTable.TABLE_NAME +
                " WHERE " + CoffeeShopDatabase.DrinksTable._ID + " = " + drink_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Drinks drinks = new Drinks(
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable._ID)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DRINK_NAME)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_TYPE)),
                cursor.getDouble(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_PRICE))
        );
        return drinks;
    }

    /**
     * get all drink
     */
    public List<Drinks> getAllDrink() {
        List<Drinks> drinksList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinksTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Drinks drinks = new Drinks(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable._ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DRINK_NAME)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_TYPE)),
                        cursor.getDouble(cursor.getColumnIndex(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_PRICE))
                );
                drinksList.add(drinks);
            } while (cursor.moveToNext());
        }
        return drinksList;
    }

    /**
     * getting drinks count
     **/
    public int getDrinksCount(){
        String sqlCountQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinksTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlCountQuery, null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }

    /**
     * update drink
     **/
    public int updateDrink(Drinks drinks){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DRINK_NAME, drinks.getDrink_name());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DESCRIPTION, drinks.getDescription());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_TYPE, drinks.getType());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_PRICE, drinks.getPrice());

        return db.update(CoffeeShopDatabase.DrinksTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.DrinksTable._ID + " = ? ", new String[]{String.valueOf(drinks.get_id())});
    }

    /**
     * delete drink
     */
    public void deleteDrink(Drinks drinks){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.DrinksTable.TABLE_NAME, CoffeeShopDatabase.DrinksTable._ID + " = ? ",
                new String[]{String.valueOf(drinks.get_id())});
    }

}
