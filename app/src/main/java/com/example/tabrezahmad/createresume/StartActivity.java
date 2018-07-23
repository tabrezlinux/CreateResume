package com.example.tabrezahmad.createresume;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.MyRoomDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Me on 11-Jul-18.
 */

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static MyRoomDatabase mDatabase;             // room database
    RecyclerView mRecyclerView;


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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab setup
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // setup drawer layout and navigation
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // navigation view setup
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    // VIEWS SETUP ---------------------------------------------------------------------------------
    private void setupView() {

        // setup toolbar and fab
        setupFabAndToolbar();

        mRecyclerView = findViewById(R.id.rv_resume_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setClipToPadding(true);


        DatasetAsysTask datasetAsync = new DatasetAsysTask();
        datasetAsync.execute();

        // itemTouchHelper
//      ItemTouchHelper touchHelper = null;

//      ItemAdapter itemAdapter = new ItemAdapter(this, models, new OnStartDragListener() {
//            @Override
//            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//                //TouchHelper.startDrag(viewHolder);
//            }
//        });

//      ItemTouchHelper.Callback callback = new EditItemTouchHelperCallback(itemAdapter);
//      touchHelper = new ItemTouchHelper(callback);


//      touchHelper.attachToRecyclerView(mRecyclerView);
//      mRecyclerView.setAdapter(itemAdapter);


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
                intent.putExtra("name", NAME);
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


    // ASYNC DATASET FETCHING
    class DatasetAsysTask extends AsyncTask<Void,Integer,List<BasicInfo>>{

        @Override
        protected List<BasicInfo> doInBackground(Void... voids) {
            List<BasicInfo> basicInfoList = mDatabase.BasicInfoDAO().getAllBasicInfo();
            return basicInfoList;
        }

        @Override
        protected void onPostExecute(List<BasicInfo> basicInfos) {
            super.onPostExecute(basicInfos);
            // string list items
            ArrayList<RVitemModel> dataset = new ArrayList<>();
//            dataset.add(new RVitemModel("Musharraf Alam", "imagepath"));
//            dataset.add(new RVitemModel("Tabrez Ahamd", "imagepath"));
//            dataset.add(new RVitemModel("Aamir Eqbal", "imagepath"));

            for (BasicInfo bi : basicInfos) {
                dataset.add(new RVitemModel(bi.name,"imagepath"));
            }

            Toast.makeText(StartActivity.this,String.valueOf(basicInfos.size()),Toast.LENGTH_SHORT).show();

            RVadapter adapter = new RVadapter(StartActivity.this, dataset);
            mRecyclerView.setAdapter(adapter);
        }
    }


    // RECYCLER VIEW TOUCH HELPER INTERFACE --------------------------------------------------------


    // RECYCLER ADAPTER ----------------------------------------------------------------------------
    class RVadapter extends RecyclerView.Adapter<RVholder> {

        Context context;
        ArrayList<RVitemModel> dataset;

        //constructor
        public RVadapter(Context context, ArrayList<RVitemModel> dataset) {
            this.context = context;
            this.dataset = dataset;
        }

        @Override
        public RVholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View vh_item = inflater.inflate(R.layout.start_activity_recycler_item, null);
            return new RVholder(vh_item);
        }

        @Override
        public void onBindViewHolder(RVholder holder, int position) {

            if (holder instanceof RVholder) {

                RVholder item = (RVholder) holder;

                // set name and picture of RVholderItem
                holder.tv_name.setText(dataset.get(position).name);
                Picasso.with(context)
                        .load(dataset.get(position).picture)
                        .placeholder(R.drawable.ic_menu_camera)
                        .into(item.iv_picture);
            }

        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

    }

    // RECYCLER VIEW HOLDER ------------------------------------------------------------------------
    class RVholder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public ImageView iv_picture;

        public RVholder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_picture = itemView.findViewById(R.id.iv_picture);
        }


    }

    // RECYCLER VIEW ITEM --------------------------------------------------------------------------
    public class RVitemModel {

        private String name;
        private String picture;

        public RVitemModel(String name, String thumbnail) {
            this.name = name;
            this.picture = thumbnail;
        }

    }


}
