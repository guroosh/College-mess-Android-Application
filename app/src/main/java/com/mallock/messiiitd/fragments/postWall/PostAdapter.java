package com.mallock.messiiitd.fragments.postWall;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lusfold.spinnerloading.SpinnerLoading;
import com.mallock.messiiitd.DataSupplier;
import com.mallock.messiiitd.R;
import com.mallock.messiiitd.models.Post;
import com.mallock.messiiitd.retrofit.PostUserClass;
import com.mallock.messiiitd.retrofit.WallService;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Mallock on 13-10-2016.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private static final String TAG = "PostAdapter";
    private final List<Post> posts;
    Context context;
    Retrofit retrofit;

    public PostAdapter(List<Post> posts, Context context, Retrofit retrofit) {
        this.posts = posts;
        this.retrofit = retrofit;
        this.context = context;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_child_layout, parent, false);
//        updateUI();
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostHolder holder, int position) {
        final Post post = posts.get(position);
        //todo: uncomment the following code, when url is supplied
        /*
        Picasso.with(context)
                .load(post.getUserImageUrl())
                .into(holder.profileImage);
        */
        if (post.getImageURL() != null && !post.getImageURL().equals("")) {
            holder.postImage.setVisibility(View.VISIBLE);

            Picasso.with(context)
                    .load(post.getImageURL())
                    .error(R.drawable.ic_hide)
                    .into(holder.postImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.loadingProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder.loadingProgressBar.setVisibility(View.GONE);
                        }
                    });
            holder.loadingProgressBar.setVisibility(View.VISIBLE);
        }else{
            holder.loadingProgressBar.setVisibility(View.GONE);

        }
        holder.dateText.setText(post.getDate());
        holder.usernameText.setText("@" + post.getUserId());
        holder.postBodyText.setText(post.getText());
        holder.likeText.setText(post.getUpVotes() + " Likes");
        if (isLiked(post)) {
            holder.likeText.setTextColor(Color.BLUE);
        }
        holder.hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallService wallService = retrofit.create(WallService.class);
                Call<Integer> call = wallService.makeHidden(new PostUserClass(DataSupplier.getUserId(), post.getPostId()));
                call.enqueue(new retrofit.Callback<Integer>() {
                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        if (response.body() != 1) {
                            Log.e(TAG, "makeHidden returned 0");
                        }
                        holder.wholePost.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(context, "network error. Please try later",Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 13-10-2016 like code goes here


            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 13-10-2016 comment code goes here
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.comment_list_layout, null);
                builder.setView(v);
                builder.setCancelable(true);
                builder.setTitle("Comments");
                CommentAdapter mAdapter = new CommentAdapter(post.getComments());
                RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(v.getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                builder.show();
            }
        });
    }

    // TODO: 13-10-2016 return whether the post has been liked by the using user or not
    private boolean isLiked(Post post) {
        return false;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

//    public Context getActivity() {
//        return this.context;
//    }


    public static class PostHolder extends RecyclerView.ViewHolder {
        View wholePost;
        ImageView profileImage, postImage;
        TextView dateText, usernameText, postBodyText, likeText, commentText;
        SpinnerLoading loadingProgressBar;
        ImageButton hideButton;
        LinearLayout like, comment;

        public PostHolder(View itemView) {
            super(itemView);
            this.wholePost = itemView;
            this.profileImage = (ImageView) itemView.findViewById(R.id.image_user);
            postImage = (ImageView) itemView.findViewById(R.id.image_post);
            loadingProgressBar = (SpinnerLoading) itemView.findViewById(R.id.loadingProgressBar);
            dateText = (TextView) itemView.findViewById(R.id.text_date_time);
            usernameText = (TextView) itemView.findViewById(R.id.text_username);
            postBodyText = (TextView) itemView.findViewById(R.id.text_post_body);
            likeText = (TextView) itemView.findViewById(R.id.text_like);
            commentText = (TextView) itemView.findViewById(R.id.text_comment);
            hideButton = (ImageButton) itemView.findViewById(R.id.button_hide);
            like = (LinearLayout) itemView.findViewById(R.id.layout_like);
            comment = (LinearLayout) itemView.findViewById(R.id.layout_comment);
        }
    }
}
