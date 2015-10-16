package com.heaven.Recommandation.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.Recommandation.MainActivity;
import com.heaven.Recommandation.R;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Zion on 15/10/15.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListingHolder> implements Callback<ActiveListings>{

    private LayoutInflater mLayoutInflater;
    private ActiveListings activeListings;
    private MainActivity activity;
    public ListingAdapter(MainActivity activity){
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
    }
    @Override
    public ListingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListingHolder(mLayoutInflater.inflate(R.layout.layout_listing, parent ,false));
    }

    @Override
    public void onBindViewHolder(ListingHolder holder, int position) {
        final Listing listing = activeListings.results[position];
        holder.mTitleView.setText(listing.title);
        holder.mPriceView.setText(listing.price);
        holder.mShopNameView.setText((CharSequence) listing.Shop);
        Picasso.with(holder.mImageView.getContext())
                .load(listing.Images[0].url_570xN)
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        if (activeListings == null)
        return 0;

        if (activeListings.results == null)
            return 0;
        return activeListings.results.length;
    }
    @Override
    public void success(ActiveListings activeListings, Response response) {
        this.activeListings = activeListings;
        notifyDataSetChanged();
        this.activity.showList();
    }
    @Override
    public void failure(RetrofitError error){
        this.activity.showError();
    }

    public ActiveListings getActiveListing(){
        return activeListings;
    }



    public class ListingHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTitleView;
        public TextView mShopNameView;
        public TextView mPriceView;


        public ListingHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.listing_image);
            mTitleView = (TextView) itemView.findViewById(R.id.listing_title);
            mShopNameView = (TextView) itemView.findViewById(R.id.listing_shopname);
            mPriceView = (TextView) itemView.findViewById(R.id.listing_price);
        }
    }
}
