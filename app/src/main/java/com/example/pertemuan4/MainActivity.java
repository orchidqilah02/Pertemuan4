
package com.example.pertemuan4;

import android.os.Bundle;
import com.example.pertemuan4.ui.AkademikPagerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private AkademikPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        pagerAdapter = new AkademikPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Mahasiswa");
                    } else {
                        tab.setText("Dosen");
                    }
                }
        ).attach();
    }
}
