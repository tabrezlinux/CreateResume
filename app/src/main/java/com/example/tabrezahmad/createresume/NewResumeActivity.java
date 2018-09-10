package com.example.tabrezahmad.createresume;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.AcademicQualification;
import com.example.tabrezahmad.createresume.database.Entity.AchievementsAndAwards;
import com.example.tabrezahmad.createresume.database.Entity.Activities;
import com.example.tabrezahmad.createresume.database.Entity.AreaOfInterest;
import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.Entity.CareerObjective;
import com.example.tabrezahmad.createresume.database.Entity.Hobby;
import com.example.tabrezahmad.createresume.database.Entity.Internship;
import com.example.tabrezahmad.createresume.database.Entity.PhotoAndSign;
import com.example.tabrezahmad.createresume.database.Entity.ProfessionalQualification;
import com.example.tabrezahmad.createresume.database.Entity.Project;
import com.example.tabrezahmad.createresume.database.Entity.Reference;
import com.example.tabrezahmad.createresume.database.Entity.Skill;
import com.example.tabrezahmad.createresume.database.Entity.Strength;
import com.example.tabrezahmad.createresume.database.Entity.TemplateChoice;
import com.example.tabrezahmad.createresume.database.Entity.WorkExperience;
import com.example.tabrezahmad.createresume.database.MyRoomDatabase;

import java.util.ArrayList;

