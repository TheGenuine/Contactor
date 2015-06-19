package de.reneruck.contactor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.reneruck.contactor.models.Contact;

/**
 * Created by reneruck on 19/06/15.
 */
public class EditContactActivity extends AppCompatActivity {

    private ContactsOpenHelper sqlHelper;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        this.sqlHelper = new ContactsOpenHelper(getApplicationContext());
        this.currentContact = new Contact(getIntent().getIntExtra("contactId", -1));

        if (this.currentContact.getId() != -1) {
            loadContactData();
        }
    }

    private void loadContactData() {
        String[] contactIds = new String[]{String.valueOf(this.currentContact.getId())};
        Cursor contactResult = this.sqlHelper.getReadableDatabase().rawQuery("select * from contacts where _id = ?", contactIds);
        contactResult.moveToFirst();
        this.currentContact = new Contact(contactResult);
    }
}
