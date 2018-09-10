package com.example.tabrezahmad.createresume.database.Converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Created by Me on 04-Jul-18.
 */

public class Converters {

    // Timestamp to Date
    @TypeConverter
    public static Date timestampToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    // Date to Timestamp
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    // String Array to String
    @TypeConverter
    public static String arrayToString(String[] strings) {
        StringBuilder sb = new StringBuilder();
        // create a comma separated value
        if (strings != null) {
            for (String s : strings) {
                sb.append("," + s.trim());
            }
        }
        return sb.toString();
    }

    // String CSV to String Array
    @TypeConverter
    public static String[] stringToArray(String strings) {
        return strings.split(",");
    }


}
