package com.gilazovdeveloper.artists.main.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionInflater;
import android.widget.ImageView;

import com.gilazovdeveloper.artists.R;
import com.gilazovdeveloper.artists.artist_preview_screen.view.ArtistPreviewFragment;
import com.gilazovdeveloper.artists.artists_screen.view.ArtistsListFragment;
import com.gilazovdeveloper.artists.model.dto.Artist;

public class UINavigator {

       public static final String ARTISTS_FRAGMENT_TAG = "artistsFragmentTag";
       public static final String ARTIST_PREVIEW_FRAGMENT_TAG = "artistPreviewFragmentTag";

       private FragmentManager fragmentManager;
       private Context context;

       public UINavigator(Context context, FragmentManager fragmentManager) {
              this.fragmentManager = fragmentManager;
              this.context = context;
       }


       public void showArtists(){

              Fragment fragment = fragmentManager.findFragmentByTag(ARTISTS_FRAGMENT_TAG);

              if (fragment==null) {

                     fragment = ArtistsListFragment.newInstance();

                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            fragment.setSharedElementReturnTransition(TransitionInflater.from(
                                    context).inflateTransition(R.transition.change_image_transform));
                            fragment. setExitTransition(TransitionInflater.from(
                                    context).inflateTransition(android.R.transition.fade));
                     }

                     replaceFragment(fragment, ARTISTS_FRAGMENT_TAG,false,null, null);
              }
       }

       public boolean clearCurrentFragment(){

              int count = fragmentManager.getBackStackEntryCount();

              if (count > 0) {
                     fragmentManager.popBackStack();
                     return true;
              }
              return false;
       }


       public void showArtistPreview(Artist artist){

              Fragment fragment = ArtistPreviewFragment.newInstance(artist, "");
              replaceFragment(fragment, ARTIST_PREVIEW_FRAGMENT_TAG, true, null, null);

       }

       @TargetApi(Build.VERSION_CODES.KITKAT)
       public void showArtistPreviewWithTransition(Artist artist, ImageView transitionImageView, String transitionName){

              ArtistPreviewFragment fragment = ArtistPreviewFragment.newInstance(artist, transitionName);

              if (canSharedElementTransition()) {
                     fragment.setSharedElementEnterTransition(TransitionInflater.from(
                             context).inflateTransition(R.transition.change_image_transform));
                     fragment.setEnterTransition(TransitionInflater.from(
                             context).inflateTransition(android.R.transition.fade));
              }

              replaceFragment(fragment,ARTIST_PREVIEW_FRAGMENT_TAG,  true, transitionImageView, transitionName);
       }

       boolean canSharedElementTransition(){
              return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
       }

       public void replaceFragment(Fragment fragment, String fragmentTag,  boolean addToBackStack, ImageView transitionImageView, String transitionName){
              FragmentTransaction transaction =fragmentManager.beginTransaction();
              transaction.replace(R.id.mainContainer,fragment, fragmentTag);
              if (addToBackStack) {
                     transaction.addToBackStack(null);
              }
              if (transitionImageView!=null){
                     transaction.addSharedElement(transitionImageView, transitionName);
              }
              transaction.commit();
       }


}
