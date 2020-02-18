package com.thesis.pdm.hallergen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;


public class DatabaseAdapter {
    myDbHelper myhelper;

    public DatabaseAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertUserData(ModelsUser user) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.ACCOUNT_ID, user.getUserUID());
        contentValues.put(myDbHelper.EMAIL, user.getEmail());
        contentValues.put(myDbHelper.FIRST_NAME, user.getFirstName());
        contentValues.put(myDbHelper.LAST_NAME, user.getLastName());
        contentValues.put(myDbHelper.MIDDLE_NAME, user.getMiddleName());
        contentValues.put(myDbHelper.PASSWORD, user.getPassword());
        contentValues.put(myDbHelper.USERNAME, user.getUsername());
        long id = db.insert(myDbHelper.TABLE_NAME_ACCOUNT, null, contentValues);
        Log.d("InsertUser", contentValues.get(myDbHelper.USERNAME).toString());
        db.close();
        return id;
    }

    public long insertIntakeData(ModelIntake intake) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.ACCOUNT_ID, intake.getAccountID());
        contentValues.put(myDbHelper.FAM_GUID, intake.getFamId());
        contentValues.put(myDbHelper.ALLOWANCE_ENERGY, intake.getEnergy());
        contentValues.put(myDbHelper.ALLOWANCE_PROTEIN, intake.getProtein());
        contentValues.put(myDbHelper.ALLOWANCE_TOTAL_FAT, intake.getTotal_fat());
        contentValues.put(myDbHelper.ALLOWANCE_ESSENTIAL_FATTY_ACID, intake.getEssentialFattyAcid());
        contentValues.put(myDbHelper.ALLOWANCE_DIETARY, intake.getDietaryFiber());
        contentValues.put(myDbHelper.ALLOWANCE_WATER, intake.getWater());
        long id = db.insert(myDbHelper.TABLE_NAME_ALLOWANCE, null, contentValues);
        Log.d("InsertIntakeData", contentValues.get(myDbHelper.ALLOWANCE_ENERGY).toString());
        db.close();
        return id;
    }


    public long insertFamilyData(ModelsFamily family) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.ACCOUNT_ID, family.getAccountUID());
        contentValues.put(myDbHelper.BIRTHDAY, family.getBirthday());
        contentValues.put(myDbHelper.FAM_GUID, family.getFamily_GUID());
        if (family.isGender()) {
            contentValues.put(myDbHelper.GENDER, "1");
        } else {
            contentValues.put(myDbHelper.GENDER, "0");
        }

        contentValues.put(myDbHelper.HEIGHT, family.getHeight());
        contentValues.put(myDbHelper.IMAGE_ID, String.valueOf(family.getImageResourcesId()));
        contentValues.put(myDbHelper.FAM_NAME, family.getName());
        if (family.isPregnant()) {
            contentValues.put(myDbHelper.PREGNANT, "1");
        } else {
            contentValues.put(myDbHelper.PREGNANT, "0");
        }
        contentValues.put(myDbHelper.BIRTHDAY, family.getBirthday());
        contentValues.put(myDbHelper.RELATION, family.getRelation());
        contentValues.put(myDbHelper.WEIGHT, family.getWeight());
        long id = dbb.insert(myDbHelper.TABLE_NAME_FAMILY, null, contentValues);
        Log.d("InsertFamily", contentValues.get(myDbHelper.FAM_NAME).toString());
        dbb.close();
        return id;
    }


    public long insertAllergy(ModelsAllergy allergy) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.FAM_GUID, allergy.getFamilyGUID());
        contentValues.put(myDbHelper.ALLERGY, allergy.getAllergyName());
        long id = dbb.insert(myDbHelper.TABLE_NAME_ALLERGY, null, contentValues);
        Log.d("InsertAllergy", contentValues.get(myDbHelper.ALLERGY).toString());
        dbb.close();
        return id;
    }


    public long insertContact(ModelsContact contact) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.ACCOUNT_ID, contact.getAccountID());
        contentValues.put(myDbHelper.CONTACT_NAME, contact.getContactName());
        contentValues.put(myDbHelper.CONTACT_NUMBER, contact.getContactNumber());
        contentValues.put(myDbHelper.CONTACT_RELATIONSHIP, contact.getContactRelation());
        long id = dbb.insert(myDbHelper.TABLE_NAME_CONTACT, null, contentValues);
        Log.d("InsertContact", contentValues.get(myDbHelper.CONTACT_NAME).toString());
        dbb.close();
        return id;
    }


    public void deleteUserData(ModelsUser user) {

    }

    public long updateUserData(ModelsUser user) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.ACCOUNT_ID, user.getUserUID());
        contentValues.put(myDbHelper.EMAIL, user.getEmail());
        contentValues.put(myDbHelper.FIRST_NAME, user.getFirstName());
        contentValues.put(myDbHelper.LAST_NAME, user.getLastName());
        contentValues.put(myDbHelper.MIDDLE_NAME, user.getMiddleName());
        contentValues.put(myDbHelper.PASSWORD, user.getPassword());
        contentValues.put(myDbHelper.USERNAME, user.getUsername());
        long id = db.update(myDbHelper.TABLE_NAME_ACCOUNT, contentValues, "accountID = ?", new String[]{user.getUserUID()});
        Log.d("UpdateUser", contentValues.get(myDbHelper.USERNAME).toString());
        db.close();
        return id;
    }

    public long updateIntakeData(ModelIntake intake) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.ACCOUNT_ID, intake.getAccountID());
        contentValues.put(myDbHelper.FAM_GUID, intake.getFamId());
        contentValues.put(myDbHelper.ALLOWANCE_ENERGY, intake.getEnergy());
        contentValues.put(myDbHelper.ALLOWANCE_PROTEIN, intake.getProtein());
        contentValues.put(myDbHelper.ALLOWANCE_TOTAL_FAT, intake.getTotal_fat());
        contentValues.put(myDbHelper.ALLOWANCE_ESSENTIAL_FATTY_ACID, intake.getEssentialFattyAcid());
        contentValues.put(myDbHelper.ALLOWANCE_DIETARY, intake.getDietaryFiber());
        contentValues.put(myDbHelper.ALLOWANCE_WATER, intake.getWater());
        long id = db.update(myDbHelper.TABLE_NAME_ALLOWANCE, contentValues,"fam_guid=?",new String[]{intake.getFamId()} );
        Log.d("updateIntakeData", contentValues.get(myDbHelper.ALLOWANCE_ENERGY).toString());
        db.close();
        return id;
    }

    public void deleteFamilyData(ModelsFamily family) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + myDbHelper.TABLE_NAME_FAMILY + " WHERE " + myDbHelper.FAMUID + "='" + family.getFamilyUID() + "'");
        db.close();
    }

    public void deleteAllergyData(ModelsFamily family) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + myDbHelper.TABLE_NAME_ALLERGY + " WHERE " + myDbHelper.FAM_GUID + "='" + family.getFamily_GUID() + "'");
        db.close();
    }

    public void deleteContact(ModelsContact contact) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + myDbHelper.TABLE_NAME_CONTACT + " WHERE " + myDbHelper.CONTACT_NUMBER + "='" + contact.getContactNumber() + "'");
        db.close();
    }


    public List<ModelsUser> getUserData() {
        List<ModelsUser> list = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {
                myDbHelper.UID,
                myDbHelper.ACCOUNT_ID,
                myDbHelper.EMAIL,
                myDbHelper.FIRST_NAME,
                myDbHelper.LAST_NAME,
                myDbHelper.MIDDLE_NAME,
                myDbHelper.PASSWORD,
                myDbHelper.USERNAME};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME_ACCOUNT, columns, null, null, null, null, null);
