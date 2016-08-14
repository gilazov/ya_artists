package com.gilazovdeveloper.artists.model;

import com.gilazovdeveloper.artists.model.dto.Artist;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruslan on 21.04.16.
 */

public class ModelImpl implements Model{
    ArtistService serverApi = Api.getArtistService();
       @Override
    public Observable<List<Artist>> getArtistList() {
           return serverApi.getArtists()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread());
    }

}
