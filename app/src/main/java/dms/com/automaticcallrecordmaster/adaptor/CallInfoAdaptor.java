package dms.com.automaticcallrecordmaster.adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dms.com.automaticcallrecordmaster.CallPlayer;
import dms.com.automaticcallrecordmaster.R;
import dms.com.automaticcallrecordmaster.core.CallRecordInfo;
import dms.com.automaticcallrecordmaster.util.Utils;

/**
 * Created by rpeela on 12/9/15.
 */
public class CallInfoAdaptor extends RecyclerView.Adapter<CallInfoAdaptor.CallInfoHolder> {

    private List<CallRecordInfo> callRecordInfos;
    private Context mContext;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CallInfoHolder holder = (CallInfoHolder) view.getTag();
            int position = holder.getPosition();
            CallRecordInfo callRecordInfo = callRecordInfos.get(position);
            Intent playIntent = new Intent(mContext, CallPlayer.class); //Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(new File(callRecordInfo.getFilePath()));
            playIntent.setData(uri);
            mContext.startActivity(playIntent);
        }
    };


    public CallInfoAdaptor(Context context, List<CallRecordInfo> feedItemList) {
        this.callRecordInfos = feedItemList;
        this.mContext = context;
    }

    @Override
    public CallInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_info_list_item, null);
        CallInfoHolder viewHolder = new CallInfoHolder(view);
        view.setTag(viewHolder);
        view.setOnClickListener(clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CallInfoHolder holder, int position) {
        holder.phone_or_name.setText(callRecordInfos.get(position).getMobileNumber());
        holder.caller_name_start_letter.setText(callRecordInfos.get(position).getMobileNumber());
        CallRecordInfo updatedCallInfo = Utils.retrieveContactRecord(mContext, callRecordInfos.get(position).getMobileNumber(), callRecordInfos.get(position));
        //Existing Contact
        if (updatedCallInfo.getPhoneContactId() != 0) {
            holder.no_profile_view.setVisibility(View.GONE);
            holder.profile_view.setVisibility(View.VISIBLE);
            holder.phone_or_name.setText(callRecordInfos.get(position).getCallerName());
            new ImageLoader(holder.profileImage).execute(updatedCallInfo.getPhoneContactId());
        } else { // For Non Existing Contact
            holder.no_profile_view.setVisibility(View.VISIBLE);
            holder.profile_view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return callRecordInfos.size();
    }

    class CallInfoHolder extends RecyclerView.ViewHolder {
        protected TextView phone_or_name, caller_name_start_letter, time_and_duration;
        protected FrameLayout profile_view, no_profile_view;
        protected CircleImageView profileImage;

        public CallInfoHolder(View view) {
            super(view);
            this.phone_or_name = (TextView) view.findViewById(R.id.phone_or_name);
            this.caller_name_start_letter = (TextView) view.findViewById(R.id.caller_name_start_letter);
            this.time_and_duration = (TextView) view.findViewById(R.id.time_and_duration);
            this.profile_view = (FrameLayout) view.findViewById(R.id.profile_view);
            this.no_profile_view = (FrameLayout) view.findViewById(R.id.no_profile_view);
            this.profileImage = (CircleImageView) view.findViewById(R.id.profile_image);
        }
    }


    public class ImageLoader extends AsyncTask<Long, Void, Bitmap> {

        private WeakReference<ImageView> weakImageView;

        public ImageLoader(ImageView imageView) {
            weakImageView = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Long... params) {
            return Utils.retrieveContactPhoto(mContext, params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid == null) {
                ImageView imageView = weakImageView.get();
                if (imageView != null)
                    imageView.setImageBitmap(aVoid);
            }
        }
    }

}