public class NewResumeActivity extends AppCompatActivity
        implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private MyRoomDatabase mDatabase;                   // room database
    private ViewPager viewPager;                        // view pager
    private MyPagerAdapter myPagerAdapter;              // custom pager adapter
    private TabLayout tabLayout;                        // tab layout

    private String NAME = null;
    private int TOTAL_TABS = 0;

    private Toast toast = null;


    // ENTITY MODELS -------------------------------------------------------------------------------
    public static BasicInfo BASIC_INFO;
    public static ArrayList<AcademicQualification> ACADEMIC_QUA = new ArrayList<>();
    public static ArrayList<ProfessionalQualification> PROFESSIONAL_QUA = new ArrayList<>();
    public static ArrayList<Project> PROJECT = new ArrayList<>();
    public static ArrayList<Internship> INTERNSHIP = new ArrayList<>();
    public static ArrayList<WorkExperience> WORK_EXPERIENCE = new ArrayList<>();

    public static ArrayList<AchievementsAndAwards> ACHIEVEMENT_AWARD = new ArrayList<>();
    public static ArrayList<Activities> ACTIVITIES = new ArrayList<>();
    public static ArrayList<Skill> SKILL = new ArrayList<>();
    public static ArrayList<Hobby> HOBBY = new ArrayList<>();
    public static ArrayList<Strength> STRENGTH = new ArrayList<>();
    public static ArrayList<AreaOfInterest> AREA_OF_INTEREST = new ArrayList<>();

    public static CareerObjective CAREER_OBJECTIVE;
    public static PhotoAndSign PHOTO_SIGN;
    public static Reference REFERENCE;

    public TemplateChoice TEMPLATE_CHOICE;
    private View root;
    private ProgressBar progressBar;


    // ACTIVITY METHODS ----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. if no intent value then finish the activity, we cannot save data without its parent key
        Bundle b = getIntent().getExtras();
        if (b == null) {
            finish();
            Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
        }
        // if intent has name value
        if ((NAME = b.getString("name")) == null) {
            finish();
            Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
        }


        // 2. else continue, open database, create entities and load fragments and viewpager
        // SET CONTENT LAYOUT
        setContentView(R.layout.activity_new);

        progressBar = findViewById(R.id.progressBar);

        // SETUP DATABASE
        setupDatabase();

        // CREATE NEW ENTRY IN DATABASE from intent values
        new RecordCreateAsyncTask().execute();

        // SETUP VIEWS
        setupView();

    }

    @Override
    protected void onPause() {
        super.onPause();

        // SAVE ENTRY IN DATABASE from intent values
        new RecordSaveAsyncTask().execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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


    // CLICK LISTENER ------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            // swap pager to next tab item, if tab item reaches end
            // then open the final activity for template view or to add photo
            case R.id.fab:
                if (viewPager.getCurrentItem() < (TOTAL_TABS - 1)) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    //Toast.makeText(this,"CUR " + viewPager.getCurrentItem() + "COUNT " + TOTAL_TABS,Toast.LENGTH_SHORT).show();
                } else {
                    finalDialog();
                }
                break;
            default:
                break;
        }
    }


    // CUSTOM METHODS ------------------------------------------------------------------------------
    // database setup
    private void setupDatabase() {

        // creating room database
        mDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, "mDatabase").build();
        //Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();

    }

    // toolbar and fab
    private void setupFabAndToolbar() {

        // toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }

    // views setup
    private void setupView() {

        root = findViewById(R.id.root_layout);

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
        tabLayout.addTab(tabLayout.newTab().setText("Basic Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Academics"));
        tabLayout.addTab(tabLayout.newTab().setText("Professional"));
        tabLayout.addTab(tabLayout.newTab().setText("Project"));
        tabLayout.addTab(tabLayout.newTab().setText("Internship"));
        tabLayout.addTab(tabLayout.newTab().setText("Experience"));
        tabLayout.addTab(tabLayout.newTab().setText("Achievement"));
        tabLayout.addTab(tabLayout.newTab().setText("Career Obj."));

        // TABLAYOUT POSITION SETUP
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // ON TAB SELECTION
        tabLayout.addOnTabSelectedListener(this);

    }

    // photo-sign pick dialog
    private void finalDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage("Add Picture or Reference");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(NewResumeActivity.this, FinalActivity.class);
                //Bundle b = new Bundle();
                //b.putLong("FOREIGN_KEY", BI_FOREIGN_KEY_ID);
                startActivity(intent);

            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Template Activity
                Intent intent = new Intent(NewResumeActivity.this, TemplatePreviewActivity.class);
                //Bundle b = new Bundle();
                //b.putLong("FOREIGN_KEY", BI_FOREIGN_KEY_ID);
                startActivity(intent);
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();

    }


    // METHODS FOR CHILDCHILD FRAGMENTS ------------------------------------------------------------
    // get database ref
    public MyRoomDatabase getMyDatabase() {
        return mDatabase;
    }

    // show toast
    public void showToast(String msg) {
        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();

    }


    // ASYNC DATA INSERT ---------------------------------------------------------------------------
    class RecordCreateAsyncTask extends AsyncTask<String, Integer, BasicInfo> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected BasicInfo doInBackground(String... strings) {

            // create an empty basic info object, insert into database and get insert id
            // create all entity in database using basic info id
            BasicInfo bi = new BasicInfo();
            bi.name = NAME;

            //--------------------------------------------------------------------------------------
            AcademicQualification academicQualification = new AcademicQualification();
            ProfessionalQualification professionalQualification = new ProfessionalQualification();
            Project project = new Project();
            Internship internship = new Internship();
            WorkExperience workExperience = new WorkExperience();

            AchievementsAndAwards achievementsAndAwards = new AchievementsAndAwards();
            Activities activities = new Activities();
            Skill skill = new Skill();
            Hobby hobby = new Hobby();
            AreaOfInterest areaOfInterest = new AreaOfInterest();
            Strength strength = new Strength();

            CareerObjective careerObjective = new CareerObjective();
            Reference reference = new Reference();
            PhotoAndSign photoAndSign = new PhotoAndSign();

            //TemplateChoice templateChoice = new TemplateChoice();


            // 1. start database transaction
            // 2. set transaction successful if all data inserted
            // 3. end transaction

            Long insertId = mDatabase.BasicInfoDAO().insert(bi);

            // if basic_info inserted then create all entities
            // else finish this activity
            if (insertId != null) {

                mDatabase.beginTransaction(); // --------------------------------------------------- begin transaction
                Log.d("ROOM create", "in Transaction");

                academicQualification.f_key = insertId;
                mDatabase.AcademicQuaDAO().insert(academicQualification);

                professionalQualification.f_key = insertId;
                mDatabase.ProfessionalQuaDAO().insert(professionalQualification);

                project.f_key = insertId;
                mDatabase.ProjectDAO().insert(project);

                internship.f_key = insertId;
                mDatabase.InternshipDAO().insert(internship);

                workExperience.f_key = insertId;
                mDatabase.WorkExperienceDAO().insert(workExperience);

                achievementsAndAwards.f_key = insertId;
                mDatabase.AchievementDAO().insert(achievementsAndAwards);

                activities.f_key = insertId;
                mDatabase.ActivitiesDAO().insert(activities);

                skill.f_key = insertId;
                mDatabase.SkillDAO().insert(skill);

                hobby.f_key = insertId;
                mDatabase.hobbyDAO().insert(hobby);

                strength.f_key = insertId;
                mDatabase.StrengthDAO().insert(strength);

                areaOfInterest.f_key = insertId;
                mDatabase.AreaOfInterestDAO().insert(areaOfInterest);

                careerObjective.f_key = insertId;
                mDatabase.CareerObjectiveDAO().insert(careerObjective);

                photoAndSign.f_key = insertId;
                mDatabase.PhotAndSignDAO().insert(photoAndSign);

                reference.f_key = insertId;
                mDatabase.ReferenceDAO().insert(reference);

                //templateChoice.fk_basic_id = insertId;
                //mDatabase.TemplateChoiceDAO.insert(templateChoice);

                bi.uid = insertId;

                mDatabase.setTransactionSuccessful(); // ------------------------------------------- transaction successful
                Log.d("ROOM create", "Transaction successful");

                mDatabase.endTransaction(); // ----------------------------------------------------- end transaction
                Log.d("ROOM create", "Transaction Ended");

                // --------------------------------------------------------------------------------- Set all entity to fields
                BASIC_INFO = bi;
                CAREER_OBJECTIVE = careerObjective;
                ACADEMIC_QUA.add(academicQualification);
                PROFESSIONAL_QUA.add(professionalQualification);
                PROJECT.add(project);
                INTERNSHIP.add(internship);
                WORK_EXPERIENCE.add(workExperience);
                ACHIEVEMENT_AWARD.add(achievementsAndAwards);
                ACTIVITIES.add(activities);
                SKILL.add(skill);
                HOBBY.add(hobby);
                STRENGTH.add(strength);
                AREA_OF_INTEREST.add(areaOfInterest);
                PHOTO_SIGN = photoAndSign;
                REFERENCE = reference;
                //TEMPLATE_CHOICE = templateChoice;

                Log.d("ROOM create", "All Entity Field Assigned");
                //----------------------------------------------------------------------------------

            }
            return bi;
        }

        @Override
        protected void onPostExecute(BasicInfo basicInfo) {
            super.onPostExecute(basicInfo);

            progressBar.setVisibility(View.GONE);

            // proceed if basic entry created
            // else finish this activity
            if (basicInfo.uid != null) {
                Log.d("ROOM create", "data created successfully");
                BASIC_INFO = basicInfo;
            } else {
                finish(); // we cannot proceed because basic entry has failed
                Log.e("ROOM create", "data insert transaction failed.");
            }
        }

    }

    // ASYNC DATA SAVE -----------------------------------------------------------------------------
    class RecordSaveAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            // save all entity in database

            mDatabase.beginTransaction(); // --------------------------------------------------- begin transaction
            Log.d("ROOM save", "in Transaction");

            publishProgress("Saving personal info");
            mDatabase.BasicInfoDAO().update(BASIC_INFO);


            publishProgress("Saving academic qualification");
            for (AcademicQualification aca : ACADEMIC_QUA) {
                mDatabase.AcademicQuaDAO().update(aca);
            }

            publishProgress("Saving professional qualification");
            for (ProfessionalQualification pqa : PROFESSIONAL_QUA) {
                mDatabase.ProfessionalQuaDAO().update(pqa);
            }

            publishProgress("Saving projects");
            for (Project proj : PROJECT) {
                mDatabase.ProjectDAO().update(proj);
            }

            publishProgress("Saving internships");
            for (Internship internship : INTERNSHIP) {
                mDatabase.InternshipDAO().update(internship);
            }

            publishProgress("Saving work experience");
            for (WorkExperience work : WORK_EXPERIENCE) {
                mDatabase.WorkExperienceDAO().update(work);
            }

            publishProgress("Saving achievements");
            for (AchievementsAndAwards achv : ACHIEVEMENT_AWARD) {
                mDatabase.AchievementDAO().update(achv);
            }

            publishProgress("Saving activities");
            for (Activities act : ACTIVITIES) {
                mDatabase.ActivitiesDAO().update(act);
            }

            publishProgress("Saving skills");
            for (Skill skill : SKILL) {
                mDatabase.SkillDAO().update(skill);
            }

            publishProgress("Saving hobbies");
            for (Hobby hobby : HOBBY) {
                mDatabase.hobbyDAO().update(hobby);
            }

            publishProgress("Saving strengths");
            for (Strength strength : STRENGTH) {
                mDatabase.StrengthDAO().update(strength);
            }

            publishProgress("Saving interest");
            for (AreaOfInterest aoi : AREA_OF_INTEREST) {
                mDatabase.AreaOfInterestDAO().update(aoi);
            }

            publishProgress("Saving career objectives");
            mDatabase.CareerObjectiveDAO().update(CAREER_OBJECTIVE);

            publishProgress("Saving photo and sign");
            mDatabase.PhotAndSignDAO().update(PHOTO_SIGN);

            publishProgress("Saving references");
            mDatabase.ReferenceDAO().update(REFERENCE);

            //mDatabase.TemplateChoiceDAO().update(TEMPLATE_CHOICE);

            mDatabase.setTransactionSuccessful(); // ------------------------------------------- transaction successful
            Log.d("ROOM save", "Transaction successful");

            mDatabase.endTransaction(); // ----------------------------------------------------- end transaction
            Log.d("ROOM save", "Transaction Ended");

            publishProgress("Save successful");

            return "Record Updated";
        }

        @Override
        protected void onPostExecute(String msg) {
            super.onPostExecute(msg);
            Log.d("ROOM save", msg);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
        }
    }


    // PAGER ---------------------------------------------------------------------------------------
    // pager adapter
    public class MyPagerAdapter extends FragmentPagerAdapter {

        // constructor method
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BasicInfoFrag();
                case 1:
                    return new AcademicQuaFrag();
                case 2:
                    return new ProfessionalQuaFrag();
                case 3:
                    return new ProjectsAndTrainingsFrag();
                case 4:
                    return new InternshipFrag();
                case 5:
                    return new WorkExperienceFrag();
                case 6:
                    return new SkillAndAchievementFrag();
                case 7:
                    return new CareerObjectiveFrag();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 8;
        }
    }

    // pager scroll callback methods
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


    // TAB LAYOUT ----------------------------------------------------------------------------------
    // tab select callback methods
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

}

