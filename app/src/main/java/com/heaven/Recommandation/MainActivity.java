package com.heaven.Recommandation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.heaven.Recommandation.api.Etsy;
import com.heaven.Recommandation.model.ListingAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private View mProgressView;
    private TextView mErrorView;
    private static final String STATE_ACTIVE_LISTING = "StateActiveListing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressView = findViewById(R.id.progress_bar);
        mErrorView= (TextView) findViewById(R.id.error_view);

        //setup recycler view

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager
                (1, StaggeredGridLayoutManager.VERTICAL));
        ListingAdapter listingAdapter = new ListingAdapter(this);
        mRecyclerView.setAdapter(listingAdapter);

        if (savedInstanceState == null){
            showLoading();
            Etsy.getActiveListing(listingAdapter);
        }else {
            //listingAdapter.success((ActiveListings)savedInstanceState.getParcelable(STATE_ACTIVE_LISTING),null);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

    public void showLoading(){
        mProgressView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    public void showList(){
        mProgressView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }
    public void showError(){
        mProgressView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }
}
