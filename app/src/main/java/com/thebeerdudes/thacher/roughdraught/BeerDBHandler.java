package com.thebeerdudes.thacher.roughdraught;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TheSh on 11/3/2017.
 */

public class BeerDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "beerjournal.db";
    private static final String TABLE_BEERS = "Beers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_BREWERY = "brewery";
    private static final String COLUMN_STARS = "stars";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_DATE = "date";

    public BeerDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_BEERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_NAME  + " TEXT " +
                COLUMN_TYPE + " TEXT " +
                COLUMN_BREWERY  + " TEXT " +
                COLUMN_STARS  + " INTEGER " +
                COLUMN_RATING + " NUMERIC " +
                COLUMN_DESCRIPTION  + " TEXT " +
                COLUMN_LOCATION  + " TEXT " +
                COLUMN_DATE + " TEXT "
                + ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP_TABLE_IF_EXISTS " + TABLE_BEERS);
        onCreate(db);
    }

    public void addBeer(Beer entry) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        values.put(COLUMN_NAME, entry.getName());
        values.put(COLUMN_TYPE, entry.getType());
        values.put(COLUMN_BREWERY, entry.getBrewery());
        values.put(COLUMN_STARS, entry.getStars());
        values.put(COLUMN_RATING, entry.getRating());
        values.put(COLUMN_DESCRIPTION, entry.getDescription());
        values.put(COLUMN_LOCATION, entry.getLocation());
        values.put(COLUMN_DATE, entry.get_date());
        db.insert(TABLE_BEERS, null, values);
    }

    public void deleteBeer(String beerName) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + "=\"" + beerName + "\";");
    }

    public String beerTabletoString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerName") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerName"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }

    public String selectBeerName(String beerName) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + " = " + beerName;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerName") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerName"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }
}
