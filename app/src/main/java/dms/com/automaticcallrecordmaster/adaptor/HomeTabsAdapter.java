package dms.com.automaticcallrecordmaster.adaptor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dms.com.automaticcallrecordmaster.R;

/**
 * Created by Raja.p on 23-12-2015.
 */
public class HomeTabsAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
    private List<Integer> mFragmentIcons = new ArrayList<>();

    public HomeTabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    public void addFragment(Fragment fragment, int drawable) {
        mFragments.add(fragment);
        mFragmentIcons.add(drawable);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    public View getTabView(int position) {

        View tab = LayoutInflater.from(mContext).inflate(R.layout.image_tab_item, null);
        ImageView tabImage = (ImageView) tab.findViewById(R.id.cartbutton);
        tabImage.setBackgroundResource(mFragmentIcons.get(position));
        if (position == 0) {
            tab.setSelected(true);
        }
        return tab;
    }
}