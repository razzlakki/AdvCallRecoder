package dms.com.automaticcallrecordmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dms.com.automaticcallrecordmaster.ui.view.RevealBackgroundView;

public class Preferences extends PreferenceActivity implements RevealBackgroundView.OnStateChangeListener {

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    static public final String PREF_RECORD_CALLS = "PREF_RECORD_CALLS";
    static public final String PREF_AUDIO_SOURCE = "PREF_AUDIO_SOURCE";
    static public final String PREF_AUDIO_FORMAT = "PREF_AUDIO_FORMAT";

    @InjectView(R.id.vRevealBackground)
    RevealBackgroundView vRevealBackground;
    @InjectView(android.R.id.list)
    ListView list_view;


    SharedPreferences prefs;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.userpreferences);
        setContentView(R.layout.preferences_layout);
        ButterKnife.inject(this);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setupToolBar();
        setupRevealBackground(savedInstanceState, vRevealBackground);
    }

    private void setupToolBar() {
        if (toolbar != null) {
            toolbar.setTitle(R.string.action_settings);
            toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTextColor));
            toolbar.setNavigationIcon(R.drawable.arrowback);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }


    protected void setupRevealBackground(Bundle savedInstanceState, final RevealBackgroundView vRevealBackground) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
    }

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            list_view.setVisibility(View.VISIBLE);
            vRevealBackground.setVisibility(View.GONE);
        } else {
            list_view.setVisibility(View.INVISIBLE);
        }
    }
}
