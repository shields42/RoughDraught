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

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "beerjournal.db";
    private static final String TABLE_BEERS = "Beers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BREWERY = "brewery";
    private static final String COLUMN_STYLE = "style";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_DESCRIPTION = "description";

    public BeerDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public BeerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_BEERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_NAME  + " TEXT " +
                COLUMN_STYLE + " TEXT " +
                COLUMN_BREWERY  + " TEXT " +
                COLUMN_RATING + " NUMERIC " +
                COLUMN_DESCRIPTION  + " TEXT "
                + ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_BEERS;
        db.execSQL(query);
        onCreate(db);
    }

    public void addBeer(Beer entry) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        values.put(COLUMN_NAME, entry.getName());
        values.put(COLUMN_STYLE, entry.getStyle());
        values.put(COLUMN_BREWERY, entry.getBrewery());
        values.put(COLUMN_RATING, entry.getRating());
        values.put(COLUMN_DESCRIPTION, entry.getDescription());
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
    public String selectBeerID(String beerID) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + " = " + beerID;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerID") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerID"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }

    public String selectBeerLocation(String beerLocation) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + " = " + beerLocation;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerLocation") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerLocation"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }

    public String selectBeerRating(Long beerRating) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + " = " + beerRating;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerRating") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerRating"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }

    public String selectBeerType(String beerType) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + " = " + beerType;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerType") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerType"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }

    public String selectBeerStyle(String beerStyle) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE " + COLUMN_NAME + " = " + beerStyle;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("beerStyle") )!= null) {
                dbString += c.getString(c.getColumnIndex("beerID"));
            }
        }
        c.close();

        db.close();
        return dbString;
    }


}
