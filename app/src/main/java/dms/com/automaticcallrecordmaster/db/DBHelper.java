package dms.com.automaticcallrecordmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dms.com.automaticcallrecordmaster.core.CallRecordInfo;

/**
 * Created by rpeela on 12/8/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "automatic_call_recorder.db";
    public static final String CALL_RECORD_TABLE = "recorded_calls";
    public static final String RECORD_TABLE_ID = "_id";
    public static final String RECORD_TABLE_PHONE_CONTACT_ID = "phone_contact_id";
    public static final String RECORD_TABLE_CALLER_NAME = "Caller_name";
    public static final String RECORD_TABLE_CALLER_MOBILE_NUMBER = "mobile_number";
    public static final String RECORD_TABLE_DURATION = "duration";
    public static final String RECORD_TABLE_START_TIME = "call_start_time";
    public static final String RECORD_TABLE_CALL_TYPE = "call_type";
    public static final String RECORD_TABLE_PATH = "recorded_path";

    private static DBHelper instance;


    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static DBHelper getInstatnce(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + CALL_RECORD_TABLE +
                        "(" + RECORD_TABLE_ID + " integer primary key, " + RECORD_TABLE_PHONE_CONTACT_ID + " integer, "+ RECORD_TABLE_CALLER_NAME + " text," + RECORD_TABLE_CALLER_MOBILE_NUMBER + " text," + RECORD_TABLE_DURATION + " integer, " + RECORD_TABLE_START_TIME + " integer, " + RECORD_TABLE_CALL_TYPE + " integer, " + RECORD_TABLE_PATH + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean insertQuestions(CallRecordInfo callRecordInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORD_TABLE_PHONE_CONTACT_ID, callRecordInfo.getPhoneContactId());
        contentValues.put(RECORD_TABLE_CALLER_NAME, callRecordInfo.getCallerName());
        contentValues.put(RECORD_TABLE_CALLER_MOBILE_NUMBER, callRecordInfo.getMobileNumber());
        contentValues.put(RECORD_TABLE_START_TIME, callRecordInfo.getStartTime());
        contentValues.put(RECORD_TABLE_DURATION, callRecordInfo.getDuration());
        contentValues.put(RECORD_TABLE_CALL_TYPE, callRecordInfo.getCallType());
        contentValues.put(RECORD_TABLE_PATH, callRecordInfo.getFilePath());
        db.insert(CALL_RECORD_TABLE, null, contentValues);
        return true;
    }

//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
//        return res;
//    }

    public int getQuestionsCount(String where) {
        SQLiteDatabase db = this.getReadableDatabase();
        int totalCount = 0;
        Cursor cursor = db.query(CALL_RECORD_TABLE, new String[]{"count(*) AS count"}, where, null, null, null,
                null);
        cursor.moveToFirst();
        totalCount = cursor.getInt(0);
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return totalCount;
    }

    public boolean updateContact(CallRecordInfo callRecordInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORD_TABLE_PHONE_CONTACT_ID, callRecordInfo.getPhoneContactId());
        contentValues.put(RECORD_TABLE_CALLER_NAME, callRecordInfo.getCallerName());
        contentValues.put(RECORD_TABLE_CALLER_MOBILE_NUMBER, callRecordInfo.getMobileNumber());
        contentValues.put(RECORD_TABLE_START_TIME, callRecordInfo.getStartTime());
        contentValues.put(RECORD_TABLE_DURATION, callRecordInfo.getDuration());
        contentValues.put(RECORD_TABLE_CALL_TYPE, callRecordInfo.getCallType());
        contentValues.put(RECORD_TABLE_PATH, callRecordInfo.getFilePath());
        db.update(CALL_RECORD_TABLE, contentValues, "_id = ? ", new String[]{Long.toString(callRecordInfo.getId())});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CALL_RECORD_TABLE,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }


    /**
     * It Will Return Descending order.
     *
     * @param where
     * @return
     */
    public ArrayList<CallRecordInfo> getAllCallRecordingInfos(String where) {
        ArrayList<CallRecordInfo> array_list = new ArrayList<CallRecordInfo>();

        if (where != null)
            where = " where " + where;
        else
            where = "";

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();

//        Cursor res = db.rawQuery("select * from " + CALL_RECORD_TABLE + where, null);
        Cursor res = db.query(CALL_RECORD_TABLE, null, null, null, null, null, RECORD_TABLE_ID + " DESC");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            CallRecordInfo callRecordInfo = new CallRecordInfo();
            callRecordInfo.setId(res.getInt(res.getColumnIndex(RECORD_TABLE_ID)));
            callRecordInfo.setPhoneContactId(res.getInt(res.getColumnIndex(RECORD_TABLE_PHONE_CONTACT_ID)));
            callRecordInfo.setCallerName(res.getString(res.getColumnIndex(RECORD_TABLE_CALLER_NAME)));
            callRecordInfo.setMobileNumber(res.getString(res.getColumnIndex(RECORD_TABLE_CALLER_MOBILE_NUMBER)));
            callRecordInfo.setStartTime(res.getLong(res.getColumnIndex(RECORD_TABLE_START_TIME)));
            callRecordInfo.setDuration(res.getLong(res.getColumnIndex(RECORD_TABLE_DURATION)));
            callRecordInfo.setCallType(res.getShort(res.getColumnIndex(RECORD_TABLE_CALL_TYPE)));
            callRecordInfo.setFilePath(res.getString(res.getColumnIndex(RECORD_TABLE_PATH)));
            array_list.add(callRecordInfo);
            res.moveToNext();
        }
        if (res != null && !res.isClosed()) {
            res.close();
        }

        return array_list;
    }
}