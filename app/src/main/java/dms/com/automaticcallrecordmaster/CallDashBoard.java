package dms.com.automaticcallrecordmaster;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import butterknife.InjectView;
import dms.com.automaticcallrecordmaster.adaptor.HomeTabsAdapter;
import dms.com.automaticcallrecordmaster.adaptor.LeftNavigationAdaptor;
import dms.com.automaticcallrecordmaster.fragment.InboxFragment;
import dms.com.automaticcallrecordmaster.fragment.SavedFragment;

public class CallDashBoard extends BaseActivity {

    //    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    //    @InjectView(R.id.tabs)
    TabLayout tabs;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private HomeTabsAdapter pageAdapter;
    private ListView leftDrawerList;
    protected String[] myDataset = {"Call Stats", "Settings", "Feedback", "About Us", "Contact Us"};
    private LeftNavigationAdaptor mAdapter;
    private int currentFragment = 1;
    private InboxFragment inBoxFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_dash_board);
        mAdapter = new LeftNavigationAdaptor(this, myDataset);
        inBoxFragment = InboxFragment.newInstance();
        initHeaderRadio(savedInstanceState);
        initGUI(savedInstanceState);

    }

    private void initHeaderRadio(final Bundle savedInstanceState) {
        LinearLayout radio_layout = (LinearLayout) findViewById(R.id.radio_layout);
        LayoutInflater influator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view1 = influator.inflate(R.layout.radio_item, null);
        view1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        radio_layout.addView(view1);
        ((ImageView) view1.findViewById(R.id.radio_icon)).setImageResource(R.drawable.voice_inbox);
        final View view2 = influator.inflate(R.layout.radio_item, null);
        view2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        ((ImageView) view2.findViewById(R.id.radio_icon)).setImageResource(R.drawable.saved_inbox_inactive);
        radio_layout.addView(view2);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragment == 2) {
                    ((ImageView) view1.findViewById(R.id.radio_icon)).setImageResource(R.drawable.voice_inbox);
                    ((ImageView) view2.findViewById(R.id.radio_icon)).setImageResource(R.drawable.saved_inbox_inactive);
                    currentFragment = 1;
                    updateFragment();
                }
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragment == 1) {
                    ((ImageView) view1.findViewById(R.id.radio_icon)).setImageResource(R.drawable.voice_inbox_inactive);
                    ((ImageView) view2.findViewById(R.id.radio_icon)).setImageResource(R.drawable.saved_inbox);
                    currentFragment = 2;
                    updateFragment();
                }
            }
        });

    }

    private void updateFragment() {

    }


    private void initGUI(Bundle savedInstanceState) {

        findViewById(R.id.menuicon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


        leftDrawerList = (ListView) findViewById(R.id.list_drawer);
        leftDrawerList.setAdapter(mAdapter);
        setUpFragments();
        leftDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveMenuItem(view, position);
                mDrawerLayout.closeDrawers();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    private void setUpFragments() {
        addFragment(getSupportFragmentManager(), inBoxFragment, R.id.container, true);
    }


    private void moveMenuItem(final View view, final int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] startingLocation = new int[2];
                view.getLocationOnScreen(startingLocation);
                startingLocation[0] += view.getWidth() / 2;
                Class<?> destination = null;
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        destination = Preferences.class;
                        break;
                    case 2:
                        break;

                    default:
                        break;
                }
                if (destination != null) {
                    AboutUs.startActivityFromLocation(startingLocation, CallDashBoard.this, destination);
                    overridePendingTransition(0, 0);
                }
            }
        }, 200);
    }
}
