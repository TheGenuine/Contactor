package de.reneruck.contactor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
