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

    //推荐使用TextureSupportMapFragment 解决滑动黑边问题
    TextureSupportMapFragment mMapFragment;

    //使用开源Tab github 开源地址 https://github.com/astuetz/PagerSlidingTabStrip
    PagerSlidingTabStrip mTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ScrollViewPager) findViewById(R.id.view_page);

        //三个Tab
        mTitleList.add("Tab1");
        mTitleList.add("地图");
        mTitleList.add("Tab2");

        //Tab1
        TestFragment testOne = new TestFragment();
        mFragments.add(testOne);

        //Tab 地图
        try {
            MapsInitializer.initialize(this.getApplicationContext());  //初始化TextureSupportMapFragment需要先设置context
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mMapFragment = TextureSupportMapFragment.newInstance();

        mFragments.add(mMapFragment);

        //Tab2
        TestTwoFragment testTwo = new TestTwoFragment();
        mFragments.add(testTwo);

        //viewPage 设置adapter
        mViewPager.setAdapter(adapter);

        //viewPage设置默认展示Tab
        mViewPager.setCurrentItem(1);

        // Tab
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //设置Tab均分整个width
        mTabs.setShouldExpand(true);
        //绑定Tab和ViewPage
        mTabs.setViewPager(mViewPager);
        //设置Tab下滚动条颜色
        mTabs.setIndicatorColorResource(R.color.colorPrimary);

    }

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
}
