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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class ListActivity extends ActionBarActivity {

    public ListView lview;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Fillup> fillups = new ArrayList<>();

        FillupDB fillupDB = FillupDB.getInstance(this);

        try {
            fillupDB.copyDB();
            SQLiteDatabase db = fillupDB.openDB();

            Cursor cursor = db.rawQuery(Globals.QUERY_SELECT_FILLUPS, new String[]{});

            while (cursor.moveToNext()) {
                Fillup fillup = new Fillup(
                        cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2), cursor.getDouble(3), new Date(cursor.getLong(4) * 1000L)
                );

                fillups.add(fillup);
            }

            cursor.close();

            db.close();
        } catch (SQLException | IOException e) {
            Log.d(this.getClass().getSimpleName(), e.getMessage());
        }

        lview = (ListView) findViewById(R.id.fillup_list);
        ArrayAdapter<Fillup> adapter = new ArrayAdapter<>(this, R.layout.listview_item, fillups.toArray(new Fillup[fillups.size()]));
        lview.setAdapter(adapter);
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
    public void showDashboard(View v) {
        Intent intent = new Intent(this, DashActivity.class);
        startActivity(intent);
    }
}
