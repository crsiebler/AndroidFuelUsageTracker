package edu.asu.bscs.csiebler.fuelusagetracker;

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
public class Globals {
    public static final String QUERY_SELECT_FILLUPS = "SELECT id,odometer,volume,price,dateFilled FROM fillup;";
    public static final String QUERY_INSERT_FILLUP = "INSERT INTO fillup(odometer,volume,price,dateFilled) VALUES (?,?,?,?);";
    public static final String QUERY_MAX_SPAN = "SELECT MAX(ABS(A.dateFilled - B.dateFilled)) FROM fillup AS A, fillup AS B WHERE A.id = B.id + 1;";
    public static final String QUERY_MIN_SPAN = "SELECT MIN(ABS(A.dateFilled - B.dateFilled)) FROM fillup AS A, fillup AS B WHERE A.id != B.id;";
    public static final String QUERY_FUEL_USED = "SELECT SUM(volume) FROM fillup;";
    public static final String QUERY_MONEY_SPENT = "SELECT SUM(price) FROM fillup;";
    public static final String QUERY_DISTANCE_TRAVELED = "SELECT MAX(odometer) - MIN(odometer) FROM fillup;";
    public static final String QUERY_LAST_FILLUP = "SELECT MAX(dateFilled) FROM fillup;";
    public static final String QUERY_DAYS_ACTIVE = "SELECT MAX(dateFilled) - MIN(dateFilled) FROM fillup;";
    public static final String QUERY_HIGH_PRICE = "SELECT MAX(price/volume) FROM fillup;";
    public static final String QUERY_LOW_PRICE = "SELECT MIN(price/volume) FROM fillup;";
    public static final String QUERY_GAS_EFFICIENCY = "SELECT CAST((MAX(odometer) - MIN(odometer)) AS float)/CAST(SUM(volume) AS float) FROM fillup;";
}
