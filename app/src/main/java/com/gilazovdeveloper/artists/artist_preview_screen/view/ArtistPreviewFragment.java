package com.gilazovdeveloper.artists.artist_preview_screen.view;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gilazovdeveloper.artists.R;
import com.gilazovdeveloper.artists.model.dto.Artist;
import com.gilazovdeveloper.artists.utils.ImageLoader;
import com.gilazovdeveloper.artists.utils.ListItemsToStringConverter;

public class ArtistPreviewFragment extends Fragment {

    private static final String BUNDLE_ARTIST_PARAM = "artists";
    private static final String BUNDLE_TRANSITION_NAME_PARAM = "transitionParam";
    private Artist artist;
    private String transitionName;

    public Artist getArtist() {
        return artist;
    }

    public ArtistPreviewFragment() {

    }

    public static ArtistPreviewFragment newInstance(Artist artist, String transitionName) {
        ArtistPreviewFragment fragment = new ArtistPreviewFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_ARTIST_PARAM, artist);
        args.putString(BUNDLE_TRANSITION_NAME_PARAM, transitionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            artist = getArguments().getParcelable(BUNDLE_ARTIST_PARAM);
            transitionName = getArguments().getString(BUNDLE_TRANSITION_NAME_PARAM, "");
        }else{
            artist = savedInstanceState.getParcelable(BUNDLE_ARTIST_PARAM);
            transitionName = savedInstanceState.getString(BUNDLE_TRANSITION_NAME_PARAM);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (artist!=null){
            outState.putParcelable(BUNDLE_ARTIST_PARAM, artist);
            outState.putString(BUNDLE_TRANSITION_NAME_PARAM, transitionName);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_preview, container, false);

        if (artist!=null){
            final ImageView artistCoverView = (ImageView) view.findViewById(R.id.artistCover);
            TextView artistGenres = (TextView) view.findViewById(R.id.artistGenres);
            TextView artistAlbumsAndTracks = (TextView) view.findViewById(R.id.artistAlbumsAndTracks);
            TextView artistDescription = (TextView) view.findViewById(R.id.artistDescription);

            if (canSharedElementTransition()) {
                artistCoverView.setTransitionName(transitionName);
            }

            if (getActivity()!=null) {
                ImageLoader.load(getActivity(),artist.getCover(), artistCoverView, true, false);
            }

            artistGenres.setText(ListItemsToStringConverter.convert(artist.getGenres()));
            artistAlbumsAndTracks.setText(String.format( "%s альбомов, %s песен",artist.getAlbums(),artist.getTracks()));
            artistDescription.setText(artist.getDescription());

            return view;
        }else {
            Toast.makeText(getContext(), "Ошибка загрузки исполнителя", Toast.LENGTH_SHORT).show();
        }

        return view;

    }

    boolean canSharedElementTransition(){
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }
}
