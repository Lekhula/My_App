package com.pabi.my_app.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import static com.pabi.my_app.provider.PlaceContract.BASE_CONTENT_URI;
import static com.pabi.my_app.provider.PlaceContract.PATH_PLACES;

/**
 * Created by Admin on 3/7/2018.
 */

public  final class PlaceEntry implements BaseColumns {

    // TaskEntry content URI = base content URI + path
    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLACES).build();

    public static final String TABLE_NAME = "places";
    public static final String COLUMN_PLACE_ID = "placeID";
}