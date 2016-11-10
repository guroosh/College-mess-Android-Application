package com.mallock.messiiitd.fragments.postWall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mallock.messiiitd.R;

import java.util.ArrayList;

/**
 * Created by Guroosh Chaudhary on 25-10-2016.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>  {

    private final ArrayList<Comment> comments;

    public CommentAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_child_layout, parent, false);
//        updateUI();
        return new CommentHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final CommentHolder holder, int position) {
        Comment comment = comments.get(position);

        holder.usernameText.setText("@" + comment.getUserId());
        holder.commentText.setText( comment.getText());
        holder.dateText.setText( comment.getDateTime());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentHolder extends RecyclerView.ViewHolder {
        View allComments;
        ImageView profileImage;
        TextView commentText, usernameText, dateText;

        public CommentHolder(View itemView) {
            super(itemView);
            this.allComments = itemView;
            this.profileImage = (ImageView) itemView.findViewById(R.id.image_user);
            usernameText = (TextView) itemView.findViewById(R.id.text_username);
            commentText = (TextView) itemView.findViewById(R.id.text_comment);
            dateText = (TextView) itemView.findViewById(R.id.text_date_time);
        }
    }
}
