package com.mallock.messiiitd.fragments.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.mallock.messiiitd.R;
import org.w3c.dom.Text;


public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public ViewHolder(View v) {
        super(v);
        mTextView = (TextView) v.findViewById(R.id.mtextview);
        //mTextView = v;
    }
}
