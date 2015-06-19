package de.reneruck.contactor;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
            populateFields();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_save:
                if(updateContact()){
                    Toast.makeText(getApplicationContext(), "Contact saved", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Couldn't save this contact, try again", Toast.LENGTH_LONG).show();
                }
        }

        return true;
    }

    private boolean updateContact(){
        String name = ((EditText) findViewById(R.id.input_name)).getText().toString();

        String[] nameSplit = name.split(" ");

        String email = ((EditText) findViewById(R.id.input_email)).getText().toString();
        String phone_priv = ((EditText) findViewById(R.id.input_phone_private)).getText().toString();
        String phone_work = ((EditText) findViewById(R.id.input_phone_work)).getText().toString();

        ContentValues values = new ContentValues();
        values.put("firstName", nameSplit[0]);
        values.put("lastName", nameSplit[1]);
        values.put("email", email);
        values.put("phonePrivate", phone_priv);
        values.put("phoneWork", phone_work);

        String[] args = {String.valueOf(this.currentContact.getId())};
        if(this.currentContact.getId() != -1){
            return this.sqlHelper.getWritableDatabase().update(ContactsOpenHelper.CONTACTS_TABLE_NAME, values, "where _id = ?", args) > 0 ? true : false;
        } else {
            return this.sqlHelper.getWritableDatabase().insert(ContactsOpenHelper.CONTACTS_TABLE_NAME, null, values) > 0 ? true : false;
        }
    }

    private void populateFields() {
        ((EditText) findViewById(R.id.input_name)).setText(this.currentContact.getName());
        ((EditText) findViewById(R.id.input_email)).setText(this.currentContact.getEmail());
        ((EditText) findViewById(R.id.input_phone_private)).setText(this.currentContact.getPhonePrivate());
        ((EditText) findViewById(R.id.input_phone_work)).setText(this.currentContact.getPhoneWork());
    }

    private void loadContactData() {
        String[] contactIds = new String[]{String.valueOf(this.currentContact.getId())};
        Cursor contactResult = this.sqlHelper.getReadableDatabase().rawQuery("select * from contacts where _id = ?", contactIds);
        contactResult.moveToFirst();
        this.currentContact = new Contact(contactResult);
    }
}
