package dms.com.automaticcallrecordmaster.adaptor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chandrashekhar.m on 16-11-2015.
 */
public class SampleFragmentPagerAdapter  extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    final int PAGE_COUNT = 3;
    private List<String> mFragmentTitles;
    private Context context;
    private List<Integer> mFragmentIcons;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        mFragments = new ArrayList<>();
        mFragmentTitles = new ArrayList<>();
        mFragmentIcons = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title, int drawable) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        mFragmentIcons.add(drawable);
    }



}
