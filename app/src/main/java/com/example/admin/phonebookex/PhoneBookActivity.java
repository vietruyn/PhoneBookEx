package com.example.admin.phonebookex;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class PhoneBookActivity extends Activity {

    private ListView lvPhoneBook;
    private PhoneBookAdapter adapter;
    private ArrayList<PhoneBook> phoneBookArrayList;

    private String phoneNumber = null;
    private String email = null;

    private Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    private String _ID = ContactsContract.Contacts._ID;
    private String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    private Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    private String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    private Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    private String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    private String DATA = ContactsContract.CommonDataKinds.Email.DATA;

    private StringBuffer output = new StringBuffer();

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);
        new getListContact().execute();

    }

    class getListContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(PhoneBookActivity.this);
            mProgressDialog.setMessage("Please Waiting...");
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ContentResolver contentResolver = getContentResolver();

            Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

            phoneBookArrayList = new ArrayList<PhoneBook>();
            if (cursor.getCount() > 0) {

                while (cursor.moveToNext()) {
                    PhoneBook phoneBook = new PhoneBook();
                    String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                    int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                    if (hasPhoneNumber > 0) {

                        output.append("\n First Name:" + name);
                        phoneBook.setName(name);

                        // Query and loop for every phone number of the contact
                        Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                        while (phoneCursor.moveToNext()) {
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                            output.append("\n Phone number:" + phoneNumber);
                            phoneBook.setPhoneNumber(phoneNumber);

                        }

                        phoneCursor.close();

                        // Query and loop for every email of the contact
                        Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);

                        while (emailCursor.moveToNext()) {

                            email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                            phoneBook.setEmail(email);
                            output.append("\nEmail:" + email);
                        }

                        emailCursor.close();
                        phoneBookArrayList.add(phoneBook);
                        Log.e("PhoneBook: ", name + " " + phoneNumber + " " + email + "\n");
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressDialog.hide();
            lvPhoneBook = (ListView) findViewById(R.id.lvPhoneNumber);
            adapter = new PhoneBookAdapter(PhoneBookActivity.this, phoneBookArrayList);
            lvPhoneBook.setAdapter(adapter);
        }
    }

}