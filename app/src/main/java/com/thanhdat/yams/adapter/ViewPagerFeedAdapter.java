package com.thanhdat.yams.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thanhdat.yams.Fragments.FeedFragment;
import com.thanhdat.yams.Fragments.HomeFragment;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;

public class ViewPagerFeedAdapter extends FragmentPagerAdapter {
    public ViewPagerFeedAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public ViewPagerFeedAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: ;
//            case 2:
            case 3: return new FeedFragment();
//            case 4:
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
