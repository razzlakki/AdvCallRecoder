package dms.com.automaticcallrecordmaster.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

import dms.com.automaticcallrecordmaster.core.CallRecordInfo;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class Utils {
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static CallRecordInfo retrieveContactRecord(Context context, String phoneNo, CallRecordInfo callRecordInfo) {
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNo));
            String[] projection = new String[]{ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.DISPLAY_NAME};
            String selection = null;
            String[] selectionArgs = null;
            String sortOrder = ContactsContract.PhoneLookup.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
            ContentResolver cr = context.getContentResolver();
            if (cr != null) {
                Cursor resultCur = cr.query(uri, projection, selection, selectionArgs, sortOrder);
                if (resultCur != null) {
                    while (resultCur.moveToNext()) {
                        callRecordInfo.setCallerName(resultCur.getString(resultCur.getColumnIndexOrThrow(ContactsContract.PhoneLookup.DISPLAY_NAME)));
                        callRecordInfo.setPhoneContactId(resultCur.getLong(resultCur.getColumnIndex(ContactsContract.PhoneLookup._ID)));
                        break;
                    }
                    resultCur.close();
                }
            }
        } catch (Exception sfg) {
            Log.e("Error", "Error in loadContactRecord : " + sfg.toString());
        }
        return callRecordInfo;
    }//fn retrieveContactRecord


    public static Bitmap retrieveContactPhoto(Context context, long contactID) {

        Bitmap photo = null;
        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }
}
