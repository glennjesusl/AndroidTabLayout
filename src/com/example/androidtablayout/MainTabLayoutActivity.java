package com.example.androidtablayout;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainTabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Photos");
//        photospec.setIndicator("Photos", getResources().getDrawable(R.drawable.icon_photos_tab));
        photospec.setIndicator("", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, AddRecordsActivity.class);
        photospec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Songs");
        // setting Title and Icon for the Tab
//        songspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_songs_tab));
        songspec.setIndicator("", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, ViewRecordsActivity.class);
        songspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Videos");
//        videospec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
        videospec.setIndicator("", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, ParsingActivity.class);
        videospec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}