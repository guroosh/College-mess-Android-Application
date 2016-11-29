package com.mallock.messiiitd.fragments.menu;

import android.app.ProgressDialog;
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
import com.mallock.messiiitd.fragments.menu.menuUtils.MenuUtils;
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
        final Menu.Item item = mDataset.get(position);
        holder.mTextView.setText(item.getName());
        holder.up_count.setText(mDataset.get(position).getUpVote() + "");
        if (MenuUtils.isUpvoted(item.getName(), context)) {
            holder.up_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.like_green));
        }
        holder.down_count.setText(mDataset.get(position).getDownVote() + "");
        if (MenuUtils.isDownvoted(item.getName(), context)) {
            holder.down_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.unlike_red));
        }
        holder.up_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_up_votes = mDataset.get(position).getUpVote();
                UpVotePost upVotePost = new UpVotePost();
                int flag = MenuUtils.isUpvoted(item.getName(), context) ? -1 : 1;
                upVotePost.setFlag(flag);
                upVotePost.setName(item.getName());
                MenuService service = DataSupplier.getRetrofit().create(MenuService.class);
                final ProgressDialog dialog = new ProgressDialog(context);
                dialog.setMessage("Sending your rating...");
                dialog.show();
                Call<Integer> call = service.menuUpVote(upVotePost);
                call.enqueue(new retrofit.Callback<Integer>() {

                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        MenuUtils.toggleUpvoted(item.getName(), context);
                        if (MenuUtils.isUpvoted(item.getName(), context)) {
                            holder.up_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.like_green));
                            current_up_votes++;
                        } else {
                            current_up_votes--;
                            holder.up_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.like));
                        }
                        mDataset.get(position).setUpVote(current_up_votes);
                        holder.up_count.setText(current_up_votes + "");
                        dialog.hide();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("RETROFIT ERROR: ", t.getMessage());
                        Toast.makeText(context, "RETROFIT ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.hide();
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
                int flag = MenuUtils.isDownvoted(item.getName(), context) ? -1 : 1;
                downVotePost.setFlag(flag);
                downVotePost.setName(item.getName());
                MenuService service = DataSupplier.getRetrofit().create(MenuService.class);
                final ProgressDialog dialog = new ProgressDialog(context);
                dialog.setCancelable(false);
                dialog.setMessage("Sending your rating...");
                dialog.show();
                Call<Integer> call = service.menuDownVote(downVotePost);
                call.enqueue(new retrofit.Callback<Integer>() {

                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        MenuUtils.toggleDownvoted(item.getName(), context);
                        if (MenuUtils.isDownvoted(item.getName(), context)) {
                            current_down_votes++;
                            holder.down_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.unlike_red));
                        } else {
                            current_down_votes--;
                            holder.down_thumb.setImageDrawable(context.getResources().getDrawable(R.drawable.unlike));
                        }
                        mDataset.get(position).setDownVote(current_down_votes);
                        holder.down_count.setText(current_down_votes + "");
                        dialog.hide();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("RETROFIT ERROR: ", t.getMessage());
                        Toast.makeText(context, "RETROFIT ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.hide();
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
