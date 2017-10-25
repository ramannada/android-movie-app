package com.blogspot.ramannada.movieapp;

import android.content.ContentValues;
//import android.content.Context;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ramannada on 10/24/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "com.blogspot.ramannada.movieapp.db";
    private static final String USER_TABLE_NAME = "user";
    private static final String MOVIE_TABLE_NAME = "movie";
    private static DatabaseHandler databaseHandler = null;
//    private Context context;

//    column user table
    private static final String USER_COLUMN_ID = "id", USER_COLUMN_USERNAME = "username",
        USER_COLUMN_EMAIL = "email", USER_COLUMN_GENDER = "gender",
        USER_COLUMN_PASSWORD = "password", USER_COLUMN_PHOTO = "photo";

//    column movie table
    private static final String MOVIE_COLUMN_ID = "id", MOVIE_COLUMN_TITLE = "title",
        MOVIE_COLUMN_RELEASE_DATE = "release_date", MOVIE_COLUMN_DIRECTOR = "director",
        MOVIE_COLUMN_DURATION = "duration", MOVIE_COLUMN_POSTER = "poster";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

//    public static void init(Context c) {
//        databaseHandler = new DatabaseHandler(c);
//    }
//
//    public synchronized static DatabaseHandler getDatabase() {
//        return databaseHandler;
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                USER_COLUMN_USERNAME + " TEXT, " +
                USER_COLUMN_EMAIL + " TEXT UNIQUE, " +
                USER_COLUMN_GENDER + " TEXT, " +
                USER_COLUMN_PASSWORD + " TEXT, " +
                USER_COLUMN_PHOTO + " INTEGER)";

        final String CREATE_MOVIE_TABLE = "CREATE TABLE " + MOVIE_TABLE_NAME + " (" +
                MOVIE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                MOVIE_COLUMN_TITLE + " TEXT UNIQUE, " +
                MOVIE_COLUMN_DIRECTOR + " TEXT, " +
                MOVIE_COLUMN_RELEASE_DATE + " TEXT, " +
                MOVIE_COLUMN_DURATION + " TEXT, " +
                MOVIE_COLUMN_POSTER + " INTEGER)";

        String QUERY_MOVIE_DATA = "INSERT INTO " + MOVIE_TABLE_NAME + "(" +
                MOVIE_COLUMN_TITLE + "," + MOVIE_COLUMN_DIRECTOR + "," +
                MOVIE_COLUMN_RELEASE_DATE + "," + MOVIE_COLUMN_DURATION + "," +
                MOVIE_COLUMN_POSTER +
                ") values ('It', 'Andrés Muschietti', 'September 8, 2017', '2h 15m',  " +
                R.drawable.pocdtnt +")," +
                "('Wonder Woman', 'Patty Jenkins', '31 May 2017', '2h 21m',  " +
                R.drawable.pocdtnt +")," +
                "('Dunkirk', 'Christopher Nolan', 'July 20, 2017', '2h',  " +
                R.drawable.pocdtnt +")," +
                "('Spider-Man: Homecoming', 'Jon Watts', 'July 5, 2017', '2h 13m',  " +
                R.drawable.pocdtnt +")," +
                "('Blade Runner 2049', 'Denis Villeneuve', 'October 4, 2017', '2h 44m',  " +
                R.drawable.pocdtnt +")," +
                "('War for the Planet of the Apes', 'Matt Reeves', 'July 21, 2017', '2h 22m',  " +
                R.drawable.pocdtnt +")," +
                "('Thor: Ragnarok', 'Taika Waititi', 'October 25, 2017', '2h 10m',  " +
                R.drawable.pocdtnt +")," +
                "('Baby Driver', 'Edgar Wright', 'August 24, 2017', '1h 53m',  " +
                R.drawable.pocdtnt +")," +
                "('Get Out', 'Jordan Peele', 'March 29, 2017', '1h 44m',  " +
                R.drawable.pocdtnt +")," +
                "('Logan', 'James Mangold', '31 March 2017', '2h 21m',  " +
                R.drawable.pocdtnt +")," +
                "('Justice League', 'Zack Snyder', 'November 16, 2017', '2h 50m',  " +
                R.drawable.pocdtnt +")," +
                "('The Dark Tower', 'Nikolaj Arcel', 'August 23, 2017', '1h 30m',  " +
                R.drawable.pocdtnt +")," +
                "('Guardians of the Galaxy Vol. 2', 'James Gunn', 'April 26, 2017', '2h 18m',  " +
                R.drawable.pocdtnt +")," +
                "('Star Wars: The Last Jedi', 'Rian Johnson', 'December 3 ,2017', '2h 30m'," +
                R.drawable.pocdtnt +")";


        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(QUERY_MOVIE_DATA);


//        sqLiteDatabase.execSQL("insert into "+ MOVIE_TABLE_NAME + "(" + MOVIE_COLUMN_TITLE + ","
//                +MOVIE_COLUMN_RELEASE_DATE+ "," + MOVIE_COLUMN_POSTER + ") VALUES (" +
//        "Wonder Woman', '31 May 2017'," +  ContextCompat.getDrawable(context, R.drawable.pocdtnt) + ")");

//        SQLiteDatabase db = this.getWritableDatabase();


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + USER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + MOVIE_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_USERNAME, user.getUsername());
        values.put(USER_COLUMN_EMAIL, user.getEmail());
        values.put(USER_COLUMN_GENDER, user.getGender());
        values.put(USER_COLUMN_PASSWORD, user.getPassword());
        values.put(USER_COLUMN_PHOTO, user.getPhoto());

        db.insert(USER_TABLE_NAME, USER_COLUMN_PHOTO, values);
        db.close();
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + USER_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setGender(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                user.setPhoto(cursor.getInt(5));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        db.close();
        return userList;
    }

    public User getUserByEmail(String email) {

        String selectQuery = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " +
                USER_COLUMN_EMAIL + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        cursor.moveToFirst();
        if(cursor.isBeforeFirst()) {
            return null;
        } else {
            User user = new User();

            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setGender(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setPhoto(cursor.getInt(5));

            return user;

        }

    }

    public Boolean processUserLogin (String email, String password) {
        User user = getUserByEmail(email);

        if (user != null) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Movie> getAllMovie() {
        ArrayList<Movie> movies = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + MOVIE_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();

                movie.setId(cursor.getInt(0));
                movie.setTitle(cursor.getString(1));
                movie.setDirector(cursor.getString(2));
                movie.setReleaseDate(cursor.getString(3));
                movie.setDuration(cursor.getString(4));
                movie.setPoster(cursor.getInt(5));

                movies.add(movie);
            } while (cursor.moveToNext());
        }

        db.close();

        return movies;
    }
}
