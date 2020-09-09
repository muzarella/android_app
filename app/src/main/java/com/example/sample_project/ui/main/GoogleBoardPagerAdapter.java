package com.example.sample_project.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sample_project.R;
//import com.example.sample_project.Skills_iqFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class GoogleBoardPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private  Context mContext;
    TabLayout mTabLayout ;

    private final List<Fragment> lstFragment = new ArrayList<>() ;
    private final List<String> lstTitles  = new ArrayList<>() ;

    public GoogleBoardPagerAdapter( FragmentManager fm) {
        super(fm);
    }

  /*  public GoogleBoardPagerAdapter( FragmentManager fm , Context context ) {
        super(fm);
        mContext = context ;
    }*/

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
      //  return PlaceholderFragment.newInstance(position + 1);
        /*Fragment fragment = null;
        if (position == 0)
        {
            fragment = new learn_hourFragment();
        }
        else if (position == 1)
        {
            fragment = new skills_iq_fragment();
        }
        return fragment;*/

         return lstFragment.get(position) ;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      //  return mContext.getResources().getString(TAB_TITLES[position]);
       /* String title = null;
        if (position == 0)
        {
            title = mContext.getResources().getString(R.string.tab_learning_leader);
        }
        else if (position == 1)
        {
            title = mContext.getResources().getString(R.string.tab_skill_iq_leader);
        }
        return title;*/

        return lstTitles.get(position) ;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
      //  return 2;

        return lstTitles.size() ;
    }

    public void AddFragment(Fragment fragment , String title){
        lstFragment.add(fragment) ;
        lstTitles.add(title) ;
    }
}