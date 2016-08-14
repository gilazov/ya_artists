package com.gilazovdeveloper.artists.artists_screen.view;

import com.gilazovdeveloper.artists.model.dto.Artist;

import java.util.List;

/**
 * Created by ruslan on 21.04.16.
 */
public interface ArtistsView {
    void showError(String message);
    void setItems(List<Artist> artistDTOs);
    void showEmptyView();
    void hideEmptyView();
    void refreshData();
    void showProgress();
    void hideProgress();

}
