package de.reneruck.contactor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by reneruck on 17/06/15.
 */
public class ContactsOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String CONTACTS_TABLE_NAME = "contacts";
    private static final String CONTACTS_TABLE_CREATE =
            "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" +
                    "_id INTEGER PRIMARY KEY autoincrement," +
                    "firstName TEXT, " +
                    "lastName TEXT," +
                    "email TEXT," +
                    "phonePrivate TEXT," +
                    "phoneWork TEXT);";

    public ContactsOpenHelper(Context context) {
        super(context, CONTACTS_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
