package com.mallock.messiiitd.fragments.menu;

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
import com.mallock.messiiitd.models.Menu;
import com.mallock.messiiitd.retrofit.MenuService;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

public class ViewPagerFragment extends Fragment {

//    ArrayList<MenuItem> menuItems;
    ArrayList<Menu.Item> menuItems;
    String title;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ViewPagerFragment newInstance(ArrayList<Menu.Item> items, String title) {

        Bundle args = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.menuItems = items;
        fragment.title = title;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.menulayout2, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        TextView menu_title = (TextView) rootView.findViewById(R.id.menu_title);
        menu_title.setText(title);
        mAdapter = new MenuAdapter(menuItems, title);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
