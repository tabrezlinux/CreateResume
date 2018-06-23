package com.example.tabrezahmad.createresume;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{



   // private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ListView listView = (ListView)findViewById(R.id.personal_info_list_view);
//
//        ArrayList<Integer> arrayList = new ArrayList<>();
//
//        arrayList.add(R.id.name);
//        arrayList.add(R.id.dob);
//
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.list_content,arrayList);
//        listView.setAdapter(arrayAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
       // tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
     //   tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
   //     tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));

  //      tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);






        //creating drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




//        FragmentManager fragmentManager = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        PersonalInfoFrag fragment = new PersonalInfoFrag();
//        fragmentTransaction.add(R.id.main_fragment, fragment);
//        fragmentTransaction.commit();

     setupView();



        //creating room database
        //final MyRoomDatabase mDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class,"mDatabase").build();

        //final User u = new User();
        //u.name = "mush";

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mDatabase.UserDao().insertUsers(u);
//                final User user  = mDatabase.UserDao().getUser();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),"record inserted ID=" + user.uid, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        t.start();


        Toast.makeText(this,"database created", Toast.LENGTH_SHORT).show();


    }

   private void setupView() {

       // Get the ViewPager and set it's PagerAdapter so that it can display items
       final ViewPager viewPager = (ViewPager) findViewById(R.id.mPager);
       final MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),MainActivity.this);
       viewPager.setAdapter(myPagerAdapter);
       // Give the TabLayout the ViewPager
       final TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
       tabLayout.addTab(tabLayout.newTab().setText("Basic Info"));
       tabLayout.addTab(tabLayout.newTab().setText("Educational Qualification"));
       tabLayout.addTab(tabLayout.newTab().setText("Work Experience"));
       tabLayout.addTab(tabLayout.newTab().setText("Project"));
       tabLayout.addTab(tabLayout.newTab().setText("Industrial Exposure"));
       tabLayout.addTab(tabLayout.newTab().setText("Inplant Training Attended"));
       tabLayout.addTab(tabLayout.newTab().setText("Internship"));
       tabLayout.addTab(tabLayout.newTab().setText("Skills"));
       tabLayout.addTab(tabLayout.newTab().setText("Strength"));
       tabLayout.addTab(tabLayout.newTab().setText("Hobbies"));
       tabLayout.addTab(tabLayout.newTab().setText("Designation"));
       tabLayout.addTab(tabLayout.newTab().setText("Career Objective"));
       tabLayout.addTab(tabLayout.newTab().setText("Area/Field Of Interest"));
       tabLayout.addTab(tabLayout.newTab().setText("Extra Activities"));
       tabLayout.addTab(tabLayout.newTab().setText("Curricular Activities"));
       tabLayout.addTab(tabLayout.newTab().setText("Achievements & Awards"));
       tabLayout.addTab(tabLayout.newTab().setText("Photo & Sign"));
       tabLayout.addTab(tabLayout.newTab().setText("Reference"));

       tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
       tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

               tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                   @Override
                   public void onTabSelected(TabLayout.Tab tab) {
                       // get tab index
                       //tab.getPosition();

                       // get viewpager page at index = tab index
                       viewPager.setCurrentItem(tab.getPosition());
                       // select view at index
                   }

                   @Override
                   public void onTabUnselected(TabLayout.Tab tab) {

                   }

                   @Override
                   public void onTabReselected(TabLayout.Tab tab) {

                   }
               });

       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
       });

    //  ViewPager mPager = (ViewPager) findViewById(R.id.mPager);
    //  MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
    //  mPager.setAdapter(myPagerAdapter);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm, MainActivity mainActivity){
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return new PersonalInfoFrag();
            if(position == 1)
                return new EductionFrag();
            if(position == 2)
                return new WorkExperienceFrag();
            if(position == 3)
                return new ProjectFrag();
            if(position == 4)
                return new IndustrialExposureFrag();
            if(position == 5)
                return new InplantTrainingAttendedFrag();
            if(position == 6)
                return new InternshipFrag();
            if(position == 7)
                return new SkillsFrag();
            if(position == 8)
                return new StrengthFrag();
            if(position == 9)
                return new HobbiesFrag();
            if(position == 10)
                return new DesignationFrag();
            if(position == 11)
                return new CareerObjectiveFrag();
            if(position == 12)
                return new AreaOfInterestFrag();
            if(position == 13)
                return new ExtraActivitiesFrag();
            if(position == 14)
                return new CurricularActivitiesFrag();
            if(position == 15)
                return new AchievementsFrag();
            if(position == 16)
                return new PhotoSignFrag();
            if(position == 17)
                return new ReferenceFrag();


            return null;
        }

        @Override
        public int getCount() {
            return 18;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "1";
                case 1:
                    return "2";
                case 2:
                    return "3";
                case 3:
                    return "4";
                case 4:
                    return "5";
                case 5:
                    return "6";
                case 6:
                    return "7";
                case 7:
                    return "8";
                case 8:
                    return "9";
                case 9:
                    return "10";
                case 10:
                    return "11";
                case 11:
                    return "12";

        }
        return null;
    }


}}

