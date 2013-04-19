package com.example.androidtablayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SqlHelperClass extends SQLiteOpenHelper{

	/* This class extends SQLOpenHelper
	 * It creates Database and set static constants
	 * that are used in the other classes
	 */ 
	
	//set database as private constants
	private static final String DATABASE_NAME = "ProgramResources.db";
	private static final int DATABASE_VERSION = 1;
	
	//public constants & database table
	public static final String TABLE_NAME = "String";
	
	//columns
	public static final String STRING_ID = "stringid";
	public static final String STRING_INPUT = "stringinput";
	
	//required constructor
	//takes the contest which is usually the current
	//activity, it also passes the database name and
	//version to the super class
	
	public SqlHelperClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//here we create the table
		String sql_String = "CREATE TABLE " + TABLE_NAME 
		+ "(" + STRING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ STRING_INPUT + " TEXT NOT NULL); " ;
		
		
		//add the table to the database
		db.execSQL(sql_String);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
