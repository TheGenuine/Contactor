package de.reneruck.contactor.models;

import android.database.Cursor;

/**
 * Created by reneruck on 19/06/15.
 */
public class Contact {

    private int id = -1;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneWork;
    private String phonePrivate;

    public Contact(int id, String firstName, String lastName, String email, String phoneWork, String phonePrivate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneWork = phoneWork;
        this.phonePrivate = phonePrivate;
    }

    public Contact(Cursor cursor) {
        String[] columnNames = cursor.getColumnNames();
        new Contact(
                cursor.getInt(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("firstName")),
                cursor.getString(cursor.getColumnIndex("lastName")),
                cursor.getString(cursor.getColumnIndex("email")),
                cursor.getString(cursor.getColumnIndex("phoneWork")),
                cursor.getString(cursor.getColumnIndex("phonePrivate"))
        );
    }

    public Contact(int id) {
        this.id = id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public String getPhonePrivate() {
        return phonePrivate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public void setPhonePrivate(String phonePrivate) {
        this.phonePrivate = phonePrivate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
