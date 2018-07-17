package com.example.tabrezahmad.createresume;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.MyRoomDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 11-Jul-18.
 */

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static MyRoomDatabase mDatabase;             // room database


    ItemTouchHelper mItemTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 1. setup database, // should be initialized first
        setupDatabase();

        // 2. setup views
        setupView();

    }


    // DATABASE SETUP   ----------------------------------------------------------------------------
    private void setupDatabase() {

        // creating room database
        mDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, "mDatabase").build();

        Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();

    }


    // FAB AND TOOLBAR SETUP    --------------------------------------------------------------------
    private void setupFabAndToolbar() {

        // toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab setup
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

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


    // VIEWS SETUP ---------------------------------------------------------------------------------
    private void setupView() {

        // setup toolbar and fab
        setupFabAndToolbar();

        RecyclerView rv_resume_list = findViewById(R.id.rv_resume_list);
        rv_resume_list.setHasFixedSize(true);
        rv_resume_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<ResumeListModels> models = new ArrayList<>();
        models.add(new ResumeListModels( "", ""));
        models.add(new ResumeListModels( "", ""));
        models.add(new ResumeListModels( "", ""));
        models.add(new ResumeListModels( "", ""));



        //mItemTouch = new ItemTouchHelper(new EditItemTouchHelperCallback(rv_list_adapter));
        //mItemTouch.attachToRecyclerView(rv_resume_list);
        //rv_resume_list.setAdapter(rv_list_adapter);

    }

    // ON BACK PRESS -------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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


    // NAVIGATION DRAWER    ------------------------------------------------------------------------
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.drawer_home:
                break;
            case R.id.drawer_new_resume:
                break;
            case R.id.drawer_backup_resource:
                break;
            case R.id.drawer_privacy_policy:
                break;
            case R.id.drawer_about_us:
                break;
            case R.id.drawer_rate_us:
                break;
        }

        // close drawer layout after clicking
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    // CLICK LISTENER   ----------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                newResume();
                break;
            default:
                break;
        }
    }

    private void newResume() {

        final UserDialog userDialog = new UserDialog(this);

        userDialog.show();

        Button cancel = userDialog.findViewById(R.id.dialog_cancel);
        Button ok = userDialog.findViewById(R.id.dialog_ok);
        final TextInputEditText et_name = userDialog.findViewById(R.id.name);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(StartActivity.this);
                ab.setMessage("Are you sure?");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        userDialog.dismiss();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = ab.create();
                alert.show();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                //Bundle b = new Bundle();
                //b.putLong("FOREIGN_KEY", BASIC_INFO_FOREIGN_KEY_ID);
                String NAME = et_name.getText().toString();
                //Toast.makeText(StartActivity.this,NAME,Toast.LENGTH_SHORT).show();
                startActivity(intent);
                userDialog.dismiss();

            }
        });


        //Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

    }

    class UserDialog extends AppCompatDialog {
        Context context;

        public UserDialog(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_new_resume);
        }

    }

}
