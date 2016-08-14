package com.gilazovdeveloper.artists.artists_screen.presenter;

import android.os.Bundle;
import android.widget.ImageView;

import com.gilazovdeveloper.artists.model.Model;
import com.gilazovdeveloper.artists.model.ModelImpl;
import com.gilazovdeveloper.artists.model.dto.Artist;
import com.gilazovdeveloper.artists.artists_screen.view.ArtistsListFragment;
import com.gilazovdeveloper.artists.artists_screen.view.ArtistsView;
import com.gilazovdeveloper.artists.main.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by ruslan on 21.04.16.
 */

public class ArtistsPresenterImpl implements ArtistsPresenter {

    private static final String BUNDLE_ARTISTS_LIST_KEY = "ARTISTS_BUNDLE_KEY";

    ArtistsView view;
    Model model = new ModelImpl();
    List<Artist> artistList;

    private Subscription subscription = Subscriptions.empty();

    public ArtistsPresenterImpl(ArtistsView view) {
        this.view = view;
    }

    public boolean isArtistListEmpty(){
        return artistList==null || artistList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isArtistListEmpty()) {
            outState.putSerializable(BUNDLE_ARTISTS_LIST_KEY, new ArrayList<>(artistList));
        }
    }

    @Override
    public void setListFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            artistList = (List<Artist>) savedInstanceState.getSerializable(BUNDLE_ARTISTS_LIST_KEY);
        }
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadData() {
        if (view!=null) {
            view.showProgress();
        }
        //если в bundle что-то лежит показываем это, иначе обновляем данные
        if (isArtistListEmpty()) {
            refreshData();
        }else {
            if (view!=null) {
                view.setItems(artistList);
                view.hideProgress();
            }
        }
    }

    @Override
    public void refreshData() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getArtistList()
                .subscribe(new Observer<List<Artist>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null) {
                            view.showError(e.getMessage());
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onNext(List<Artist> artists) {

                        if (artists != null && !artists.isEmpty()) {
                            artistList = artists;
                            if (view!=null) {
                                view.setItems(artists);
                                view.hideEmptyView();
                            }
                        } else {
                            if (view!=null) {
                            view.showEmptyView();
                            }
                        }

                        if (view!=null) {
                            view.hideProgress();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(Artist artist) {
        if (artist!=null && view!=null) {
            MainActivity mainActivity = (MainActivity) ((ArtistsListFragment) view).getActivity();
            if (mainActivity!=null) {
                mainActivity.showArtistPreview(artist);
            }
        }

    }

    @Override
    public void onItemClick(Artist artist, ImageView transitionImageView, String transitionName) {
        //start preview fragment with transition
        if (artist != null && view != null) {
            MainActivity mainActivity = (MainActivity) ((ArtistsListFragment) view).getActivity();
            if (mainActivity != null) {
                if (transitionImageView != null) {
                    mainActivity.showArtistPreviewWithTransition(artist, transitionImageView, transitionName);
                }
            }
        }
    }

}
