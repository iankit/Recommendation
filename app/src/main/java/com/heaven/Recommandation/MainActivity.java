package com.heaven.Recommandation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.heaven.Recommandation.api.Etsy;
import com.heaven.Recommandation.model.ActiveListings;
import com.heaven.Recommandation.model.ListingAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private View mProgressView;
    private TextView mErrorView;
    private ListingAdapter adapter;
    private static final String STATE_ACTIVE_LISTING = "StateActiveListing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressView = findViewById(R.id.progress_bar);
        mErrorView = (TextView) findViewById(R.id.error_view);

        //setup recycler view

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager
                (1, StaggeredGridLayoutManager.VERTICAL));

         adapter = new ListingAdapter(this);

        mRecyclerView.setAdapter(adapter);

        if (savedInstanceState == null) {
            showLoading();
            Etsy.getActiveListing( adapter);
        }
        else {
            if (savedInstanceState.containsKey(STATE_ACTIVE_LISTING)) {

                adapter.success((ActiveListings) savedInstanceState.getParcelable(STATE_ACTIVE_LISTING), null);
                showList();
            }
            else {
                showLoading();
                Etsy.getActiveListing(adapter);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ActiveListings activeListings = adapter.getActiveListing();
        if (activeListings!= null){
            outState.putParcelable(STATE_ACTIVE_LISTING,activeListings);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void showLoading() {
        mProgressView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    public void showList() {
        mProgressView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    public void showError() {
        mProgressView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }
}
