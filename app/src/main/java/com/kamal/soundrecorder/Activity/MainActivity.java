package com.kamal.soundrecorder.Activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kamal.soundrecorder.R;
import com.astuetz.PagerSlidingTabStrip;
import com.kamal.soundrecorder.Fragments.FileViewerFragment;
import com.kamal.soundrecorder.Fragments.RecordFragment;

public class MainActivity extends BaseActivity {

    private TabLayout tab;
    private PagerSlidingTabStrip tabss;
    private ViewPager pager;

    @Override
    protected void initViews() {
        setupToolbar("Sound");
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MainActivity.MyAdapter(getSupportFragmentManager()));
        tab = findViewById(R.id.tabs);
//        tabs.setViewPager(pager);
        tab.setupWithViewPager(pager);

        for (int i = 0; i < tab.getTabCount(); i++) {

            TabLayout.Tab tabL = tab.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(this);
                tabL.setCustomView(tabTextView);
                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.setText(tabL.getText());
                if (i == 0) {
                    tabTextView.setTextSize(24);  //default tab text size
                    tabTextView.setTextColor(getColor(R.color.black));
                }
            }
        }

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabs) {
                ViewGroup vg = (ViewGroup) tab.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabs.getPosition());
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTextSize(24); //tab text size when selected
                        ((TextView) tabViewChild).setTextColor(getColor(R.color.black));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tabs) {
                ViewGroup vg = (ViewGroup) tab.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabs.getPosition());
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTextSize(18); //tab text size when unselected
                        ((TextView) tabViewChild).setTextColor(getColor(R.color.grey_tab));
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tabs) {
                tab.clearOnTabSelectedListeners();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {
        private String[] titles = {getString(R.string.tab_title_record),
                getString(R.string.tab_title_saved_recordings)};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return RecordFragment.newInstance(position);
                }
                case 1: {
                    return FileViewerFragment.newInstance(position);
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}