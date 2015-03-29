package com.treelzebub.umap.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.idiogram.umap.R;
import com.treelzebub.umap.api.discogs.model.Discogs;
import com.treelzebub.umap.ui.fragment.LoginFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DashboardActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    private boolean mHasLoggedIn = false; //TODO use shared pref

    @InjectView(R.id.navigation_drawer)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.nav_list)
    ListView mListView;

    private ArrayAdapter<String> mListAdapter;
    private static final String[] listOptions = {"Search", "My Collection", "Accounts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.inject(this);

        mListAdapter = new ArrayAdapter<>(DashboardActivity.this, android.R.layout.simple_list_item_1, listOptions);
        mListView.setAdapter(mListAdapter);

        if (mToolbar != null) {
            mToolbar.setTitle(mHasLoggedIn ? Discogs.getInstance().getmUserName() : "uMAP");
            setSupportActionBar(mToolbar);
        }
        initDrawer();

        FragmentManager fm = getSupportFragmentManager();
        if (!mHasLoggedIn) {
            fm.beginTransaction().add(R.id.container, new LoginFragment()).commit();
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
    }

    private void initDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean hasLoggedIn() {
        return mHasLoggedIn;
    }

    public void setHasLoggedIn(boolean mHasLoggedIn) {
        this.mHasLoggedIn = mHasLoggedIn;
    }
}
