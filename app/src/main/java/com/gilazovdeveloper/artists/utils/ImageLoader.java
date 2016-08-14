package com.gilazovdeveloper.artists.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gilazovdeveloper.artists.model.dto.Cover;

/**
 * Created by ruslan on 21.04.16.
 * Сразу грузим большое изображение  для корректной shared element анимации
 * Для миниатюры уменьшаем размер изображения методом thumbnail
 */
public class ImageLoader {

    public static void load(Context ctx, Cover cover, ImageView view, boolean hideIfEmpty, boolean isThumbnail)    {
        if (cover!=null) {
            if (isThumbnail) {
                Glide
                        .with(ctx)
                        .load(cover.getBig())
                        .skipMemoryCache(true)
                        .override(1000,1000)
                        .thumbnail(0.3f)
                        .into(view);
            }else{
                Glide
                        .with(ctx)
                        .load(cover.getBig())
                        .skipMemoryCache(true)
                        .override(1000,1000)
                        .into(view);
            }

        }else if (hideIfEmpty){
            view.setVisibility(View.GONE);
        }
    }
}
