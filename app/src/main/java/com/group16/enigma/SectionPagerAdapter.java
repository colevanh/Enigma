package com.group16.enigma;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hayde on 22-Oct-16.
 */


public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Returns the fragment depending on the position in the tab layout
        switch (position) {
            case 0:
                return new FriendsListFragment();
            case 1:
                return new ChatListFragment();
            case 2:
                return new SettingsFragment();
            default:
                //return new SecondTabFragment();
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Sets title for each tab
        switch (position) {
            case 0:
                return "Friends";
            case 1:
                return "Chat";
            case 2:
                return "Settings";
            default:
                return "";
        }
    }
}