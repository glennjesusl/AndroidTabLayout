package com.example.androidtablayout;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ViewRecordsActivity extends Activity {
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewrecord_layout); 
        
    getRecords();
            
    }
    
    public void getRecords() {
    	
    	//initiate the SqlHelperClass and get the database
    	SqlHelperClass sqlHelper = new SqlHelperClass(ViewRecordsActivity.this);
    	SQLiteDatabase db = sqlHelper.getWritableDatabase();
    	
    	//create an array of the columns
    	String[] columns = new String[] {
    			sqlHelper.STRING_INPUT 	};
    	
    	//start a cursor to move through the records
    	Cursor cursor = db.query(sqlHelper.TABLE_NAME, columns, null, null, null, null, null);
    	
    	String result = " ";
    	
    	//use the cursor to loop through the records
    	for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
    		//Im just concatenating the records into a string terminated by line break
    		
//    		result += cursor.getString(0) + ", " + cursor.getString(1) + "\n" ;
    		result += cursor.getString(0) + "\n" ;
    	}
    	
    	//assign the result to the textview
    	TextView content = (TextView)findViewById(R.id.textview2);
    	content.setText(result);
    	
    }
    
}