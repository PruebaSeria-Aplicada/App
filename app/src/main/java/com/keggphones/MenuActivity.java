package com.keggphones;

import android.content.Intent;
import android.content.res.Resources;

import android.graphics.Rect;

import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;

import java.util.ArrayList;

import com.keggphones.Domain.Brand;
import com.keggphones.Domain.Phone;
import com.keggphones.WS.getAllPhonesWS;


public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recyclerView;
    private PhoneAdapter adapter;
    private ArrayList<Phone> phoneList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        phoneList = new ArrayList<>();

        adapter = new PhoneAdapter(this, phoneList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        try {
            Glide.with(this).load(R.drawable.side_nav_bar).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) { e.printStackTrace(); }

        if(SearchPhoneActivity.wordSearch != null){
            simulateSearch(SearchPhoneActivity.wordSearch);
        }else{
            initPhoneCardAll();
        }



        Button btnAll = (Button)findViewById(R.id.btn_All);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPhoneCardAll();
            }
        });

        Button btnAndroid = (Button)findViewById(R.id.btn_android);
        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPhoneCardAndroid();
            }
        });

        Button btnIOS = (Button)findViewById(R.id.btn_ios);
        btnIOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPhoneCardIOS();
            }
        });

        Button btnMicrosoft = (Button)findViewById(R.id.btn_microsoft);
        btnMicrosoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPhoneCardWindows();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    //Se cargan las cardView con todos los artíuclos
    public void initPhoneCardAll(){
        phoneList.clear();
        for(int i = 0; i < getAllPhonesWS.phonesList.size(); i++){
            phoneList.add(getAllPhonesWS.phonesList.get(i));
            adapter.notifyDataSetChanged();
        }

    }

    //Se cargan las cardView con todos los artículos Android
    public void initPhoneCardAndroid(){
        phoneList.clear();
        for(int i = 0; i < getAllPhonesWS.phonesList.size(); i++){
            if(getAllPhonesWS.phonesList.get(i).getOs().equalsIgnoreCase("android")){
                phoneList.add(getAllPhonesWS.phonesList.get(i));
                adapter.notifyDataSetChanged();
            }
        }
    }

    //Se cargan las cardView con todos los artículos IOS
    public void initPhoneCardIOS(){
        phoneList.clear();
        for(int i = 0; i < getAllPhonesWS.phonesList.size(); i++){
            if(getAllPhonesWS.phonesList.get(i).getOs().equalsIgnoreCase("ios")){
                phoneList.add(getAllPhonesWS.phonesList.get(i));
                adapter.notifyDataSetChanged();
            }
        }

    }
    //Se cargan las cardView con todos los artículos IOS
    public void initPhoneCardWindows(){
        phoneList.clear();
        for(int i = 0; i < getAllPhonesWS.phonesList.size(); i++){
            if(getAllPhonesWS.phonesList.get(i).getOs().equalsIgnoreCase("windows")){
                phoneList.add(getAllPhonesWS.phonesList.get(i));
                adapter.notifyDataSetChanged();
            }
        }

    }


    public void simulateSearch(String word){
        if(word.equalsIgnoreCase("android")){
            initPhoneCardAndroid();
        }else if (word.equalsIgnoreCase("ios")){
            initPhoneCardIOS();
        }else if(word.equalsIgnoreCase("windows")){
            initPhoneCardWindows();
        }else{
            initPhoneCardAll();
        }

    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
        getMenuInflater().inflate(R.menu.menu, menu);
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

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_search) {
            Intent search = new Intent(MenuActivity.this,SearchPhoneActivity.class);
            startActivity(search);

        } else if (id == R.id.nav_shoppingCar) {

        } else if (id == R.id.nav_editProfile) {
            Intent editProfile = new Intent(MenuActivity.this, EditProfileActivity.class);
            startActivity(editProfile);

        }
        //else if (id == R.id.nav_shopping_history) {}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
