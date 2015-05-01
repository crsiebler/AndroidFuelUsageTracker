package edu.asu.bscs.csiebler.fuelusagetracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

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
public class DashActivity extends ActionBarActivity {

    public TextView fuelUsedTV;
    public TextView moneySpentTV;
    public TextView distanceDrivenTV;
    public TextView gasEfficiencyTV;
    public TextView lastFillupTV;
    public TextView daysActiveTV;
    public TextView longStreakTV;
    public TextView shortStreakTV;
    public TextView cheapGasTV;
    public TextView expensiveGasTV;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        fuelUsedTV = (TextView) findViewById(R.id.fuelUsed_Label);
        moneySpentTV = (TextView) findViewById(R.id.moneySpent_Label);
        distanceDrivenTV = (TextView) findViewById(R.id.distanceDriven_Label);
        gasEfficiencyTV = (TextView) findViewById(R.id.gasEfficiency_Label);
        lastFillupTV = (TextView) findViewById(R.id.lastFillup_Label);
        daysActiveTV = (TextView) findViewById(R.id.daysActive_Label);
        longStreakTV = (TextView) findViewById(R.id.longStreak_Label);
        shortStreakTV = (TextView) findViewById(R.id.shortStreak_Label);
        cheapGasTV = (TextView) findViewById(R.id.cheapGas_Label);
        expensiveGasTV = (TextView) findViewById(R.id.expensiveGas_Label);
    }

    @Override
    public void onResume() {
        super.onResume();

        FillupDB fillupDB = FillupDB.getInstance(this);

        try {
            fillupDB.copyDB();
            SQLiteDatabase db = fillupDB.openDB();

            Cursor cursor = db.rawQuery(Globals.QUERY_FUEL_USED, new String[]{});

            if (cursor.moveToNext()) {
                fuelUsedTV.setText(new DecimalFormat("0.0").format(cursor.getDouble(0)) + " Gallons");
            } else {
                fuelUsedTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_MONEY_SPENT, new String[]{});

            if (cursor.moveToNext()) {
                moneySpentTV.setText("$" + new DecimalFormat("0.00").format(cursor.getDouble(0)));
            } else {
                moneySpentTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_DISTANCE_TRAVELED, new String[]{});

            if (cursor.moveToNext()) {
                distanceDrivenTV.setText(cursor.getInt(0) + " Miles");
            } else {
                distanceDrivenTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_GAS_EFFICIENCY, new String[]{});

            if (cursor.moveToNext()) {
                gasEfficiencyTV.setText(new DecimalFormat("0.0").format(cursor.getDouble(0)) + " mpg");
            } else {
                gasEfficiencyTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_LAST_FILLUP, new String[]{});

            if (cursor.moveToNext()) {
                Calendar cal = Calendar.getInstance();
                int seconds = (int) (cal.getTimeInMillis() / 1000);

                lastFillupTV.setText(((seconds - cursor.getInt(0)) / 86400) + " days ago");
            } else {
                lastFillupTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_DAYS_ACTIVE, new String[]{});

            if (cursor.moveToNext()) {
                if (cursor.getInt(0) != 0) {
                    daysActiveTV.setText((cursor.getInt(0) / 86400) + " days");
                } else {
                    daysActiveTV.setText("0 days");
                }
            } else {
                daysActiveTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_MAX_SPAN, new String[]{});

            if (cursor.moveToNext()) {
                if (cursor.getInt(0) != 0) {
                    longStreakTV.setText((cursor.getInt(0) / 86400) + " days");
                } else {
                    longStreakTV.setText("0 days");
                }
            } else {
                longStreakTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_MIN_SPAN, new String[]{});

            if (cursor.moveToNext()) {
                if (cursor.getInt(0) != 0) {
                    shortStreakTV.setText((cursor.getInt(0) / 86400) + " days");
                } else {
                    shortStreakTV.setText("0 days");
                }
            } else {
                shortStreakTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_LOW_PRICE, new String[]{});

            if (cursor.moveToNext()) {
                cheapGasTV.setText("$" + new DecimalFormat("0.00").format(cursor.getDouble(0)) + "/gal");
            } else {
                cheapGasTV.setText(R.string.label_empty);
            }

            cursor.close();

            cursor = db.rawQuery(Globals.QUERY_HIGH_PRICE, new String[]{});

            if (cursor.moveToNext()) {
                expensiveGasTV.setText("$" + new DecimalFormat("0.00").format(cursor.getDouble(0)) + "/gal");
            } else {
                expensiveGasTV.setText(R.string.label_empty);
            }

            cursor.close();

            db.close();
        } catch (SQLException | IOException e) {
            Log.d(this.getClass().getSimpleName(), e.getMessage());
        }
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
    public void showFillups(View v) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param v
     */
    public void addFillup(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
