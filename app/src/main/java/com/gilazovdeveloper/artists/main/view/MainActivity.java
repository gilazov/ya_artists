package com.gilazovdeveloper.artists.main.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.gilazovdeveloper.artists.R;
import com.gilazovdeveloper.artists.main.utils.UINavigator;
import com.gilazovdeveloper.artists.model.dto.Artist;

public class MainActivity extends AppCompatActivity{
    private FragmentManager fragmentManager;
    private ActionBar actionBar;
    private UINavigator uiNavigator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        fragmentManager = getSupportFragmentManager();

        uiNavigator = new UINavigator(this, fragmentManager);

        if (savedInstanceState==null) {
                showArtists();
        }

    }

    @Override
    public void onBackPressed() {
        if (uiNavigator.clearCurrentFragment()){
          showArtistsToolbar();
        }else{
            super.onBackPressed();
        }
    }

    void showArtistsToolbar(){

        if (actionBar!=null) {
            actionBar.setTitle(R.string.title_singers);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

    }

    void showArtistPreviewToolbar(Artist artist){
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(artist.getName());
        }
    }

    public void showArtists(){
        uiNavigator.showArtists();
    }

    public void showArtistPreview(Artist artist){
        uiNavigator.showArtistPreview(artist);
        showArtistPreviewToolbar(artist);
    }

    public void showArtistPreviewWithTransition(Artist artist, ImageView transitionImageView, String transitionName){
        showArtistPreviewToolbar(artist);
        uiNavigator.showArtistPreviewWithTransition(artist,transitionImageView,transitionName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
