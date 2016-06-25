package dms.com.automaticcallrecordmaster.core;

/**
 * Created by rpeela on 12/8/15.
 */
public class CallRecordInfo {

    public static final short TYPE_INCOMING = 1;
    public static final short TYPE_OUTGOING = 2;

    private long id, phoneContactId;
    private long duration;
    private long startTime;
    private String callerName;
    private String mobileNumber;
    private String filePath;
    private short callType;


    public short getCallType() {
        return callType;
    }

    public void setCallType(short callType) {
        this.callType = callType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public long getPhoneContactId() {
        return phoneContactId;
    }

    public void setPhoneContactId(long phoneContactId) {
        this.phoneContactId = phoneContactId;
    }
}
