package com.gilazovdeveloper.artists.model;

import com.gilazovdeveloper.artists.model.dto.Artist;

import java.util.List;

import rx.Observable;

/**
 * Created by ruslan on 21.04.16.
 */
public interface Model {
    Observable<List<Artist>> getArtistList();
}
