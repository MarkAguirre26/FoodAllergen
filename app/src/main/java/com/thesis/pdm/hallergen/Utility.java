package com.thesis.pdm.hallergen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.se.omapi.Session;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.net.PasswordAuthentication;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.sql.DataSource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Utility {

    private static Utility instance = new Utility();

    public static Utility getInstance() {
        return instance;
    }

    public static void setInstance(Utility instance) {
        Utility.instance = instance;
    }

    private Context curContext;

    public void setContext(Context context) {
        this.curContext = context;
    }

    public static boolean RotateToggle(Context context, View view) {
        boolean status = view.getTag().toString().equals("ON");
        view.startAnimation(AnimationUtils.loadAnimation(context, status ? R.anim.anim_rotate_c : R.anim.anim_rotate_cc));
        view.setTag(status ? "OFF" : "ON");
        return status;
    }

    public static boolean isOnline(Object SystemService) {
        ConnectivityManager conMngr = (ConnectivityManager) SystemService;
        NetworkInfo netInfo = conMngr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void ChangePasswordInputType(MotionEvent event, EditText et, boolean status) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (et.getRight() - et.getCompoundDrawables()[2].getBounds().width())) {
                int eye = status ? R.drawable.icon_visibility_off : R.drawable.icon_visibility;
                TransformationMethod method = status ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance();
                et.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, eye, 0);
                et.setTransformationMethod(method);
            }
        }
    }

    public static ModelsUser FindUser(String username, String password, ArrayList<ModelsUser> userList) {
        for (ModelsUser user : userList) {

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    public static void setLogUserDataToPref(SharedPreferences.Editor prefEdit, ModelsUser logUser) {

        prefEdit.putString(String.valueOf(R.string.pref_UID), logUser.getUserUID()).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Fname), logUser.getFirstName()).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Mname), logUser.getMiddleName()).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Lname), logUser.getLastName()).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Username), logUser.getUsername()).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Password), logUser.getPassword()).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Email), logUser.getEmail()).apply();

    }


    public static ModelsUser getLogUserDataFromPref(SharedPreferences pref) {

        ModelsUser user  =  new ModelsUser();
        user.setUserUID(pref.getString(String.valueOf(R.string.pref_UID), ""));
        user.setFirstName(pref.getString(String.valueOf(R.string.pref_Fname), ""));
        user.setMiddleName(pref.getString(String.valueOf(R.string.pref_Mname), ""));
        user.setLastName(pref.getString(String.valueOf(R.string.pref_Lname), ""));
        user.setUsername(pref.getString(String.valueOf(R.string.pref_Username), ""));
        user.setPassword(pref.getString(String.valueOf(R.string.pref_Password), ""));
        user.setEmail(pref.getString(String.valueOf(R.string.pref_Email), ""));
        return user;

    }


    public static void setRegistrationUserDataToPref(SharedPreferences prefEdit, ModelsUser user,String code) {

        prefEdit.edit().putString(String.valueOf(R.string.pref_UID), user.getUserUID()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_Fname), user.getFirstName()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_Mname), user.getMiddleName()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_Lname), user.getLastName()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_Username), user.getUsername()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_Password), user.getPassword()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_Email), user.getEmail()).apply();
        prefEdit.edit().putString(String.valueOf(R.string.pref_code), code).apply();

    }


    public static ModelsUser getRegistrationUserDataFromPref(SharedPreferences prefEdit) {
        ModelsUser user  =  new ModelsUser();
        user.setUserUID(prefEdit.getString(String.valueOf(R.string.pref_UID), ""));
        user.setFirstName(prefEdit.getString(String.valueOf(R.string.pref_Fname), ""));
        user.setMiddleName(prefEdit.getString(String.valueOf(R.string.pref_Mname), ""));
        user.setLastName(prefEdit.getString(String.valueOf(R.string.pref_Lname), ""));
        user.setUsername(prefEdit.getString(String.valueOf(R.string.pref_Username), ""));
        user.setPassword(prefEdit.getString(String.valueOf(R.string.pref_Password), ""));
        user.setEmail(prefEdit.getString(String.valueOf(R.string.pref_Email), ""));
        return user;
    }



    public static ArrayList<ModelsUser> GetAllUsersToFirebase(DatabaseReference refDB) {
        final ArrayList<ModelsUser> userlist = new ArrayList<>();
        refDB.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String userUID = postSnapshot.getKey();
                    assert userUID != null;
                    DataSnapshot userInfo = dataSnapshot.child(userUID);
                    ModelsUser user = new ModelsUser();
                    user.setUserUID(userUID);
                    user.setFamily(getFamily(userInfo));
                    user.setEmail((String) userInfo.child("email").getValue());
                    user.setFirstName((String) userInfo.child("firstName").getValue());
                    user.setLastName((String) userInfo.child("lastName").getValue());
                    user.setMiddleName((String) userInfo.child("middleName").getValue());
                    user.setPassword((String) userInfo.child("password").getValue());
                    user.setUsername((String) userInfo.child("username").getValue());
                    userlist.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return userlist;
    }





    private static List<ModelsFamily> getFamily(DataSnapshot userInfo){
        List<ModelsFamily> list =new ArrayList<>();
        for (DataSnapshot dsp: userInfo.child("familyMember").getChildren()){
            ModelsFamily f =  dsp.getValue(ModelsFamily.class);
            f.setFamilyUID(dsp.getKey());
           list.add(f);

        }
        return list;
    }

//    public static ArrayList<ModelsContact> GetUserContactsToFirebase(DatabaseReference refDB, String userUID) {
//        final ArrayList<ModelsContact> contactlist = new ArrayList<>();
//        refDB.child("Users").child(userUID).child("contacts").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    String contactUID = postSnapshot.getKey();
//                    assert contactUID != null;
//                    DataSnapshot contactInfo = dataSnapshot.child(contactUID);
//                    ModelsContact contact = new ModelsContact();
//                    contact.setContactUID(contactUID);
//                    contact.setContactName((String) contactInfo.child("contactName").getValue());
//                    contact.setContactRelation((String) contactInfo.child("contactRelation").getValue());
//                    contact.setContactNumber((String) contactInfo.child("contactNumber").getValue());
//                    contact.setReceiveMessage((boolean) contactInfo.child("isReceiveMessage").getValue());
//                    contactlist.add(contact);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//        return contactlist;
//    }

    public  static String getGUID(){
        return UUID.randomUUID().toString();
    }
    public static void AppSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static String getAge(String date) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(stringToDate(date));

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return String.valueOf(age);
    }

    public static Date stringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(date);
    }
}
