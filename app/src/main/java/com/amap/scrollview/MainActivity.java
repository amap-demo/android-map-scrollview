package com.amap.scrollview;

import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.TextureSupportMapFragment;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    ScrollViewPager mViewPager;
    List<String> mTitleList = new ArrayList<String>();
    List<Fragment> mFragments = new ArrayList<Fragment>();
    TextureSupportMapFragment mMapFragment;
    PagerSlidingTabStrip tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ScrollViewPager) findViewById(R.id.view_page);

        mTitleList.add("Tab1");
        mTitleList.add("地图");
        mTitleList.add("Tab2");

        TestFragment testOne = new TestFragment();
        mFragments.add(testOne);

        try {
            MapsInitializer.initialize(this.getApplicationContext());  //初始化TextureSupportMapFragment需要先设置context
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mMapFragment = TextureSupportMapFragment.newInstance();
        mFragments.add(mMapFragment);

        TestTwoFragment testTwo = new TestTwoFragment();
        mFragments.add(testTwo);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }
        };
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setShouldExpand(true);
        tabs.setIndicatorColorResource(R.color.colorPrimary);
        tabs.setViewPager(mViewPager);
    }
}
