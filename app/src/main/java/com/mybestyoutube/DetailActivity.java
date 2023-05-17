package com.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mybestyoutube.data.YoutubeVideoDataBase;
import com.mybestyoutube.model.YoutubeVideo;

public class DetailActivity extends AppCompatActivity {
    private Context context;
    private YoutubeVideo youtubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = getApplicationContext();

        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();
        youtubeVideo  = (YoutubeVideo) bundle.getSerializable(MainActivity.KEY_VIDEO);

        Button btn_watch = findViewById(R.id.btn_detail_watch);
        btn_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeVideo.getUrl()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + youtubeVideo.getUrl()));
                try {
                    startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });

        TextView tv_title = findViewById(R.id.detail_video_title);
        TextView tv_description = findViewById(R.id.detail_video_description);
        TextView tv_category = findViewById(R.id.detail_video_category);
        tv_title.setText(youtubeVideo.getTitle());
        tv_description.setText(youtubeVideo.getDescription());
        tv_category.setText(youtubeVideo.getCategory());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nav_delete_video){
            YoutubeVideoDataBase.getDb(context).youtubeVideoDAO().deleteYoutubeVideo(youtubeVideo);
            finish();
        } else if (item.getItemId()==R.id.nav_edit_video) {
            Intent intent = new Intent(context,UpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(MainActivity.KEY_VIDEO, youtubeVideo);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}