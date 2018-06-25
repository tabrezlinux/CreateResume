package com.example.tabrezahmad.createresume;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.MyRoomDatabase;
import com.example.tabrezahmad.createresume.database.Entity.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    MyRoomDatabase mDatabase;           // room database
    ViewPager viewPager;                // view pager
    MyPagerAdapter myPagerAdapter;      // custom pager adapter
    TabLayout tabLayout;                // tab layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. setup database, // should be initialized first
        setupDatabase();

        // 2. setup views
        setupView();

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    private void setupDatabase() {

        // creating room database
        mDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, "mDatabase").build();

        final User u = new User();
        u.name = "mush";

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mDatabase.UserDao().insertUsers(u);
                final User user = mDatabase.UserDao().getUser();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "record inserted ID=" + user.uid, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        t.start();

        Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void setupFabAndToolbar() {

        // toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab setup
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // setup drawer layout and navigation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // navigation view setup
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setupView() {

        // setup toolbar and fab
        setupFabAndToolbar();

        // view pager adapter
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), MainActivity.this);

        // setup ViewPager and set PagerAdapter
        viewPager = (ViewPager) findViewById(R.id.mPager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        // set TabLayout tab items
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Basic Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Academic Qualification"));
        tabLayout.addTab(tabLayout.newTab().setText("Work Experience"));
        tabLayout.addTab(tabLayout.newTab().setText("Photo & Sign"));
        tabLayout.addTab(tabLayout.newTab().setText("Reference"));

        // positioning tabLayout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // on tab click listener
        tabLayout.addOnTabSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case  R.id.nav_send:
                break;
        }

        // close drawer layout after clicking
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    // TabLayout tab selection events
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

    // ViewPager page scrolling events
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

    // custom pager adapter ////////////////////////////////////////////////////////////////////////
    public class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm, MainActivity mainActivity) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BasicInfoFrag();
                case 1:
                    return new AcademicQualificationFrag();
                case 2:
                    return new WorkExperienceFrag();
                case 3:
                    return new PhotoAndSignFrag();
                case 4:
                    return new ReferenceFrag();
                default:
                    return null;
            }
            /*
            if (position == 1)
                return new AcademicQualificationFrag();
            if (position == 2)
                return new WorkExperienceFrag();
            if (position == 3)
                return new ProjectsAndTrainingsFrag();
            if (position == 4)
                return new IndustrialExposureFrag();
            if (position == 5)
                return new InplantTrainingAttendedFrag();
            if (position == 6)
                return new InternshipFrag();
            if (position == 7)
                return new SkillsFrag();
            if (position == 8)
                return new StrengthFrag();
            if (position == 9)
                return new HobbiesFrag();
            if (position == 10)
                return new DesignationFrag();
            if (position == 11)
                return new CareerObjectiveFrag();
            if (position == 12)
                return new AreaOfInterestFrag();
            if (position == 13)
                return new ExtraActivitiesFrag();
            if (position == 14)
                return new CurricularActivitiesFrag();
            if (position == 15)
                return new AchievementsFrag();
            if (position == 16)
                return new PhotoAndSignFrag();
            if (position == 17)
                return new ReferenceFrag();
            */
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


}

