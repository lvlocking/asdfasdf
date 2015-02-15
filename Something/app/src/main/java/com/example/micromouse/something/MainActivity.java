package com.example.micromouse.something;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase mydatabase = openOrCreateDatabase("database",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS events(eventID INT PRIMARY KEY, Text TEXT, timing VARCHAR);");
        //time format yyyymmddhhmm
        mydatabase.execSQL("INSERT INTO events VALUES(0,'TEST', '201502141319');");


    }

    /*
     * Takes date and converts date to unix time
     */
    public long convertDateToMillisecond(String date){
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        long unixtime;
        format.setTimeZone(TimeZone.getDefault());
        try
        {
            unixtime = format.parse(date).getTime();
            unixtime = unixtime/1000;
            return unixtime;
        }
        catch(ParseException e){
            return 0;
        }
    }

    /*
     * Take date and returns true if it is a valid date, false if it is not.
     */
    public boolean valiDate(String date){

    }



    /*
     * Takes database, text, and date and adds it to database.  Automatically gets ID.
     */
    public void insertIntoDatabase(SQLiteDatabase database, String text, String timing){
        int id = databaseCount(database);
        database.execSQL("INSERT INTO events VALUES(" + String.valueOf(id) + ",'"+text+ "','" + timing +"');");
    }
    /*
     * Gets the next ID to use for the database.
     */
    public int databaseCount(SQLiteDatabase database){
        Cursor c = database.rawQuery("select count(*) as number from events;", null);
        StringBuffer buffer = new StringBuffer();
        c.moveToFirst();
        int value = Integer.getInteger(c.getString(0));
        c.close();
        return value;
    }

    public boolean queryDatabase(SQLiteDatabase database){
        Cursor c=database.rawQuery("SELECT * FROM events ORDER BY timing", null);
        if(c.getCount()==0)
        {
            //Print not found error somehow?
            return false;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("ID: "+c.getString(0)+"\n");
            buffer.append("TEXT: "+c.getString(1)+"\n");
            buffer.append("TIMING: "+c.getString(2)+"\n\n");
        }
        c.close();
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {

    }
}
