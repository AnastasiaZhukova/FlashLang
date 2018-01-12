package com.github.anastasiazhukova.flashlang.utils;

public final class ViewPagerUtils {

    public static String getFragmentTag(final int pViewPagerId, final int pPosition) {
        return "android:switcher:" + pViewPagerId + ":" + pPosition;
    }

}
