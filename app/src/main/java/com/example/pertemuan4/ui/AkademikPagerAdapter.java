
package com.example.pertemuan4.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AkademikPagerAdapter extends FragmentStateAdapter {

    public AkademikPagerAdapter(
            @NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new MahasiswaFragment();
        }
        return new DosenFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
