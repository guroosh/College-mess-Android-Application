package com.mallock.messiiitd.fragments.postWall;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mallock.messiiitd.BaseActivity;
import com.mallock.messiiitd.DataSupplier;
import com.mallock.messiiitd.NetworkUtils;
import com.mallock.messiiitd.R;
import com.mallock.messiiitd.misc.OnCloseListener;
import com.mallock.messiiitd.models.Post;
import com.mallock.messiiitd.retrofit.WallService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Mallock on 06-10-2016.
 */

public class WallFragment extends Fragment implements Serializable {

    TextView errorText;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wall_layout, container, false);
        errorText = (TextView) view.findViewById(R.id.network_error);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        refreshRecyclerView(recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPostFragment addPostFragment = new AddPostFragment();
                addPostFragment.setListener(new OnCloseListener() {
                    @Override
                    public void onClose(Context context) {
                        refreshRecyclerView(recyclerView);
                    }
                });
                FragmentManager fm = getActivity().getFragmentManager();
                addPostFragment.show(fm, "tag");

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRecyclerView(recyclerView);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fab.animate().translationY(fab.getHeight() + 100).alpha(1.0f);
                } else {
                    fab.animate().translationY(0);
                }
            }
        });
        return view;
    }


    public void refreshRecyclerView(final RecyclerView recyclerView) {
        if (!NetworkUtils.isActive(getContext())) {
            errorText.setVisibility(View.VISIBLE);
            return;
        } else {
            errorText.setVisibility(View.GONE);
        }
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataSupplier.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WallService wallService = retrofit.create(WallService.class);
        Call<List<Post>> call = wallService.getPosts(DataSupplier.getUserId());
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Response<List<Post>> response, Retrofit r) {
                errorText.setVisibility(View.GONE);
                List<Post> postList = response.body();
                List<Post> displayList = new ArrayList<>();
                for (Post post : postList) {
                    if (post.getHidden() != 1) {
                        displayList.add(post);
                    }
                }
                Collections.reverse(displayList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                PostAdapter mAdapter = new PostAdapter(displayList, WallFragment.this, retrofit, recyclerView);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("RETROFIT ERROR: ", t.getMessage());
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }
}
