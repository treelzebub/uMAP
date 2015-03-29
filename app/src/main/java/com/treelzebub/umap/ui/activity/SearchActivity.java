package com.treelzebub.umap.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;

import com.idiogram.umap.R;
import com.treelzebub.umap.ui.fragment.MarketplaceFragment;
import com.treelzebub.umap.ui.fragment.NavigationDrawerFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tre Murillo on 3/28/15
 */
public class SearchActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @InjectView(R.id.search_view)
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            search(query);
        }
    }

    private void search(String query) {
        //TODO
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        //TODO

        switch (position) {
            case R.string.search:
                break;
            case R.string.marketplace:
//                fm.beginTransaction().add(R.id.container, new MarketplaceFragment()).commit();
                break;
            case R.string.my_collection:
                break;
            default: break;
        }
    }
}
