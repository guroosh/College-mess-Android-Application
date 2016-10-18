package com.mallock.messiiitd.fragments.postWall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mallock.messiiitd.DataSupplier;
import com.mallock.messiiitd.R;

/**
 * Created by Mallock on 06-10-2016.
 */

public class WallFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wall_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new PostAdapter(DataSupplier.getPosts(getContext()), getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
