package com.gilazovdeveloper.artists.artists_screen.listener;

import android.widget.ImageView;

import com.gilazovdeveloper.artists.model.dto.Artist;

/**
 * Created by ruslan on 21.04.16.
 */
public interface OnArtistsItemClickListener {
    void onItemClick(Artist artist, ImageView transitionImageView, String transitionName);
}
