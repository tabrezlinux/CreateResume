package com.example.tabrezahmad.createresume;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.MyRoomDatabase;

public class FinalActivity extends AppCompatActivity
        implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    public static MyRoomDatabase mDatabase;     // room database
    ViewPager viewPager;                        // view pager
    MyPagerAdapter myPagerAdapter;              // custom pager adapter
    TabLayout tabLayout;                        // tab layout

    public static Long BASIC_INFO_FOREIGN_KEY_ID = null;
    public static int TOTAL_TABS = 0;

    // ON CREATE ACTIVITY --------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // SET CONTENT LAYOUT
        setContentView(R.layout.activity_final);

        // INIT DATABASE
        setupDatabase();

        // INIT VIEWS
        setupView();

    }


    // DATABASE ------------------------------------------------------------------------------------
    private void setupDatabase() {

        // creating room database
        mDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, "mDatabase").build();

        Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();

    }


    // TOOLBAR AND FAB  ----------------------------------------------------------------------------
    private void setupFabAndToolbar() {

        // toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }


    // VIEW SETUP   --------------------------------------------------------------------------------
    private void setupView() {

        // TOOLBAR AND FAB SETUP
        setupFabAndToolbar();

        // PAGER ADAPTER SETUP
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.mPager);
        viewPager.setAdapter(myPagerAdapter);
        TOTAL_TABS = viewPager.getAdapter().getCount();
        viewPager.addOnPageChangeListener(this);


        // TAB ITEMS SETUP
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Picture and Sign"));
        tabLayout.addTab(tabLayout.newTab().setText("Reference"));

        // TABLAYOUT POSITION SETUP
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        // ON TAB SELECTION
        tabLayout.addOnTabSelectedListener(this);

    }


    // BACK BUTTON PRESS    ------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    // OPTIONS MENU --------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // TAB ITEM EVENTS  ----------------------------------------------------------------------------
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // get viewpager page at index = tab index
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    // PAGER SCROLLING EVENTS   --------------------------------------------------------------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    // CLICK LISTENER    ---------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (viewPager.getCurrentItem() < (TOTAL_TABS - 1)) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    //Toast.makeText(this,"CUR " + viewPager.getCurrentItem() + "COUNT " + TOTAL_TABS,Toast.LENGTH_SHORT).show();
                } else {
                    // Preview Activity
                    Intent intent = new Intent(FinalActivity.this, PreviewActivity.class);
                    //Bundle b = new Bundle();
                    //b.putLong("FOREIGN_KEY", BASIC_INFO_FOREIGN_KEY_ID);
                    startActivity(intent);
                }
                break;
        }
    }

    // PAGER ADAPTER    ----------------------------------------------------------------------------
    public class MyPagerAdapter extends FragmentPagerAdapter {

        // constructor method
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PhotoAndSignFrag();
                case 1:
                    return new ReferenceFrag();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}

