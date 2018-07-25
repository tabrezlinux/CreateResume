package com.example.tabrezahmad.createresume;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.audiofx.AudioEffect;
import android.media.effect.Effect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.carrier.CarrierService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.telephony.CarrierConfigManager;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.MyRoomDatabase;
import com.squareup.picasso.Picasso;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;

/**
 * Created by Me on 11-Jul-18.
 */

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static MyRoomDatabase mDatabase;             // room database
    RecyclerView mRecyclerView;
    RVadapter adapter;


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

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Toast.makeText(StartActivity.this, "touched", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        DatasetAsysTask datasetAsync = new DatasetAsysTask();
        datasetAsync.execute();

        // ItemTouchHelper.Callback callback = new EditItemTouchHelperCallback(itemAdapter);

        // itemTouchHelper
        // ItemTouchHelper touchHelper = touchHelper = new ItemTouchHelper(callback);;

        // ItemAdapter itemAdapter = new ItemAdapter(this, models, );

        // touchHelper.attachToRecyclerView(mRecyclerView);
        // mRecyclerView.setAdapter(itemAdapter);.


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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.notifyDataSetChanged();
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

                if (!et_name.getText().toString().trim().isEmpty()) {
                    AlertDialog alert = ab.create();
                    alert.show();
                } else {
                    userDialog.dismiss();
                }


            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NAME = et_name.getText().toString().trim();
                if (NAME.isEmpty()) {
                    Toast.makeText(StartActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("name", NAME);
                    userDialog.dismiss();
                    startActivity(intent);
                }

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


    // ASYNC DATASET FETCHING ----------------------------------------------------------------------
    class DatasetAsysTask extends AsyncTask<Void, Integer, List<BasicInfo>> {

        @Override
        protected List<BasicInfo> doInBackground(Void... voids) {
            List<BasicInfo> basicInfoList = mDatabase.BasicInfoDAO().getAllBasicInfo();
            return basicInfoList;
        }

        @Override
        protected void onPostExecute(List<BasicInfo> basicInfos) {

            super.onPostExecute(basicInfos);

            // ItemModel sets
            ArrayList<RVitemModel> dataset = new ArrayList<>();

            // loop for each BI sets
            for (BasicInfo bi : basicInfos) {
                dataset.add(new RVitemModel(bi.uid, bi.name, "imagepath"));
            }

            Toast.makeText(StartActivity.this, String.valueOf(basicInfos.size()), Toast.LENGTH_SHORT).show();

            adapter = new RVadapter(StartActivity.this, dataset);
            adapter.registerAdapterDataObserver(new RVadapterDataObserver());
            mRecyclerView.setAdapter(adapter);


        }
    }


    // RECYCLER VIEW TOUCH HELPER INTERFACE --------------------------------------------------------


    // RECYCLER ADAPTER ----------------------------------------------------------------------------
    class RVadapter extends RecyclerView.Adapter<RVholder> implements View.OnClickListener {

        Context context;
        ArrayList<RVitemModel> dataset;
        Toast toast;

        //constructor
        public RVadapter(Context context, ArrayList<RVitemModel> dataset) {
            this.context = context;
            this.dataset = dataset;
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);

        }

        // one time
        @Override
        public RVholder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View vh_item = inflater.inflate(R.layout.start_activity_recycler_item, null);
            vh_item.setOnClickListener(this);
            return new RVholder(vh_item);

        }

        // multiple time
        @Override
        public void onBindViewHolder(RVholder holder, int position) {

            if (holder instanceof RVholder) {

                RVitemModel model = dataset.get(position);

                // set view item id
                holder.rootView.setId(model.uid.intValue());
                holder.ib_context_menu.setOnClickListener(this);

                // set name and picture of RVholderItem
                holder.tv_name.setText(model.name);
                Picasso.with(context)
                        .load(dataset.get(position).picture)
                        .placeholder(R.drawable.ic_menu_camera)
                        .into(holder.iv_picture);
            }

        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }


        @Override
        public void onClick(View v) {
            if (toast != null)
                toast.cancel();

            toast = Toast.makeText(context, "Clicked RV " + v.getId(), Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    // RECYCLER VIEW HOLDER
    class RVholder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public ImageView iv_picture;
        public ImageButton ib_context_menu;
        public View rootView;

        public RVholder(View itemView) {
            super(itemView);
            rootView = itemView;
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_picture = itemView.findViewById(R.id.iv_picture);
            ib_context_menu = itemView.findViewById(R.id.ib_context_menu);
        }
    }

    // ADAPTER DATA OBSERVER
    class RVadapterDataObserver extends RecyclerView.AdapterDataObserver {

        public RVadapterDataObserver() {
            super();
        }

        @Override
        public void onChanged() {
            super.onChanged();
        }
    }

    // RECYCLER VIEW ITEM
    public class RVitemModel {

        private Long uid;
        private String name;
        private String picture;

        public RVitemModel(Long uid, String name, String thumbnail) {
            this.uid = uid;
            this.name = name;
            this.picture = thumbnail;
        }

    }



}
