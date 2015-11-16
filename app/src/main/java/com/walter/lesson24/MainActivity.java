package com.walter.lesson24;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void call(View v)
    {
        Intent phone=new Intent(Intent.ACTION_CALL);
        phone.setData(Uri.parse("tel:0723740215"));
        try
        {
            startActivity(phone);
        }
        catch(SecurityException e)
        {
            Log.e("DATA","You need to add permission in your manifest");
        }
    }
    public void sms(View v)
    {
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage("0723740215",null,"Test message",null,null);
    }
    public void inbox(View v)
    {
        String inbox="content://sms/inbox";
        // String sent="content://sms/sent";
        // String draft="content://sms/draft";
        Cursor cursor= getContentResolver().query(Uri.parse(inbox), null, null, null, null);
        if(cursor.moveToFirst())
        {
            int x=0;
            do{
                String address= cursor.getString(2);
                String date= cursor.getString(5);
                String body= cursor.getString(12);
                Log.d("DATA",address+" : "+date+" : "+body);
                x++;
              }while(x<=2);
            // 2 : address//5 : date_sent//12 : body
           /* for(int i=0; i<cursor.getColumnCount(); i++)
            {
               Log.d("COLUMN", i+" : "+cursor.getColumnName(i));
            }*/
        }

    }
    public void outbox(View v)
    {
        String inbox="content://sms/sent";
        // String sent="content://sms/sent";
        // String draft="content://sms/draft";
        Cursor cursor= getContentResolver().query(Uri.parse(inbox), null, null, null, null);
        if(cursor.moveToFirst())
        {
           int x=0;
            do{
                String address= cursor.getString(2);
                String date= cursor.getString(5);
                String body= cursor.getString(12);
                Log.d("DATA",address+" : "+date+" : "+body);
                x++;
            }while(x<=5);
            // 2 : address//5 : date_sent//12 : body
          /* for(int i=0; i<cursor.getColumnCount(); i++)
            {
               Log.d("COLUMN", i+" : "+cursor.getColumnName(i));
            }*/
        }

    }


}
