package ivc.coffee.shop.tmtruc.com.sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ivc.coffee.shop.tmtruc.com.model.CoffeeShop;
import ivc.coffee.shop.tmtruc.com.model.CoffeeShopImage;
import ivc.coffee.shop.tmtruc.com.model.DrinkImage;
import ivc.coffee.shop.tmtruc.com.model.DrinkOrder;
import ivc.coffee.shop.tmtruc.com.model.Drinks;
import ivc.coffee.shop.tmtruc.com.model.OrderDetail;

import static ivc.coffee.shop.tmtruc.com.sqlhelper.CoffeeShopDatabase.DATABASE_NAME;
import static ivc.coffee.shop.tmtruc.com.sqlhelper.CoffeeShopDatabase.DATABASE_VERSION;

/**
 * Created by tmtruc on 27/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    public long insertCoffeeShop(CoffeeShop coffeeShop) {
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
        long index = db.insert(CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME, null, contentValues);
        db.close();
        return index;
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
                cursor.getDouble(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_LONGITUDE))
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
    public int getCoffeeShopCount() {
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
    public int updateCoffeeShop(CoffeeShop coffeeShop) {
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
    public void deleteCoffeeShop(CoffeeShop coffeeShop) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME, CoffeeShopDatabase.CoffeeShopTable._ID + " = ? ",
                new String[]{String.valueOf(coffeeShop.get_id())});
    }

    //-------------------------- COFFEE SHOP IMAGE TABLE METHOD ----------------------------------//

    /**
     * insert new coffee shop image
     */
    public long insertCoffeeShopImage(CoffeeShopImage coffeeShopImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable._ID, coffeeShopImage.get_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID, coffeeShopImage.getShop_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL, coffeeShopImage.getImage_url());

        // insert row
        long index = db.insert(CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME, null, contentValues);
        db.close();
        return index;
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

    /**
     * getting all image of shop
     **/
    public List<CoffeeShopImage> getAllCoffeShopImageOfShop(String coffee_shop_name) {
        List<CoffeeShopImage> coffeeShopImageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.CoffeeShopTable.TABLE_NAME + " cs, "
                + CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME + " csi WHERE cs."
                + CoffeeShopDatabase.CoffeeShopTable._ID + " = csi."
                + CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID
                + " AND cs." + CoffeeShopDatabase.CoffeeShopTable.COLUMN_NAME_SHOP_NAME + " = '" + coffee_shop_name +"'";
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
    public int getCoffeeShopImageCount() {
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
    public int updateCoffeeShopImage(CoffeeShopImage coffeeShopImage) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_SHOP_ID, coffeeShopImage.getShop_id());
        contentValues.put(CoffeeShopDatabase.CoffeeShopImageTable.COLUMN_NAME_IMAGE_URL, coffeeShopImage.getImage_url());

        return db.update(CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.CoffeeShopImageTable._ID + " = ? ", new String[]{String.valueOf(coffeeShopImage.get_id())});
    }

    /**
     * delete coffe shop image
     */
    public void deleteCoffeeShopImage(CoffeeShopImage coffeeShopImage) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.CoffeeShopImageTable.TABLE_NAME, CoffeeShopDatabase.CoffeeShopImageTable._ID + " = ? ",
                new String[]{String.valueOf(coffeeShopImage.get_id())});
    }


    //---------------------------- DRINKS TABLE METHODS -------------------------------------//

    /**
     * insert new drink
     */
    public long insertDrink(Drinks drinks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.DrinksTable._ID, drinks.get_id());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DRINK_NAME, drinks.getDrink_name());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_DESCRIPTION, drinks.getDescription());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_TYPE, drinks.getType());
        contentValues.put(CoffeeShopDatabase.DrinksTable.COLUMN_NAME_PRICE, drinks.getPrice());

        // insert row
        long index = db.insert(CoffeeShopDatabase.DrinksTable.TABLE_NAME, null, contentValues);
        db.close();
        return index;
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
     * get drink in order detail
     * */
    public Drinks getDrinkInOrderDetail(int drink_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinksTable.TABLE_NAME + " dr, "
                + CoffeeShopDatabase.OrderDetailTable.TABLE_NAME + " od WHERE dr."
                + CoffeeShopDatabase.DrinksTable._ID + " = " + drink_id
                + " AND dr." + CoffeeShopDatabase.DrinksTable._ID + " = od." + CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_DRINK_ID;
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
     * getting drinks count
     **/
    public int getDrinksCount() {
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
    public int updateDrink(Drinks drinks) {
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
    public void deleteDrink(Drinks drinks) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.DrinksTable.TABLE_NAME, CoffeeShopDatabase.DrinksTable._ID + " = ? ",
                new String[]{String.valueOf(drinks.get_id())});
    }

    //-------------------------- DRINK IMAGE TABLE METHOD ----------------------------------//

    /**
     * insert new drink image
     */
    public long insertDrinkImage(DrinkImage drinkImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.DrinkImageTable._ID, drinkImage.get_id());
        contentValues.put(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_DRINK_ID, drinkImage.getDrink_id());
        contentValues.put(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_IMAGE_URL, drinkImage.getImage_url());

        // insert row
        long index = db.insert(CoffeeShopDatabase.DrinkImageTable.TABLE_NAME, null, contentValues);
        db.close();
        return index;
    }

    /**
     * get single drink image
     **/
    public DrinkImage getDrinkImage(int drink_image_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkImageTable.TABLE_NAME +
                " WHERE " + CoffeeShopDatabase.DrinkImageTable._ID + " = " + drink_image_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        DrinkImage drinkImage = new DrinkImage(
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable._ID)),
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_DRINK_ID)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_IMAGE_URL))
        );
        return drinkImage;
    }

    /**
     * get all drink image
     */
    public List<DrinkImage> getAllDrinkImage() {
        List<DrinkImage> drinkImageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkImageTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DrinkImage drinkImage = new DrinkImage(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable._ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_DRINK_ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_IMAGE_URL))

                );
                drinkImageList.add(drinkImage);
            } while (cursor.moveToNext());
        }
        return drinkImageList;
    }

    /**
     * getting all image of drink
     **/
    public List<DrinkImage> getAllImageOfDrink(int drink_id) {
        List<DrinkImage> drinkImageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkImageTable.TABLE_NAME
                + " WHERE " + CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_DRINK_ID + " = " + drink_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DrinkImage drinkImage = new DrinkImage(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable._ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_DRINK_ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_IMAGE_URL))

                );
                drinkImageList.add(drinkImage);
            } while (cursor.moveToNext());
        }
        return drinkImageList;
    }

    /**
     * getting drink image count
     **/
    public int getDrinkImageCount() {
        String sqlCountQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkImageTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlCountQuery, null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }

    /**
     * update drink image
     **/
    public int updateDrinkImage(DrinkImage drinkImage) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_DRINK_ID, drinkImage.getDrink_id());
        contentValues.put(CoffeeShopDatabase.DrinkImageTable.COLUMN_NAME_IMAGE_URL, drinkImage.getImage_url());

        return db.update(CoffeeShopDatabase.DrinkImageTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.DrinkImageTable._ID + " = ? ", new String[]{String.valueOf(drinkImage.get_id())});
    }

    /**
     * delete drink image
     */
    public void deleteDrinkImage(DrinkImage drinkImage) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.DrinkImageTable.TABLE_NAME, CoffeeShopDatabase.DrinkImageTable._ID + " = ? ",
                new String[]{String.valueOf(drinkImage.get_id())});
    }


    //---------------------------- DRINK ORDER TABLE METHODS -------------------------------------//

    /**
     * insert new drink order
     */
    public long insertDrinkOrder(DrinkOrder drinkOrder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.putNull(CoffeeShopDatabase.DrinkOrderTable._ID);
        contentValues.put(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_ORDER_DATE, drinkOrder.getOrder_date());
        contentValues.put(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_TOTAL_COST, drinkOrder.getTotal_cost());

        // insert row
        long index = db.insert(CoffeeShopDatabase.DrinkOrderTable.TABLE_NAME, null, contentValues);
        db.close();
        return index;
    }

    /**
     * get single drink order
     **/
    public DrinkOrder getDrinkOrder(int drink_order_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkOrderTable.TABLE_NAME +
                " WHERE " + CoffeeShopDatabase.DrinkOrderTable._ID + " = " + drink_order_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        DrinkOrder drinkOrder = new DrinkOrder(
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkOrderTable._ID)),
                cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_ORDER_DATE)),
                cursor.getDouble(cursor.getColumnIndex(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_TOTAL_COST))
        );
        return drinkOrder;
    }

    /**
     * get all drink order
     */
    public List<DrinkOrder> getAllDrinkOrder() {
        List<DrinkOrder> drinkOrderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkOrderTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DrinkOrder drinkOrder = new DrinkOrder(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.DrinkOrderTable._ID)),
                        cursor.getString(cursor.getColumnIndex(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_ORDER_DATE)),
                        cursor.getDouble(cursor.getColumnIndex(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_TOTAL_COST))
                );
                drinkOrderList.add(drinkOrder);
            } while (cursor.moveToNext());
        }
        return drinkOrderList;
    }

    /**
     * getting drink order count
     **/
    public int getDrinkOrderCount() {
        String sqlCountQuery = "SELECT * FROM " + CoffeeShopDatabase.DrinkOrderTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlCountQuery, null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }

    /**
     * update drink order
     **/
    public int updateDrinkOrder(DrinkOrder drinkOrder) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_ORDER_DATE, drinkOrder.getOrder_date());
        contentValues.put(CoffeeShopDatabase.DrinkOrderTable.COLUMN_NAME_TOTAL_COST, drinkOrder.getTotal_cost());

        return db.update(CoffeeShopDatabase.DrinkOrderTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.DrinkOrderTable._ID + " = ? ", new String[]{String.valueOf(drinkOrder.get_id())});
    }

    /**
     * delete drink order`
     */
    public void deleteDrinkOrder(DrinkOrder drinkOrder) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.DrinkOrderTable.TABLE_NAME, CoffeeShopDatabase.DrinkOrderTable._ID + " = ? ",
                new String[]{String.valueOf(drinkOrder.get_id())});
    }


    //---------------------------- ORDER DETAIL TABLE METHODS -------------------------------------//

    /**
     * insert new order detail
     */
    public long insertOrderDetail(OrderDetail orderDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.putNull(CoffeeShopDatabase.OrderDetailTable._ID);
        contentValues.put(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_ORDER_ID, orderDetail.getOrder_id());
        contentValues.put(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_DRINK_ID, orderDetail.getDrink_id());
        contentValues.put(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_QUANTITY, orderDetail.getQuantity());

        // insert row
        long index = db.insert(CoffeeShopDatabase.OrderDetailTable.TABLE_NAME, null, contentValues);
        db.close();
        return index;
    }

    /**
     * get single order detail
     **/
    public OrderDetail getOrderDetail(int order_detail_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.OrderDetailTable.TABLE_NAME +
                " WHERE " + CoffeeShopDatabase.OrderDetailTable._ID + " = " + order_detail_id;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        OrderDetail orderDetail = new OrderDetail(
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable._ID)),
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_ORDER_ID)),
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_DRINK_ID)),
                cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_QUANTITY))
        );
        return orderDetail;
    }

    /**
     * get all order detail
     */
    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectQuery = "SELECT * FROM " + CoffeeShopDatabase.OrderDetailTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlSelectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                OrderDetail orderDetail = new OrderDetail(
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable._ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_ORDER_ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_DRINK_ID)),
                        cursor.getInt(cursor.getColumnIndex(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_QUANTITY))
                );
                orderDetailList.add(orderDetail);
            } while (cursor.moveToNext());
        }
        return orderDetailList;
    }

    /**
     * getting order detail count
     **/
    public int getOrderDetailCount() {
        String sqlCountQuery = "SELECT * FROM " + CoffeeShopDatabase.OrderDetailTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlCountQuery, null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }

    /**
     * update order detail
     **/
    public int updateOrderDetail(OrderDetail orderDetail) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_ORDER_ID, orderDetail.getOrder_id());
        contentValues.put(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_DRINK_ID, orderDetail.getDrink_id());
        contentValues.put(CoffeeShopDatabase.OrderDetailTable.COLUMN_NAME_QUANTITY, orderDetail.getQuantity());

        return db.update(CoffeeShopDatabase.OrderDetailTable.TABLE_NAME, contentValues,
                CoffeeShopDatabase.OrderDetailTable._ID + " = ? ", new String[]{String.valueOf(orderDetail.get_id())});
    }

    /**
     * delete Order detail
     */
    public void deleteOrderDetail(OrderDetail orderDetail) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(CoffeeShopDatabase.OrderDetailTable.TABLE_NAME, CoffeeShopDatabase.OrderDetailTable._ID + " = ? ",
                new String[]{String.valueOf(orderDetail.get_id())});
    }
}
