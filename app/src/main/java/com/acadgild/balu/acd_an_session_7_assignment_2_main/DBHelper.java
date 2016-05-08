package com.acadgild.balu.acd_an_session_7_assignment_2_main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by BALU on 4/19/2016.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DB_PRODUCTS";

    private static final String TABLE_PRODUCTS = "PRODUCTS";
    private static final String COL_ID = "PRODUCT_ID";
    private static final String COL_NAME = "PRODUCT_NAME";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String str_create_table = "CREATE TABLE " + TABLE_PRODUCTS + " ( "
                                 + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                 + COL_NAME + " TEXT )";
        db.execSQL(str_create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_PRODUCTS);
        this.onCreate(db);
    }

    public void add_new_product(String new_product_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, new_product_name);

        db.insert(TABLE_PRODUCTS, null, values);

        db.close();
    }

    public ArrayList<String> get_all_products()
    {
        ArrayList<String> arrayList = new ArrayList<>();

        String str_query = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(str_query, null);

        if (cursor.moveToFirst())
        {
            do {
                arrayList.add(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            }while (cursor.moveToNext());

        }
        cursor.close();
        return arrayList;
    }

    public void clear_table()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_PRODUCTS);

        db.close();

    }
}
