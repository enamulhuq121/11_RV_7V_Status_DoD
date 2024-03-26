package com.icddrb.enamapppractice;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "participantManager";
    private static final String TABLE_PARTICIPANT = "participants";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_GENDER = "gender";

    private static final String KEY_STATUS = "status";
    private static final String KEY_DOB = "date_of_birth";
    private static final String KEY_DATE_OF_DEATH = "date_of_death";

    private static final String KEY_OCCUPATION = "occupation";
    private static final String KEY_HOBBIES = "hobbies";


    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PARTICIPANT_TABLE = "CREATE TABLE " + TABLE_PARTICIPANT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_GENDER + " TEXT,"
                + KEY_STATUS + " TEXT,"
                + KEY_DOB + " DATE,"
                + KEY_DATE_OF_DEATH + " DATE,"
                + KEY_OCCUPATION + " TEXT,"
                + KEY_HOBBIES + " TEXT"+")";
        db.execSQL(CREATE_PARTICIPANT_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPANT);
        onCreate(db);
    }


    public void addParticipant(String name, String gender,String status, String date_of_birth, String date_of_death,  String occupation, String hobbies) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GENDER, gender);
        values.put(KEY_STATUS, status);
        values.put(KEY_DOB, date_of_birth);
        values.put(KEY_DATE_OF_DEATH, date_of_death);
        values.put(KEY_OCCUPATION, occupation);
        values.put(KEY_HOBBIES, hobbies);

        db.insert(TABLE_PARTICIPANT, null, values);
        db.close();
    }


    @SuppressLint("Range")
    public String getParticipantHobbies(int participantId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_HOBBIES};
        String selection = KEY_ID + "=?";
        String[] selectionArgs = {String.valueOf(participantId)};
        Cursor cursor = db.query(TABLE_PARTICIPANT, columns, selection, selectionArgs, null, null, null);
        String hobbies = "";
        if (cursor.moveToFirst()) {
            hobbies = cursor.getString(cursor.getColumnIndex(KEY_HOBBIES));
        }
        cursor.close();
        return hobbies;
    }




    public List<ParticipantModal> readParticipants() {
        List<ParticipantModal> participantList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String[] columns = {KEY_ID, KEY_NAME, KEY_GENDER, KEY_STATUS,KEY_DOB,KEY_DATE_OF_DEATH, KEY_OCCUPATION, KEY_HOBBIES};
            cursor = db.query(TABLE_PARTICIPANT, columns, null, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String gender = cursor.getString(2);
                    String status = cursor.getString(3);
                    String date_of_birth = cursor.getString(4);
                    String date_of_death = cursor.getString(5);
                    String occupation = cursor.getString(6);
                    String hobbies = cursor.getString(7);

                    ParticipantModal participant = new ParticipantModal(id, name, gender,status, date_of_birth,date_of_death, occupation, hobbies);
                    participantList.add(participant);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return participantList;
    }





    public void updateParticipant(int id, String newName, String newGender, String newStatus, String newDate_of_birth,  String newDate_of_death, String newOccupation, String newHobbies) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newName);
        values.put(KEY_GENDER, newGender);
        values.put(KEY_STATUS, newStatus);
        values.put(KEY_DOB, newDate_of_birth);
        values.put(KEY_DATE_OF_DEATH, newDate_of_death);
        values.put(KEY_OCCUPATION, newOccupation);
        values.put(KEY_HOBBIES, newHobbies);

        db.update(TABLE_PARTICIPANT, values, KEY_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
    }







    public ParticipantModal getParticipant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PARTICIPANT, new String[] { KEY_ID, KEY_NAME, KEY_GENDER, KEY_STATUS, KEY_DOB, KEY_DATE_OF_DEATH,  KEY_OCCUPATION, KEY_HOBBIES},
                KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int nameIndex = cursor.getColumnIndex(KEY_NAME);
            int genderIndex = cursor.getColumnIndex(KEY_GENDER);
            int statusIndex = cursor.getColumnIndex(KEY_STATUS);
            int dobIndex = cursor.getColumnIndex(KEY_DOB);
            int dateOfDeathIndex = cursor.getColumnIndex(KEY_DATE_OF_DEATH);
            int occupationIndex = cursor.getColumnIndex(KEY_OCCUPATION);
            int hobbiesIndex = cursor.getColumnIndex(KEY_HOBBIES);


            if (idIndex != -1 && nameIndex != -1 && genderIndex != -1 && statusIndex != -1 && dobIndex != -1 && dateOfDeathIndex != -1 && occupationIndex != -1 && hobbiesIndex != -1 ) {
                ParticipantModal participant = new ParticipantModal(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(genderIndex),
                        cursor.getString(statusIndex),
                        cursor.getString(dobIndex),
                        cursor.getString(dateOfDeathIndex),
                        cursor.getString(occupationIndex),
                        cursor.getString(hobbiesIndex));

                cursor.close();
                return participant;
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return null; // Return null if participant data is not found or columns are not available
    }

    public void deleteParticipant(String participantName){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_PARTICIPANT,"name=?",new String[]{participantName});
        db.close();
    }



}
