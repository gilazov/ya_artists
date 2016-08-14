package com.gilazovdeveloper.artists.model;

import com.gilazovdeveloper.artists.model.dto.Artist;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ruslan on 21.04.16.
 */
public interface ArtistService {
        @GET("artists.json")
        Observable<List<Artist>> getArtists();
}
