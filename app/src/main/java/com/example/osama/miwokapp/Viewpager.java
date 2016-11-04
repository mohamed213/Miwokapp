package com.example.osama.miwokapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by osama on 9/4/2016.
 */
public class Viewpager extends FragmentPagerAdapter {
    public Viewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
        {
            return new NumberFragment();
        }else if (position==1){
            return new FamilyFragment();

        }else if (position == 2){
            return new ColorFragment();
        }else  {
            return new PhraseFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       switch (position){
           case 0 :
               return "Number";
           case 1 :
               return "Family";
           case 2:
               return "Color";
           default:
               return "Phrase";

       }

        }
    }

