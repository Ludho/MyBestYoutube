package com.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mybestyoutube.data.YoutubeVideoDataBase;
import com.mybestyoutube.model.YoutubeVideo;

public class AddYouTubeActivity extends AppCompatActivity {

    public String category;
    public Context context;
    public YoutubeVideoDataBase YoutubeVideoDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube);
        context = getApplicationContext();
        initSpinner();
        initButton();
    }

    private void initButton(){
        Button btn_add = findViewById(R.id.btn_add_video_add);
        Button btn_cancel = findViewById(R.id.btn_add_video_cancel);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVideo();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void addVideo() {
        String title = ((TextView)findViewById(R.id.et_add_video_title)).getText().toString();
        String description = ((TextView)findViewById(R.id.et_add_video_description)).getText().toString();
        String url = ((TextView)findViewById(R.id.et_add_video_url)).getText().toString();
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(url) || TextUtils.isEmpty(description)){
            Toast toast = Toast.makeText(context,"Please complete the form before adding video",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        YoutubeVideo youtubeVideo = new YoutubeVideo(title, description, url, category);
        YoutubeVideoDataBase.getDb(context).youtubeVideoDAO().insertYoutubeVideo(youtubeVideo);
        finish();
    }

    /*
    * Initialize spinner with categories
    * */
    private void initSpinner(){
        Spinner spinner = findViewById(R.id.sp_add_video_category);
        String[] arraySpinner = new String[] {
                (String) getResources().getText(R.string.comedy_category),
                (String) getResources().getText(R.string.meme_category),
                (String) getResources().getText(R.string.cooking_category),
                (String) getResources().getText(R.string.gaming_category),
                (String) getResources().getText(R.string.music_category),
                (String) getResources().getText(R.string.sport_category)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        category = getResources().getText(R.string.comedy_category).toString();
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        switch (position){
                            case 0:
                                category= (String)getResources().getText(R.string.comedy_category);
                                break;
                            case 1:
                                category= (String)getResources().getText(R.string.meme_category);
                                break;
                            case 2:
                                category= (String)getResources().getText(R.string.cooking_category);
                                break;
                            case 3:
                                category= (String)getResources().getText(R.string.gaming_category);
                                break;
                            case 4:
                                category= (String)getResources().getText(R.string.music_category);
                                break;
                            case 5:
                                category= (String)getResources().getText(R.string.sport_category);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}