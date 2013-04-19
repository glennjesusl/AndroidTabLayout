package com.example.androidtablayout;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecordsActivity extends Activity {
	
	EditText textString;
	Button btnSave;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord_layout);
        
        btnSave = (Button)findViewById(R.id.button1);
        btnSave.setOnClickListener(new AddRecordListener());
    }
    
    private class AddRecordListener implements View.OnClickListener{
    	
    	public void onClick(View v) {
    		
    		//get database
    		SqlHelperClass sqlHelper = new SqlHelperClass(AddRecordsActivity.this);
    		SQLiteDatabase db = sqlHelper.getWritableDatabase();
    		
    		//get the values from the EditText controls
    		textString = (EditText)findViewById(R.id.editText1);
    		String stringinput = textString.getText().toString();
    		
    		//add the values from the EditText
    		ContentValues values = new ContentValues();
    		values.put(SqlHelperClass.STRING_INPUT, stringinput);
    		
    		//insert into the Database
    		long string_id = db.insert(SqlHelperClass.TABLE_NAME, null, values);
    		db.close();
    		
    		//if the insert is successful
    		if(string_id != -1) {
    			//start a toast message
    			Toast toast = Toast.makeText(AddRecordsActivity.this, "Record Added", Toast.LENGTH_LONG);
    			toast.show();    			
    		}
    		
    		else { 
    			Toast toast = Toast.makeText(AddRecordsActivity.this, "Record failed to Insert", Toast.LENGTH_LONG);
    			toast.show(); 
    		}
    		
    		clearForm();
    		  		
    	}
    }
    
    private void clearForm() {
    	textString.setText("");
    }
        
}
