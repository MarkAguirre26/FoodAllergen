package com.thesis.pdm.hallergen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.clearcut.LogUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;

public class ContactsActivity extends AppCompatActivity implements AdapterRecyclerContact.OnLongClickContactListener {

    private EditText etContactName, etContactNumber, etContactRelation;

    private ModelsContact selectedContact = new ModelsContact();
    private static ArrayList<ModelsContact> _contactsList = new ArrayList<>();

    private AdapterRecyclerContact adapter;
    private RecyclerView recyclerView;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdit;

    private DatabaseReference refDB = FirebaseDatabase.getInstance().getReference();

    private DatabaseAdapter db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        pref = this.getSharedPreferences(String.valueOf(R.string.pref_Account), MODE_PRIVATE);
        prefEdit = pref.edit();

        TextView tvTitle = findViewById(R.id.toolbar_title);
        ImageView btnRight = findViewById(R.id.toolbar_btn_right);
        tvTitle.setText(R.string.str_contact);
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setImageResource(R.mipmap.icon_add);

        db = new DatabaseAdapter(getApplicationContext());


        etContactName = findViewById(R.id.etContactName);
        etContactNumber = findViewById(R.id.etContactNumber);
        etContactRelation = findViewById(R.id.etContactRelation);
        getContacts();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 1: // Add Contact
                Uri ContactUri = data.getData();
                Cursor cursor = getContentResolver().query(ContactUri, null, null, null, null);
                cursor.moveToFirst();
                etContactName.setText(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                etContactNumber.setText(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                break;
        }
    }

    private void ClearContactInfo() {
        etContactName.setText(null);
        etContactNumber.setText(null);
        etContactRelation.setText(null);
    }

    private void getContacts() {
        //TODO get contact from database
        initRecyclerContact();
    }

    //display to list
    private void initRecyclerContact() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerContactList);
        recyclerView.setLayoutManager(layoutManager);
        RefreshList();
    }

    private void RefreshList() {

        List<ModelsContact> contactList = db.getContactData();
        _contactsList.addAll(contactList);
        adapter = new AdapterRecyclerContact(this, _contactsList, this);
        recyclerView.setAdapter(adapter);
    }

    public void OnClick_ToolbarBack(View view) {
        finish();
    }

    public void OnClick_ToolbarForward(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_contact_dialog).setVisibility(View.VISIBLE);
        findViewById(R.id.btnSaveContact).setTag("New");
        ClearContactInfo();
    }

    public void OnCLick_CancelAddContact(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_contact_dialog).setVisibility(View.GONE);
    }

    public void OnCLick_ChooseContact(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        Intent addContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(addContact, 1);
    }

    public void OnCLick_SaveContact(View view) {
        if (etContactName.getText().toString().equals("") || etContactNumber.toString().equals("") || etContactRelation.toString().equals("")) {
            return;
        }
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));

        // add to model sa input data
        ModelsContact newContact = new ModelsContact();
        newContact.setAccountID(logUser.getUserUID());
        newContact.setContactName(etContactName.getText().toString());
        newContact.setContactNumber(etContactNumber.getText().toString());
        newContact.setContactRelation(etContactRelation.getText().toString());
        newContact.setReceiveMessage(true);

        // get the userID to save the contact
//        String userUID = pref.getString(String.valueOf(R.string.pref_UID), "");
//        assert userUID != null;

        if (view.getTag().equals("New")) {
//            refDB.child("Users").child(userUID).child("contacts").push().setValue(newContact);
//            _contactsList.add(newContact);
            db.insertContact(newContact);
        }// Add New Contact
        else {
            db.deleteContact(newContact);
            db.insertContact(newContact);
            _contactsList.set(Integer.parseInt((String) view.getTag()), newContact);
        }// Edit Selected Contact

        // refresh list after add/edit
        RefreshList();
        // hide dialog
        findViewById(R.id.con_contact_dialog).setVisibility(View.GONE);
    }

    @Override
    public void OnLongClickContactListener(int position) {
        selectedContact = _contactsList.get(position);
        TextView tvname = findViewById(R.id.tvEditDelete);
        tvname.setText(selectedContact.getContactName());
        findViewById(R.id.con_edit_delete).setVisibility(View.VISIBLE);
        findViewById(R.id.btnSaveContact).setTag(String.valueOf(position));
    }

    public void OnCLick_Edit(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));

        // TODO Edit function
    }

    public void OnCLick_Delete(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));

        // TODO Delete Function
    }

    public void OnCLick_Close(View view) {
        findViewById(R.id.con_edit_delete).setVisibility(View.GONE);
    }
}
