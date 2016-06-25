package dms.com.automaticcallrecordmaster.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import dms.com.automaticcallrecordmaster.R;
import dms.com.automaticcallrecordmaster.adaptor.SampleFragmentPagerAdapter;

/**
 * Created by Raja.p on 23-12-2015.
 */
public class SavedFragment extends BaseFragment {

    private static SavedFragment instance;

    public static SavedFragment newInstance() {
        if (instance == null)
            instance = new SavedFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inbox_fragment, container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        initData(view);
        return view;
    }

    private void initData(View view) {
        SampleFragmentPagerAdapter pageAdapter = new SampleFragmentPagerAdapter(getFragmentManager(), getActivity());
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        pageAdapter.addFragment(AllCalls.newInstance(1), "     All Calls      ", R.drawable.addtolist);
        pageAdapter.addFragment(IncomingCalls.newInstance(2), "     Incoming Calls     ", R.drawable.addtolist);
        pageAdapter.addFragment(OutgoingCalls.newInstance(3), "    Outgoing Calls     ", R.drawable.addtolist);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
       /* BaseFragment baseFragment = (BaseFragment) pageAdapter.getItem(0);
        baseFragment.onOnload();*/
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSmoothScrollingEnabled(true);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab1 = tabLayout.getTabAt(i);
            //tab1.setCustomView(pageAdapter.getTabView(i));
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
