package com.example.tabrezahmad.createresume;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.Entity.BasicInfo;
import com.example.tabrezahmad.createresume.database.FormValidator;
import com.example.tabrezahmad.createresume.database.MyRoomDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 11-Jul-18.
 */

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static MyRoomDatabase mDatabase;             // room database
    RecyclerView mRecyclerView;                         // recycler view
    RVadapter adapter;                                  // recycler adapter
    android.support.v7.app.AlertDialog exitDialog;      // alert dialog

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

        // populate all resume entry from database
        getResumeListAsyncTask getAllDatasetAsync = new getResumeListAsyncTask();
        getAllDatasetAsync.execute();

    }

    // ON BACK PRESS -------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            // create exit dialog if not created
            // then show exit dialog
            if (exitDialog == null) {
                exitDialog = new android.support.v7.app.AlertDialog.Builder(this)
                        .setMessage("Exit Application")
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
            }

            // show exit dialog if not showing
            if (!exitDialog.isShowing())
                exitDialog.show();
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
                createNewResume();
                break;
            default:
                break;
        }
    }

    // show new resume dialog
    private void createNewResume() {

        // 1. show username input dialog
        final UserDialog userDialog = new UserDialog(this);
        userDialog.show();

        Button cancel = userDialog.findViewById(R.id.dialog_cancel);
        Button ok = userDialog.findViewById(R.id.dialog_ok);
        final TextInputEditText et_name = userDialog.findViewById(R.id.name);


        // IF (2 ROUTE) ********************************************************
        // ROUTE - 1
        //  - on cancel username input dialog
        //  - if username input is not empty then show alert dialog
        //  - else cancel username entry dialog
        // ROUTE - 2
        //  - on ok username input dialog
        //  - if username is valid and not empty, create database entry
        //  - if data entry successful then start Edit Activity (Main Activity)
        //  - send entry data UID in intent
        //  - else toast error

        // ROUTE - 1
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

                String NAME = et_name.getText().toString().trim();
                boolean isEmpty = NAME.isEmpty();
                boolean isLengthValid = FormValidator.inLengthRange(NAME, 1, 60);

                // if username field is not empty and valid
                // then alert user
                if (!isEmpty && isLengthValid) {
                    AlertDialog alert = ab.create();
                    alert.show();
                } else {
                    userDialog.dismiss();
                }


            }
        });

        // ROUTE - 2
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if NAME field is not empty and valid then
                // start new resume activity with intent NAME
                String NAME = et_name.getText().toString().trim();
                boolean isEmpty = NAME.isEmpty();
                boolean isLengthValid = FormValidator.inLengthRange(NAME, 1, 60);

                if (!isEmpty && isLengthValid) {
                    Intent intent = new Intent(StartActivity.this, NewResumeActivity.class);
                    intent.putExtra("name", NAME);
                    startActivity(intent);
                    userDialog.dismiss();
                } else
                    Toast.makeText(StartActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
                    //Snackbar.make(rootView, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

    }

    // resume user dialog
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
    class getResumeListAsyncTask extends AsyncTask<Void, Integer, List<RVitemModel>> {

        @Override
        protected List<RVitemModel> doInBackground(Void... voids) {

            // ItemModel sets
            ArrayList<RVitemModel> dataset = new ArrayList<>();

            // get all database resume entry
            // create new RVItemModel list
            // return list of RVItemModels
            try {
                List<BasicInfo> basicInfos = mDatabase.BasicInfoDAO().getAllBasicInfo();

                // loop for each BI sets
                for (BasicInfo bi : basicInfos) {
                    RVitemModel item = new RVitemModel(bi.uid, bi.name, "imagepath");
                    dataset.add(item);
                }
            } catch (Exception e) {
                Log.e("Room Databae", "No Resume Entry found");
            }

            return dataset;

        }

        @Override
        protected void onPostExecute(List<RVitemModel> rvItemModels) {

            super.onPostExecute(rvItemModels);

            // toast item count
            Toast.makeText(StartActivity.this, String.valueOf(rvItemModels.size()), Toast.LENGTH_SHORT).show();

            // create adapter
            // set dataset observer
            // set adapter
            adapter = new RVadapter(StartActivity.this, rvItemModels);
            mRecyclerView.setAdapter(adapter);

        }

    }

    // ASYNC DATA INSERT ---------------------------------------------------------------------------
    class RecordCreateAsyncTask extends AsyncTask<BasicInfo, Integer, BasicInfo> {

        @Override
        protected BasicInfo doInBackground(BasicInfo... basicInfos) {

            // get first BasicInfo object
            BasicInfo bi = basicInfos[0];

            // set id = 0
            bi.uid = null;

            // insert into database
            Long insertId = mDatabase.BasicInfoDAO().insert(bi);

            if (insertId != null)
                bi.uid = insertId;

            return bi;

        }

        @Override
        protected void onPostExecute(BasicInfo basicInfo) {
            super.onPostExecute(basicInfo);

            // start activity
            if (basicInfo.uid != null) {
                Toast.makeText(StartActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartActivity.this, NewResumeActivity.class);
                intent.putExtra("name", basicInfo.name);
                intent.putExtra("uid", basicInfo.uid);
                startActivity(intent);
            } else
                Toast.makeText(StartActivity.this, "Error Creating Resume", Toast.LENGTH_SHORT).show();

        }
    }

    // ASYNC DATA COPY ------------------------------------------------------------------------
    class RecordDuplicateAsyncTask extends AsyncTask<BasicInfo, Integer, Long> {

        @Override
        protected Long doInBackground(BasicInfo... basicInfos) {

            // get first BasicInfo object
            BasicInfo bi = basicInfos[0];

            // set id = 0, mandatory for duplicating a record
            bi.uid = null;

            // insert into database
            Long insertId = mDatabase.BasicInfoDAO().insert(bi);

            if (insertId != null)
                bi.uid = insertId;

            return bi.uid;

        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            // show toast and refresh list if insert id is returned
            if (aLong != null) {
                Toast.makeText(StartActivity.this, "Record Cloned", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(StartActivity.this, "Error Cloning", Toast.LENGTH_SHORT).show();

        }
    }

    // ASYNC DATA DELETE ---------------------------------------------------------------------------
    class RecordDeleteAsyncTask extends AsyncTask<Long, Integer, Integer> {

        @Override
        protected Integer doInBackground(Long... longs) {

            BasicInfo bi = new BasicInfo();
            bi.uid = longs.clone()[0];

            Log.e("Record id ", bi.uid.toString());

            // delete record from database
            return mDatabase.BasicInfoDAO().delete(bi);

        }

        @Override
        protected void onPostExecute(Integer intVal) {
            super.onPostExecute(intVal);

            // refresh recycler dataset list
            if (intVal != null && (intVal > 0)) {
                adapter.notifyDataSetChanged();
            } else
                Toast.makeText(StartActivity.this, "Error Deleting Record", Toast.LENGTH_SHORT).show();

        }

    }


    // RECYCLER ADAPTER ----------------------------------------------------------------------------
    class RVadapter extends RecyclerView.Adapter<RVholder> implements View.OnClickListener {

        Context context;
        List<RVitemModel> dataset;
        Toast toast;

        // constructor
        public RVadapter(Context context, List<RVitemModel> dataset) {
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

            // open each list item edit activity with its respective uid
            return new RVholder(vh_item);

        }

        // multiple time
        @Override
        public void onBindViewHolder(RVholder holder, int position) {

            if (holder instanceof RVholder) {

                final RVitemModel model = dataset.get(position);

                // set view item id
                holder.rootView.setId(model.uid.intValue());
                holder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showToast("Item Clicked");

                        // 1. if item has template_id set
                        // then preview in new activity
                        // 2. else open edit activity

                        if (false) {
                            // TODO show preview activity of this resume
                        } else {
                            // show edit activity
                            Intent intent = new Intent(context, EditResumeActivity.class);
                            intent.putExtra("uid", model.uid);       // Long uid
                            intent.putExtra("name", model.name);     // String name
                            BasicInfo bi = new BasicInfo();
                            //intent.putExtra(bi);
                            context.startActivity(intent);
                        }
                    }
                });

                // on context menu click
                // open popup menu
                // get the id of list item which context menu has been clicked
                // handle click events of popup menu e.g delete record, edit record on list item by its id
                holder.ib_context_menu.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final Long item_id = model.uid;
                        Toast.makeText(getApplicationContext(), model.uid.toString(), Toast.LENGTH_SHORT).show();

                        switch (v.getId()) {
                            case R.id.ib_context_menu:
                                MyPopupMenu pp = new MyPopupMenu(context, v, Gravity.RIGHT);
                                pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        switch (item.getItemId()) {
                                            case R.id.action_delete:
                                                new RecordDeleteAsyncTask().execute(item_id);
                                                break;
                                            case R.id.action_edit:
                                                Intent intent = new Intent(context, EditResumeActivity.class);
                                                intent.putExtra("uid", model.uid);       // Long uid
                                                intent.putExtra("name", model.name);     // String name
                                                context.startActivity(intent);
                                                break;
                                            case R.id.action_duplicate:
                                                BasicInfo basicInfo = new BasicInfo();
                                                basicInfo.uid = model.uid;
                                                basicInfo.name = model.name;
                                                new RecordDuplicateAsyncTask().execute(basicInfo);
                                                break;
                                        }
                                        return true;
                                    }
                                });
                                pp.show();
                                break;
                        }

                    }
                });

                // set name and picture of RVholderItem
                holder.tv_name.setText(model.name);
                Picasso.with(context)
                        .load(dataset.get(position).picture)
                        .placeholder(R.drawable.ic_menu_camera)
                        .into(holder.iv_picture);
            }

        }

        // get item count
        @Override
        public int getItemCount() {
            return dataset.size();
        }

        // on click
        @Override
        public void onClick(View v) {

        }

        // SHOW TOAST
        public void showToast(String msg) {
            if (toast != null)
                toast.cancel();

            toast = Toast.makeText(StartActivity.this, msg, Toast.LENGTH_SHORT);
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

    // RECYCLER VIEW ITEM MODEL
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


    // CONTEXT POPUP MENU --------------------------------------------------------------------------
    class MyPopupMenu extends PopupMenu {

        public MyPopupMenu(Context context, View anchor, int gravity) {
            super(context, anchor, gravity);
            getMenuInflater().inflate(R.menu.context_menu_start_activity, getMenu());
        }
    }

}
