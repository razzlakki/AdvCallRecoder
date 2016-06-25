package dms.com.automaticcallrecordmaster.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import dms.com.automaticcallrecordmaster.Preferences;
import dms.com.automaticcallrecordmaster.core.CallRecordInfo;
import dms.com.automaticcallrecordmaster.db.SharedPrefrenceDataHolder;
import dms.com.automaticcallrecordmaster.listener.PhoneListener;

public class CallBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.d("CallRecorder", "CallBroadcastReceiver::onReceive got Intent: " + intent.toString());
        String outGoingNumber = null;
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            outGoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            //Toast.makeText(context,"OutGoing Number"+numberToCall,Toast.LENGTH_LONG).show();
            SharedPrefrenceDataHolder.setSharedPreferences(context, SharedPrefrenceDataHolder.OUT_GOING_NUMBER_KEY, outGoingNumber);
            SharedPrefrenceDataHolder.setSharedPreferencesInt(context, SharedPrefrenceDataHolder.CALL_TYPE, CallRecordInfo.TYPE_OUTGOING);
            Log.d("CallRecorder", "CallBroadcastReceiver intent has EXTRA_PHONE_NUMBER: " + outGoingNumber);
        }
        Boolean shouldRecord = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(Preferences.PREF_RECORD_CALLS, false);
        if(shouldRecord) {
            PhoneListener phoneListener = new PhoneListener(context);
            TelephonyManager telephony = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
            Log.d("PhoneState::onReceive", "set PhoneStateListener");
        }
    }
}
