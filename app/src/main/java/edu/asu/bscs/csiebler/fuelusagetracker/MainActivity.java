package edu.asu.bscs.csiebler.fuelusagetracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Copyright 2015 Cory Siebler
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author Cory Siebler csiebler@asu.edu
 * @version Apr 25, 2015
 */
public class MainActivity extends ActionBarActivity {

    private EditText odometerField;
    private EditText volumeField;
    private EditText priceField;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        odometerField = (EditText) findViewById(R.id.odometer_field);
        volumeField = (EditText) findViewById(R.id.fillup_field);
        priceField = (EditText) findViewById(R.id.price_field);
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
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

    /**
     *
     * @param v
     */
    public void saveFillup(View v) {
        if (validInput()) {
            FillupDB fillupDB = FillupDB.getInstance(this);

            try {
                Log.d(this.getClass().getSimpleName(), "INSERTING NEW FILLUP");

                fillupDB.copyDB();
                SQLiteDatabase db = fillupDB.openDB();

                db.execSQL(Globals.QUERY_INSERT_FILLUP, new String[]{
                        odometerField.getText().toString(),
                        volumeField.getText().toString(),
                        priceField.getText().toString(),
                        String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000)
                });

                db.close();

                Log.d(this.getClass().getSimpleName(), "SUCCESS - FILLUP INSERTED");

                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
            } catch (SQLException | IOException e) {
                Log.d(this.getClass().getSimpleName(), e.getMessage());
            }
        } else {
            Log.d(this.getClass().getSimpleName(), "ERROR: Invalid Input");
        }
    }

    private boolean validInput() {
        return odometerField.getText().length() > 0
                && volumeField.getText().length() > 0
                && priceField.getText().length() > 0;
    }

    /**
     *
     * @param v
     */
    public void clearFields(View v) {
        odometerField.setText(R.string.label_empty);
        volumeField.setText(R.string.label_empty);
        priceField.setText(R.string.label_empty);
    }

    /**
     *
     * @param v
     */
    public void cancel(View v) {
        Intent intent = new Intent(this, DashActivity.class);
        startActivity(intent);
    }
}
