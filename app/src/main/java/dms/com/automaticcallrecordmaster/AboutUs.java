package dms.com.automaticcallrecordmaster;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.InjectView;
import dms.com.automaticcallrecordmaster.ui.view.RevealBackgroundView;

public class AboutUs extends BaseActivity {


    @InjectView(R.id.vRevealBackground)
    RevealBackgroundView vRevealBackground;
    @InjectView(R.id.rvUserProfile)
    RecyclerView rvUserProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setupRevealBackground(savedInstanceState, vRevealBackground);
    }


    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            rvUserProfile.setVisibility(View.VISIBLE);
        } else {
            rvUserProfile.setVisibility(View.INVISIBLE);
        }
    }
}