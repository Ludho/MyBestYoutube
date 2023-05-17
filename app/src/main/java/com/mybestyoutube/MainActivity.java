package com.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mybestyoutube.data.YoutubeVideoDataBase;
import com.mybestyoutube.model.YoutubeVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView rvVideos;
    public final static String KEY_VIDEO = "video_key";
    private YoutubeVideoDataBase YoutubeVideoDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // setup reclyclerView
        rvVideos = findViewById(R.id.rv_videos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvVideos.setHasFixedSize(true);
        rvVideos.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        VideoAsyncTask videoAsyncTask = new VideoAsyncTask();
        videoAsyncTask.execute();
    }

    class VideoAsyncTask extends AsyncTask<Nullable, Nullable, List<YoutubeVideo>> {

        @Override
        protected List<YoutubeVideo> doInBackground(Nullable... nullables) {
            List<YoutubeVideo> videoList = YoutubeVideoDataBase.getDb(context).
                    youtubeVideoDAO().getAllYoutubeVideo();     // return tout les videos de la bd
            return videoList;

        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> videos) {
            VideoAdapter videoAdapter = new VideoAdapter(videos, new VideoAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(YoutubeVideo youtubeVideo) {

                    // navigate to video's detail
                    Intent intent = new Intent(context, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(MainActivity.KEY_VIDEO, youtubeVideo);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            rvVideos.setAdapter(videoAdapter);
        }
    }


    /*
     *
     *  MENU SETUP
     *
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nav_add_video){
            Intent intent = new Intent(getApplicationContext(),AddYouTubeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}