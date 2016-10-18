package com.mallock.messiiitd.fragments.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mallock.messiiitd.R;
import com.mallock.messiiitd.fragments.postWall.Post;

import java.util.ArrayList;


public class MenuAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final ArrayList<MenuItem>  mDataset;
     ArrayList<Post> posts;
    Context context;

    public MenuAdapter(ArrayList<MenuItem> mDataset) {
        this.mDataset = mDataset;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuchildlayout, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).itemName);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
