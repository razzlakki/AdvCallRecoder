package dms.com.automaticcallrecordmaster.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rpeela on 12/9/15.
 */
public class SharedPrefrenceDataHolder {

    private static String sharedPrefrenceKey = "com.dms.callrecoder.sharedprefrence";
    public static String INCOMING_NUMBER_KEY = "INCOMING_KEY";
    public static String OUT_GOING_NUMBER_KEY = "OUTGOING_KEY";
    public static String START_TIME_KEY = "START_TIME";
    public static String CALL_TYPE = "CALL_TYPE";
    public static String CALL_PATH = "CALL_FILE_PATH";


    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(sharedPrefrenceKey, Context.MODE_PRIVATE);
//        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setSharedPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setSharedPreferencesLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static String getSharedPreferences(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }

    public static long getLongSharedPreferences(Context context, String key) {
        return getSharedPreferences(context).getLong(key, 0);
    }


    public static void setSharedPreferencesInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntSharedPreferences(Context context, String key) {
        return getSharedPreferences(context).getInt(key, 0);
    }


}
