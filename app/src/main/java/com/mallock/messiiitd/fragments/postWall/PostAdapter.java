package com.mallock.messiiitd.fragments.postWall;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lusfold.spinnerloading.SpinnerLoading;
import com.mallock.messiiitd.DataSupplier;
import com.mallock.messiiitd.MainActivity;
import com.mallock.messiiitd.R;
import com.mallock.messiiitd.models.Comment;
import com.mallock.messiiitd.models.Post;
import com.mallock.messiiitd.retrofit.CommentPost;
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
    WallFragment fragment;
    Retrofit retrofit;
    RecyclerView recyclerView;

    public PostAdapter(List<Post> posts, WallFragment fragment, Retrofit retrofit, RecyclerView recyclerView) {
        this.posts = posts;
        this.retrofit = retrofit;
        this.fragment = fragment;
        this.recyclerView = recyclerView;
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
        Picasso.with(fragment)
                .load(post.getUserImageUrl())
                .into(holder.profileImage);
        */
        if (post.getImageURL() != null && !post.getImageURL().equals("")) {
            holder.postImage.setVisibility(View.VISIBLE);
            holder.loadingProgressBar.setVisibility(View.VISIBLE);
            Picasso.with(fragment.getContext())
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
        } else {
            holder.loadingProgressBar.setVisibility(View.GONE);
            holder.postImage.setVisibility(View.GONE);
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
                WallService wallService = DataSupplier.getRetrofit().create(WallService.class);
                Call<Integer> call = wallService.makeHidden(DataSupplier.getUserId(), post.getPostId());
                call.enqueue(new retrofit.Callback<Integer>() {
                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        holder.wholePost.setVisibility(View.GONE);
                        fragment.refreshRecyclerView(recyclerView);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(fragment.getContext(), "network error. Please try later", Toast.LENGTH_LONG)
                                .show();
                        Log.e(TAG, t.getMessage());
                    }
                });

            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 13-10-2016 like code goes here
                WallService wallService = DataSupplier.getRetrofit().create(WallService.class);
                Call<Integer> call = wallService.toggleLike(DataSupplier.getUserId(), post.getPostId());
                call.enqueue(new retrofit.Callback<Integer>() {
                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        if (holder.likeText.getCurrentTextColor() == Color.BLACK) {
                            post.setUpVotes(post.getUpVotes() + 1);
                            holder.likeText.setTextColor(Color.BLUE);
                            holder.likeText.setText(post.getUpVotes() + " Likes");
                        } else {
                            post.setUpVotes(post.getUpVotes() - 1);
                            holder.likeText.setTextColor(Color.BLACK);
                            holder.likeText.setText(post.getUpVotes() + " Likes");
                        }
                        //fragment.refreshRecyclerView(recyclerView);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(fragment.getContext(), "network error. Please try later---> "+t.getMessage(), Toast.LENGTH_LONG)
                                .show();
                        Log.e(TAG, t.getMessage());
                    }
                });

            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.comment_list_layout, null);
                builder.setView(v);
                builder.setCancelable(true);
                builder.setTitle("Comments");
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        fragment.refreshRecyclerView(recyclerView);

                    }
                });
                final AlertDialog dialog = builder.show();
                CommentAdapter mAdapter = new CommentAdapter(post.getComments());
                final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(v.getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                Button add_comment = (Button) v.findViewById(R.id.add_comment);
                final EditText edit_comment = (EditText) v.findViewById(R.id.comment_et);
                add_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        // TODO: 11/17/2016 add comment POST
                        CommentPost comment = new CommentPost();
                        comment.setText(edit_comment.getText().toString());
                        comment.setUserId(DataSupplier.getUserId());
                        comment.setPostId(post.getPostId());
                        WallService service = DataSupplier.getRetrofit().create(WallService.class);
                        Call<Integer> call = service.addComment(comment);
                        call.enqueue(new retrofit.Callback<Integer>() {
                            @Override
                            public void onResponse(Response<Integer> response, Retrofit retrofit) {
                                Toast.makeText(view.getContext(), "DONE", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.e("RETROFIT ERROR: ", t.getMessage());
                                Toast.makeText(view.getContext(), "RETROFIT ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
    }

    /**
     * returns whether the post has been liked by the user
     * @param post
     * @return true, if the post is liked, false otherwise
     */
    private boolean isLiked(Post post) {
        return post.getLiked()==1;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


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
