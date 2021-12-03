<<<<<<< Updated upstream:app/src/main/java/com/thanhdat/yams/Adapter/ViewPagerMainAdapter.java
package com.thanhdat.yams.Adapter;
=======
package com.thanhdat.yams.Adapters;
>>>>>>> Stashed changes:app/src/main/java/com/thanhdat/yams/Adapters/ViewPagerMainAdapter.java

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.thanhdat.yams.Fragments.DietFragment;

import com.thanhdat.yams.Fragments.FavoriteFragment;
import com.thanhdat.yams.Fragments.FeedFragment;

import com.thanhdat.yams.Fragments.HomeFragment;
import com.thanhdat.yams.Fragments.ProfileFragment;

public class ViewPagerMainAdapter extends FragmentStatePagerAdapter {
    public ViewPagerMainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: return new FavoriteFragment();
            case 2: return new DietFragment();
            case 3: return new FeedFragment();
            case 4: return new ProfileFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
