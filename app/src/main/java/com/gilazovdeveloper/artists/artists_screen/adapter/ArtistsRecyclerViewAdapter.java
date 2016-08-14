package com.gilazovdeveloper.artists.artists_screen.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilazovdeveloper.artists.R;
import com.gilazovdeveloper.artists.model.dto.Artist;
import com.gilazovdeveloper.artists.utils.ImageLoader;
import com.gilazovdeveloper.artists.utils.ListItemsToStringConverter;
import com.gilazovdeveloper.artists.artists_screen.listener.OnArtistsItemClickListener;

import java.util.List;

/**
 * Created by ruslan on 21.04.16.
 */

public class ArtistsRecyclerViewAdapter extends RecyclerView.Adapter<ArtistsRecyclerViewAdapter.ViewHolder> {

    public static final String BASE_TRANSITION_NAME = "transition";

    private final List<Artist> mValues;
    private OnArtistsItemClickListener mListener;
    private Context ctx;

    public ArtistsRecyclerViewAdapter(Context ctx, List<Artist> items, OnArtistsItemClickListener listener) {
        mValues = items;
        this.mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artists_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return true; // чтобы recyclerView не создавал новые холдеры когда мы скролим, а анимация не успела отработать
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Artist artist = mValues.get(position);

        holder.name.setText(artist.getName());
        holder.genres.setText(ListItemsToStringConverter.convert(artist.getGenres()));
        holder.tracksAndAlbums.setText(String.format( "%s альбомов, %s песен",artist.getAlbums(),artist.getTracks()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.cover.setTransitionName(BASE_TRANSITION_NAME + position);
        }

        ImageLoader.load(ctx, artist.getCover(), holder.cover, true, true);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    if (holder.cover!=null) {
                        //передаем в onclick объект и картинку для shared element анимации
                        mListener.onItemClick(artist, holder.cover, BASE_TRANSITION_NAME + position);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView genres;
        public final TextView tracksAndAlbums;
        public final ImageView cover;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.name);
            genres = (TextView) view.findViewById(R.id.genres);
            tracksAndAlbums = (TextView) view.findViewById(R.id.tracksAndAlbums);
            cover = (ImageView) view.findViewById(R.id.cover);

        }

    }
}