//        StringBuffer buffer = new StringBuffer();
        Log.e("bilang", cursor.getCount() + "");
        while (cursor.moveToNext()) {

            ModelsUser modelsUser = new ModelsUser();
//            String cid = String.valueOf(cursor.getInt(cursor.getColumnIndex(myDbHelper.UID)));
            String accountID = cursor.getString(cursor.getColumnIndex(myDbHelper.ACCOUNT_ID));
            String email = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            String firstName = cursor.getString(cursor.getColumnIndex(myDbHelper.FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(myDbHelper.LAST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndex(myDbHelper.MIDDLE_NAME));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.PASSWORD));
            String username = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
//            modelsUser.setUserUID(cid);
            modelsUser.setEmail(email);
            modelsUser.setUserUID(accountID);
            modelsUser.setFamily(getFamilyData(modelsUser));
            modelsUser.setFirstName(firstName);
            modelsUser.setLastName(lastName);
            modelsUser.setMiddleName(middleName);
            modelsUser.setPassword(password);
            modelsUser.setUsername(username);
            list.add(modelsUser);
        }
        db.close();
        return list;
    }




    public List<ModelsFamily> getFamilyData(ModelsUser user) {
        List<ModelsFamily> list = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {
                myDbHelper.FAMUID,
                myDbHelper.FAM_GUID,
                myDbHelper.ACCOUNT_ID,
                myDbHelper.BIRTHDAY,
                myDbHelper.GENDER,
                myDbHelper.HEIGHT,
                myDbHelper.IMAGE_ID,
                myDbHelper.FAM_NAME,
                myDbHelper.PREGNANT,
                myDbHelper.RELATION,
                myDbHelper.WEIGHT,
        };
        Cursor cursor = db.query(myDbHelper.TABLE_NAME_FAMILY, columns, null, null, null, null, null);
//        StringBuffer buffer = new StringBuffer();
        Log.e("familyCount", cursor.getCount() + "");
        while (cursor.moveToNext()) {
            ModelsFamily famMember = new ModelsFamily();
//            String cid = String.valueOf(cursor.getInt(cursor.getColumnIndex(myDbHelper.FAMUID)));
            String accountID = cursor.getString(cursor.getColumnIndex(myDbHelper.ACCOUNT_ID));

            if (accountID.equals(user.getUserUID())) {


                String family_id = cursor.getString(cursor.getColumnIndex(myDbHelper.FAMUID));
                String family_guid = cursor.getString(cursor.getColumnIndex(myDbHelper.FAM_GUID));
                String birthday = cursor.getString(cursor.getColumnIndex(myDbHelper.BIRTHDAY));
                String gender = cursor.getString(cursor.getColumnIndex(myDbHelper.GENDER));
                String height = cursor.getString(cursor.getColumnIndex(myDbHelper.HEIGHT));
                String image_id = cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGE_ID));
                String fam_name = cursor.getString(cursor.getColumnIndex(myDbHelper.FAM_NAME));
                String pregrant = cursor.getString(cursor.getColumnIndex(myDbHelper.PREGNANT));
                String relation = cursor.getString(cursor.getColumnIndex(myDbHelper.RELATION));
                String weight = cursor.getString(cursor.getColumnIndex(myDbHelper.WEIGHT));

                famMember.setFamilyUID(family_id);
                famMember.setFamily_GUID(family_guid);
                famMember.setAccountUID(accountID);
                famMember.setBirthday(birthday);

                if (gender.equals("1")) {
                    famMember.setGender(true);
                } else {
                    famMember.setGender(false);
                }
                famMember.setHeight(Double.valueOf(height));
                famMember.setImageResourcesId(Integer.valueOf(image_id));
                famMember.setName(fam_name);
                if (pregrant.equals("1")) {
                    famMember.setPregnant(true);
                } else {
                    famMember.setPregnant(false);
                }
                famMember.setRelation(relation);
                famMember.setWeight(Double.valueOf(weight));


                if (famMember.getFamily_GUID() != null) {
                    ArrayList<ModelsAllergy> allergies = new ArrayList<>();
                    allergies.addAll(getAllergyData(famMember));
                    famMember.setAllergylist(allergies);
                }


                list.add(famMember);
            }
        }
        return list;
    }


    public List<ModelsAllergy> getAllergyData(ModelsFamily family) {
        List<ModelsAllergy> list = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {
                myDbHelper.ALLERGY_UID,
                myDbHelper.FAM_GUID,
                myDbHelper.ALLERGY,
        };
        Cursor cursor = db.query(myDbHelper.TABLE_NAME_ALLERGY, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ModelsAllergy modelsAllergy = new ModelsAllergy();
            String cid = String.valueOf(cursor.getInt(cursor.getColumnIndex(myDbHelper.ALLERGY_UID)));
            String FAMGUID = cursor.getString(cursor.getColumnIndex(myDbHelper.FAM_GUID));
            String allergy = cursor.getString(cursor.getColumnIndex(myDbHelper.ALLERGY));
            Log.e("Allergy", allergy + " " + FAMGUID + " " + cid);
            if (FAMGUID.equals(family.getFamily_GUID())) {
                modelsAllergy.setUid(cid);
                modelsAllergy.setFamilyGUID(FAMGUID);
                modelsAllergy.setAllergyName(allergy);
                list.add(modelsAllergy);

            }


        }
        return list;
    }


    public List<ModelsContact> getContactData() {
        List<ModelsContact> list = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {
                myDbHelper.CONTACT_UID,
                myDbHelper.ACCOUNT_ID,
                myDbHelper.CONTACT_NAME,
                myDbHelper.CONTACT_NUMBER,
                myDbHelper.CONTACT_RELATIONSHIP,
        };
        Cursor cursor = db.query(myDbHelper.TABLE_NAME_CONTACT, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ModelsContact mContact = new ModelsContact();
            String cid = String.valueOf(cursor.getInt(cursor.getColumnIndex(myDbHelper.CONTACT_UID)));
            String accountID = cursor.getString(cursor.getColumnIndex(myDbHelper.ACCOUNT_ID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.CONTACT_NAME));
            String number = cursor.getString(cursor.getColumnIndex(myDbHelper.CONTACT_NUMBER));
            String relationdhip = cursor.getString(cursor.getColumnIndex(myDbHelper.CONTACT_RELATIONSHIP));

            if (logUser.getUserUID().equals(accountID)) {
                mContact.setContactUID(cid);
                mContact.setAccountID(accountID);
                mContact.setContactName(name);
                mContact.setContactNumber(number);
                mContact.setContactRelation(relationdhip);

                list.add(mContact);

            }
        }
        return list;
    }


    public boolean checkUserExist(String username, String password) {
        String[] columns = {myDbHelper.ACCOUNT_ID};
        SQLiteDatabase db = myhelper.getWritableDatabase();

        String selection = "username=? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(myDbHelper.TABLE_NAME_ACCOUNT, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();


        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }


    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME_ACCOUNT = "USER";   // Table Name
        private static final String TABLE_NAME_FAMILY = "FAMILY";   // Table Name
        private static final String TABLE_NAME_ALLERGY = "ALLERGY";   // Table Name
        private static final String TABLE_NAME_CONTACT = "CONTACT";   // Table Name
        private static final String TABLE_NAME_ALLOWANCE = "CONTACT";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version

        //        ----------------USER ACCOUNT------------------------
        private static final String UID = "user_uid";     // Column I (Primary Key)
        private static final String ACCOUNT_ID = "accountID";     //
        private static final String EMAIL = "email";    //
        private static final String FIRST_NAME = "firstName";    //
        private static final String LAST_NAME = "lastName";    //
        private static final String MIDDLE_NAME = "middleName";    //
        private static final String PASSWORD = "password";    //
        private static final String USERNAME = "username";    //

        //    ------------------- FAMILY -----------------------
        private static final String FAMUID = "fam_uid";     // Column I (Primary Key)
        private static final String FAM_GUID = "fam_guid";     // Column I (Primary Key)
        private static final String BIRTHDAY = "birthday";     //
        private static final String GENDER = "gender";     //
        private static final String HEIGHT = "hEight";     //
        private static final String IMAGE_ID = "imageResourcesId";     //
        private static final String FAM_NAME = "NAME";     //
        private static final String PREGNANT = "pregnant";     //
        private static final String RELATION = "relation";     //
        private static final String WEIGHT = "weight";     //

        //    ------------------- ALLERGY -----------------------
        private static final String ALLERGY_UID = "allergy_uid";     // Column I (Primary Key)
        private static final String ALLERGY = "allergy";     //


        //    ------------------- CONTACT -----------------------
        private static final String CONTACT_UID = "contact_uid";     // Column I (Primary Key)
        private static final String CONTACT_NAME = "contact_name";     //
        private static final String CONTACT_NUMBER = "contact_number";     //
        private static final String CONTACT_RELATIONSHIP = "contact_relationship";     //

        //    ------------------- ALLOWANCE_LEFT -----------------------
        private static final String ALLOWANCE_UID = "allowance_uid";     // Column I (Primary Key)
        private static final String ALLOWANCE_ENERGY = "allowance_energy";     //
        private static final String ALLOWANCE_PROTEIN = "allowance_protein";     //
        private static final String ALLOWANCE_TOTAL_FAT = "allowance_total_fat";     //
        private static final String ALLOWANCE_ESSENTIAL_FATTY_ACID = "allowance_essential_fatty_acid";     //
        private static final String ALLOWANCE_DIETARY = "allowance_dietary";     //
        private static final String ALLOWANCE_WATER = "allowance_water";     //


        private static final String CREATE_ALLOWANCE_LEFT_TABLE = "CREATE TABLE " + TABLE_NAME_ALLOWANCE +
                " (" + ALLOWANCE_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + ACCOUNT_ID + " VARCHAR(255) ," +
                "" + FAM_GUID + " VARCHAR(255) ," +
                "" + ALLOWANCE_ENERGY + " VARCHAR(255) ," +
                "" + ALLOWANCE_PROTEIN + " VARCHAR(255) ," +
                "" + ALLOWANCE_TOTAL_FAT + " VARCHAR(255) ," +
                "" + ALLOWANCE_ESSENTIAL_FATTY_ACID + " VARCHAR(255) ," +
                "" + ALLOWANCE_DIETARY + " VARCHAR(255) ," +
                "" + ALLOWANCE_WATER + " VARCHAR(225));";


        private static final String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME_CONTACT +
                " (" + CONTACT_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + ACCOUNT_ID + " VARCHAR(255) ," +
                "" + CONTACT_NAME + " VARCHAR(255) ," +
                "" + CONTACT_NUMBER + " VARCHAR(255) ," +
                "" + CONTACT_RELATIONSHIP + " VARCHAR(225));";


        private static final String CREATE_ALLERGY_TABLE = "CREATE TABLE " + TABLE_NAME_ALLERGY +
                " (" + ALLERGY_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + FAM_GUID + " VARCHAR(255) ," +
                "" + ALLERGY + " VARCHAR(225));";


        private static final String CREATE_FAMILY_TABLE = "CREATE TABLE " + TABLE_NAME_FAMILY +
                " (" + FAMUID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + FAM_GUID + " VARCHAR(255) ," +
                "" + ACCOUNT_ID + " VARCHAR(255) ," +
                "" + BIRTHDAY + " VARCHAR(225)," +
                "" + GENDER + " INTEGER(10)," +
                "" + HEIGHT + " VARCHAR(225)," +
                "" + IMAGE_ID + " VARCHAR(225)," +
                "" + FAM_NAME + " VARCHAR(225)," +
                "" + PREGNANT + " INTEGER(10)," +
                "" + RELATION + " VARCHAR(225)," +
                "" + WEIGHT + " VARCHAR(225));";


        private static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_NAME_ACCOUNT +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + ACCOUNT_ID + " VARCHAR(255) ," +
                "" + EMAIL + " VARCHAR(255) ," +
                "" + FIRST_NAME + " VARCHAR(225)," +
                "" + LAST_NAME + " VARCHAR(225)," +
                "" + MIDDLE_NAME + " VARCHAR(225)," +
                "" + PASSWORD + " VARCHAR(225)," +
                "" + USERNAME + " VARCHAR(225));";


        private static final String DROP_TABLE_ACCOUNT = "DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT;
        private static final String DROP_TABLE_FAMILY = "DROP TABLE IF EXISTS " + TABLE_NAME_FAMILY;
        private static final String DROP_TABLE_ALLERGY = "DROP TABLE IF EXISTS " + TABLE_NAME_ALLERGY;
        private static final String DROP_TABLE_CONTACT = "DROP TABLE IF EXISTS " + TABLE_NAME_CONTACT;
        private static final String DROP_TABLE_ALLOWANCE = "DROP TABLE IF EXISTS " + TABLE_NAME_ALLOWANCE;
        private Context context;


        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_ACCOUNT_TABLE);
                db.execSQL(CREATE_FAMILY_TABLE);
                db.execSQL(CREATE_ALLERGY_TABLE);
                db.execSQL(CREATE_CONTACT_TABLE);
                db.execSQL(CREATE_ALLOWANCE_LEFT_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE_ACCOUNT);
                db.execSQL(DROP_TABLE_FAMILY);
                db.execSQL(DROP_TABLE_ALLERGY);
                db.execSQL(DROP_TABLE_CONTACT);
                db.execSQL(DROP_TABLE_ALLOWANCE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}