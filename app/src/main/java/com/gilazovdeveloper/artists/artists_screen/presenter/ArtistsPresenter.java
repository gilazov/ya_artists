package com.gilazovdeveloper.artists.artists_screen.presenter;

import android.os.Bundle;
import android.widget.ImageView;

import com.gilazovdeveloper.artists.model.dto.Artist;

/**
 * Created by ruslan on 21.04.16.
 */
public interface ArtistsPresenter {
    void onStop();
    void loadData();
    void refreshData();
    void onItemClick(Artist artist);
    void onItemClick(Artist artist, ImageView transitionImageView, String transitionName);
    void onSaveInstanceState(Bundle outState) ;
    void setListFromBundle(Bundle savedInstanceState);
}
