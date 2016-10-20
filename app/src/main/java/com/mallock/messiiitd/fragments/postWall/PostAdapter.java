package com.mallock.messiiitd.fragments.postWall;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mallock.messiiitd.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mallock on 13-10-2016.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private final ArrayList<Post> posts;
    Context context;

    public PostAdapter(ArrayList<Post> posts, Context context) {
        this.posts = posts;
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
        Post post = posts.get(position);
        //todo: uncomment the following code, when url is supplied
        /*
        Picasso.with(context)
                .load(post.getUserImageUrl())
                .into(holder.profileImage);
*/
        if(post.getImageUrl()!=null && !post.getImageUrl().equals(""))
            holder.postImage.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load(post.getImageUrl())
                .error(R.drawable.ic_hide)
                .into(holder.postImage);

        holder.dateText.setText(post.getDateTime());
        holder.usernameText.setText("@" + post.getUserId());
        holder.postBodyText.setText(post.getText());
        holder.likeText.setText(post.getUpvotes() + " Likes");
        if (isLiked(post)) {
            holder.likeText.setTextColor(Color.BLUE);
        }
        holder.hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.wholePost.setVisibility(View.GONE);
                // TODO: 13-10-2016 save in sharedPreferences to keep hidden
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


    public static class PostHolder extends RecyclerView.ViewHolder {
        View wholePost;
        ImageView profileImage, postImage;
        TextView dateText, usernameText, postBodyText, likeText, commentText;
        ImageButton hideButton;
        LinearLayout like, comment;

        public PostHolder(View itemView) {
            super(itemView);
            this.wholePost = itemView;
            this.profileImage = (ImageView) itemView.findViewById(R.id.image_user);
            postImage = (ImageView) itemView.findViewById(R.id.image_post);
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
