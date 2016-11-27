package com.mallock.messiiitd.fragments.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mallock.messiiitd.R;
import org.w3c.dom.Text;


class ViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView, up_count, down_count;
    ImageView up_thumb, down_thumb;
    ViewHolder(View v) {
        super(v);
        mTextView = (TextView) v.findViewById(R.id.menu_item_name);
        up_count = (TextView) v.findViewById(R.id.upCount);
        down_count = (TextView) v.findViewById(R.id.downCount);
        up_thumb = (ImageView) v.findViewById(R.id.upThumb);
        down_thumb = (ImageView) v.findViewById(R.id.downThumb);
    }
}
