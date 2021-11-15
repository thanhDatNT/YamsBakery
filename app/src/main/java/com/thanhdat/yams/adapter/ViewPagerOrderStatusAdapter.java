package com.thanhdat.yams.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thanhdat.yams.Fragments.PendingOrderFragment;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;
import com.thanhdat.yams.Fragments.ShippingOrderFragment;

public class ViewPagerOrderStatusAdapter extends FragmentStatePagerAdapter {
    public ViewPagerOrderStatusAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PendingOrderFragment();
            case 1:
                return new ShippingOrderFragment();
            case 2:
                return new PreviousOrderFragment();

            default:
                return new PendingOrderFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Chờ xác nhận";
                break;
            case 1:
                title = "Đang giao";
                break;
            case 2:
                title = "Lịch sử";
                break;
        }
        return title;
    }
}
