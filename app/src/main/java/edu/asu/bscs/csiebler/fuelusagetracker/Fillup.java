package edu.asu.bscs.csiebler.fuelusagetracker;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Copyright 2015 Cory Siebler
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author Cory Siebler csiebler@asu.edu
 * @version Apr 30, 2015
 */
public class Fillup {

    private int id;
    private int odometer;
    private double volume;
    private double price;
    private Date date;

    public Fillup() {
        this.date = new Date();
    }

    public Fillup(int odometer, double volume, double price) {
        this.odometer = odometer;
        this.volume = volume;
        this.price = price;
        this.date = new Date();
    }

    public Fillup(int id, int odometer, double volume, double price, Date date) {
        this.id = id;
        this.odometer = odometer;
        this.volume = volume;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return DateFormat.getDateInstance().format(date) + " - "
                + new DecimalFormat("0.0").format(volume) + " Gallons [$"
                + new DecimalFormat("0.00").format(price) + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
