//package dms.com.automaticcallrecordmaster.constants;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//
//import dms.com.automaticcallrecordmaster.R;
//
//
///**
// * Created by rpeela on 10/10/15.
// */
//public class FragmentConstents {
//
//    public static void addFragmenttoDisplay(FragmentActivity activity, Fragment fragment, String fragmentName) {
//        FragmentManager manager = activity.getSupportFragmentManager();
//        if (hasFragmnet(manager, fragment)) {
//            removeFragment(manager, fragment, false);
//            addFragment(manager, fragment, false);
//        } else {
//            addFragment(manager, fragment, true);
//        }
//    }
//
//    /**
//     * Add from bottom and remove from bottom
//     *
//     * @param activity
//     * @param fragment
//     */
//    public static void addFragmentFromBottom(FragmentActivity activity, Fragment fragment) {
//        FragmentManager manager = activity.getSupportFragmentManager();
//        if (hasFragmnet(manager, fragment)) {
//            removeFragment(manager, fragment, R.anim.slide_in_from_botom, R.anim.slide_out_to_bottom);
//        } else {
//            addFragment(manager, fragment, R.anim.slide_in_from_botom, R.anim.slide_out_to_bottom);
//        }
//    }
//
////    public static void addFragment(FragmentManager manager, Fragment fragment, int... animations) {
////        FragmentTransaction ft = manager.beginTransaction();
////        if (animations[0] != 0 & animations[1] != 0)
////            ft.setCustomAnimations(animations[0], animations[0]);
////        ft.add(R.id.container, fragment);
////        ft.addToBackStack(null);
////        ft.commit();
////    }
//
////    public static void addFragment(FragmentManager manager, Fragment fragment, boolean needAnimation) {
////        FragmentTransaction ft = manager.beginTransaction();
////        if (needAnimation)
////            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
////        ft.add(R.id.container, fragment);
////        ft.addToBackStack(null);
////        ft.commit();
////    }
//
//    /**
//     * Default animation remove from Right Out
//     *
//     * @param manager
//     * @param fragment
//     * @param needAnimation
//     */
//    public static void removeFragment(FragmentManager manager, Fragment fragment, boolean needAnimation) {
//        if (needAnimation)
//            removeFragment(manager, fragment, R.anim.slide_in_left, R.anim.slide_out_right);
//        else
//            removeFragment(manager, fragment, 0, 0);
//    }
//
//    public static void removeFragment(FragmentManager manager, Fragment fragment, int... animations) {
//        if (hasFragmnet(manager, fragment)) {
//            FragmentTransaction ftTemp = manager.beginTransaction();
//            if (animations[0] != 0 & animations[1] != 0)
//                ftTemp.setCustomAnimations(animations[0], animations[0]);
//            ftTemp.remove(fragment);
//            ftTemp.commit();
//        }
//    }
//
//
//    public static void removeFragmentReverseAnimation(FragmentManager manager, Fragment fragment) {
//        if (hasFragmnet(manager, fragment)) {
//            FragmentTransaction ftTemp = manager.beginTransaction();
//            ftTemp.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//            ftTemp.remove(fragment);
//            ftTemp.commit();
//        }
//    }
//
//    public static boolean hasFragmnet(FragmentManager manager, Fragment fragment) {
//        if (fragment.isAdded())
//            return true;
//        return false;
//    }
//
//
//}
