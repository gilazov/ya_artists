package com.gilazovdeveloper.artists.artists_screen.view;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilazovdeveloper.artists.R;
import com.gilazovdeveloper.artists.model.dto.Artist;
import com.gilazovdeveloper.artists.artists_screen.presenter.ArtistsPresenter;
import com.gilazovdeveloper.artists.artists_screen.presenter.ArtistsPresenterImpl;
import com.gilazovdeveloper.artists.artists_screen.adapter.ArtistsRecyclerViewAdapter;
import com.gilazovdeveloper.artists.artists_screen.listener.OnArtistsItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtistsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistsListFragment extends Fragment implements ArtistsView, OnArtistsItemClickListener {

    private ArtistsPresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager layoutManager;
    private View view;
    private TextView emptyView;
    private TextView hintView;

    public ArtistsListFragment() {
    }

    public static ArtistsListFragment newInstance() {
        return new ArtistsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ArtistsPresenterImpl(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadData();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artists_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        emptyView = (TextView) view.findViewById(R.id.emptyView);
        hintView = (TextView) view.findViewById(R.id.hintView);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgress();
                refreshData();
            }
        });

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setHasFixedSize(true);

        presenter.setListFromBundle(savedInstanceState);

        return view;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
        hintView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        hintView.setVisibility(View.GONE);
    }

    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
        hintView.setVisibility(View.GONE);
    }

    @Override
    public void refreshData() {
        presenter.refreshData();
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setItems(List<Artist> result) {
        if (recyclerView!=null) {
            recyclerView.setAdapter(new ArtistsRecyclerViewAdapter(getContext(), result, this));
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Artist artist, ImageView transitionImageView, String transitionName) {
     if (canSharedElementTransition()){
            //версия андроид подходит, можем shared element анимацию использовать
            presenter.onItemClick(artist,transitionImageView, transitionName);
        }else{
            presenter.onItemClick(artist);
        }
    }

    boolean canSharedElementTransition(){
        return  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

}
