package com.mallock.messiiitd.fragments.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mallock.messiiitd.DataSupplier;
import com.mallock.messiiitd.R;
import com.mallock.messiiitd.models.Menu;
import com.mallock.messiiitd.retrofit.DownVotePost;
import com.mallock.messiiitd.retrofit.MenuService;
import com.mallock.messiiitd.retrofit.UpVotePost;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;


public class MenuAdapter extends RecyclerView.Adapter<ViewHolder> {

    private int current_up_votes;
    private int current_down_votes;
    private final ArrayList<Menu.Item> mDataset;
    private final String title;
    private Context context;

    public MenuAdapter(ArrayList<Menu.Item> mDataset, String title) {
        this.mDataset = mDataset;
        this.title = title;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuchildlayout, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());
        holder.up_count.setText(mDataset.get(position).getUpVote() + "");
        holder.down_count.setText(mDataset.get(position).getDownVote() + "");
        holder.up_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_up_votes = mDataset.get(position).getUpVote();
                // TODO: 27-11-2016 increase in backend
                UpVotePost upVotePost = new UpVotePost();
                upVotePost.setFlag(1);
                upVotePost.setName(mDataset.get(position).getName());
                MenuService service = DataSupplier.getRetrofit().create(MenuService.class);
                Call<Integer> call = service.menuUpVote(upVotePost);
                call.enqueue(new retrofit.Callback<Integer>() {

                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        current_up_votes++;
                        mDataset.get(position).setUpVote(current_up_votes);
                        holder.up_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.like_green));
                        holder.up_count.setText(current_up_votes + "");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("RETROFIT ERROR: ", t.getMessage());
                        Toast.makeText(context, "RETROFIT ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.down_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_down_votes = mDataset.get(position).getDownVote();
                // TODO: 27-11-2016 decrease in backend
                DownVotePost downVotePost = new DownVotePost();
                downVotePost.setFlag(1);
                downVotePost.setName(mDataset.get(position).getName());
                MenuService service = DataSupplier.getRetrofit().create(MenuService.class);
                Call<Integer> call = service.menuDownVote(downVotePost);
                call.enqueue(new retrofit.Callback<Integer>() {

                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        current_down_votes++;
                        mDataset.get(position).setDownVote(current_down_votes);
                        holder.down_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.unlike_red));
                        holder.down_count.setText(current_down_votes + "");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("RETROFIT ERROR: ", t.getMessage());
                        Toast.makeText(context, "RETROFIT ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
