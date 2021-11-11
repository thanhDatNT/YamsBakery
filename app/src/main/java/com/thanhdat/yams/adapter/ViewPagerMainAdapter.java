package com.thanhdat.yams.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thanhdat.yams.Fragments.HomeFragment;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;

public class ViewPagerMainAdapter extends FragmentStatePagerAdapter {
    public ViewPagerMainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: ;
//            case 2:
//            case 3:
//            case 4:
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
