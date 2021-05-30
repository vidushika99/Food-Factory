package com.example.foodfactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class DataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="FoodFactory.db";
    private static final String TABLE_NAME="Register_table";
   // public static final String ID = "id";
    public static final String Name_COL = "NAME";
    public static final String Weight_COL = "WEIGHT";
    public static final String Price_COL= "PRICE";
    public static final String Description_COL = "DESCRIPTION";
    public static final String inputString_Col = "Availability";

    public DataBase (Context context){
        super(context,DATABASE_NAME,null,1);
       // SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ TABLE_NAME+"( NAME TEXT PRIMARY KEY,WEIGHT INTEGER,PRICE INTEGER,DESCRIPTION TEXT, AVAILABILITY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME );
           onCreate(db);
    }
    public boolean insertData(String name,Integer weight,Integer price,String description){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name_COL,name);
        contentValues.put(Weight_COL,weight);
        contentValues.put(Price_COL,price);
        contentValues.put(Description_COL,description);


        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;

    }
    public Cursor displayFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);

        Cursor result=db.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME+ " ORDER BY "+DataBase.Name_COL+" ASC"
                , new String[] {} );
        System.out.println(Arrays.toString(result.getColumnNames()));
        return result;
    }


    public Cursor displayAvailability(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from  "+TABLE_NAME+" WHERE "+inputString_Col+"= 'true'",null);

       // Cursor result=db.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME+ " ORDER BY "+DataBase.inputString_Col+" ASC", new String[] {} );
        return result;
    }


    public void addAvailabilityList(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+inputString_Col+" = 'true' WHERE NAME = '"+NAME+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+inputString_Col+" = 'true' WHERE NAME = '"+NAME+"'");


    }

    public boolean updateEditData(String name,Integer weight,Integer price,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor result = db.rawQuery("select * from  "+TABLE_NAME+" WHERE "+ID+"= 'id' ",null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name_COL,name);
        contentValues.put(Weight_COL,weight);
        contentValues.put(Price_COL,price);
        contentValues.put(Description_COL,description);
        db.update(TABLE_NAME, contentValues, "NAME = ?",new String[] { name });
        //db.update(TABLE_NAME, contentValues, "NAME = ?"+name,null);
        return true;


    }
    public Cursor searchFood(String keyword){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + inputString_Col + " where " + Name_COL + " like ?", new String[] { "%" + keyword + "%" });
        return cursor;

    }


}
