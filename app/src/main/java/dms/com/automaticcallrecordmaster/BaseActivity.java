package dms.com.automaticcallrecordmaster;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;

import java.util.List;

import butterknife.ButterKnife;
import dms.com.automaticcallrecordmaster.ui.view.RevealBackgroundView;

/**
 * Created by rpeela on 11/30/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements RevealBackgroundView.OnStateChangeListener {

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static void startActivityFromLocation(int[] startingLocation, AppCompatActivity startingActivity, Class<?> destination) {
        Intent intent = new Intent(startingActivity, destination);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
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

    protected void initFragments(Bundle savedInstanceState, Fragment... fragments) {
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_top);
            for (Fragment fragment : fragments) {
                ft.add(R.id.container, fragment);
            }
            //ft.addToBackStack(null);
            ft.commit();
        }
    }


//    protected void initFragments(Bundle savedInstanceState, Fragment... fragments) {
//        if (savedInstanceState == null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_top);
//            for (Fragment fragment : fragments) {
//                ft.add(R.id.container, fragment);
//            }
//            //ft.addToBackStack(null);
//            ft.commit();
//        }
//    }

    public void hideActionBar() {
        getSupportActionBar().setShowHideAnimationEnabled(false);
        getSupportActionBar().hide();
    }

    public void showActionbarWithColor(int color, String title) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setShowHideAnimationEnabled(false);
        getSupportActionBar().show();

    }

    @Override
    public void onStateChange(int state) {

    }

    public static void addFragmenttoDisplay(FragmentActivity activity, Fragment fragment, String fragmentName) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if (hasFragmnet(manager, fragment)) {
            removeFragment(manager, fragment, false);
            addFragment(manager, fragment, false);
        } else {
            addFragment(manager, fragment, true);
        }
    }

    public static void replaceFragmentToDisplay(FragmentActivity activity, Fragment fragment, String fragmentName, Bundle savedBundle) {
        FragmentManager manager = activity.getSupportFragmentManager();
        List<Fragment> allFragments = manager.getFragments();
        FragmentTransaction ft = manager.beginTransaction();
        if (allFragments != null)
            for (int i = 0; i < allFragments.size(); i++) {
                ft.remove(allFragments.get(i));
            }
        ft.commit();
        FragmentTransaction ft1 = manager.beginTransaction();
        ft1.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft1.add(R.id.container, fragment, fragmentName);
        // ft1.addToBackStack(null);
        ft1.commit();
    }

    public static void addFragment(FragmentManager manager, Fragment fragment, boolean needAnimation) {
        FragmentTransaction ft = manager.beginTransaction();
        if (needAnimation)
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.add(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void addFragment(FragmentManager manager, Fragment fragment, int container, boolean needAnimation) {
        FragmentTransaction ft = manager.beginTransaction();
        if (needAnimation)
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.add(container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Default animation remove from Right Out
     *
     * @param manager
     * @param fragment
     * @param needAnimation
     */
    public static void removeFragment(FragmentManager manager, Fragment fragment, boolean needAnimation) {
        if (needAnimation)
            removeFragment(manager, fragment, R.anim.slide_in_left, R.anim.slide_out_right);
        else
            removeFragment(manager, fragment, 0, 0);
    }

    public static void removeFragment(FragmentManager manager, Fragment fragment, int... animations) {
        if (hasFragmnet(manager, fragment)) {
            FragmentTransaction ftTemp = manager.beginTransaction();
            if (animations[0] != 0 & animations[1] != 0)
                ftTemp.setCustomAnimations(animations[0], animations[0]);
            ftTemp.remove(fragment);
            ftTemp.commit();
        }
    }


    public static boolean hasFragmnet(FragmentManager manager, Fragment fragment) {

//        List<Fragment> allFragments = manager.getFragments();
//        if (allFragments != null)
//            for (int i = 0; i < allFragments.size(); i++) {
//                if (allFragments.get(i).equals(fragment)) {
//                    Log.e("Is Added", " *** Added Here");
//                    return true;
//                }
//            }
//        return false;

        if (fragment.isAdded()) {
            Log.e("Is Added", " *** Added Here");
            return true;
        }
        Log.e("Is Added", " *** Not Added Here");
        return false;
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }
}
