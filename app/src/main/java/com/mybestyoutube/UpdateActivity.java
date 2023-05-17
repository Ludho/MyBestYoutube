package com.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mybestyoutube.data.YoutubeVideoDataBase;
import com.mybestyoutube.model.YoutubeVideo;

import java.util.Arrays;

public class UpdateActivity extends AppCompatActivity {

    private Context context;
    private YoutubeVideo youtubeVideo;
    private YoutubeVideoDataBase YoutubeVideoDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        context = getApplicationContext();
        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();
        youtubeVideo  = (YoutubeVideo) bundle.getSerializable(MainActivity.KEY_VIDEO);

        initSpinner();
        EditText et_title = findViewById(R.id.et_edit_video_title);
        EditText et_description = findViewById(R.id.et_edit_video_description);
        et_title.setText(youtubeVideo.getTitle());
        et_description.setText(youtubeVideo.getDescription());

        Button btn_edit = findViewById(R.id.btn_edit_video_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editVideo();
            }
        });

        Button btn_cancel = findViewById(R.id.btn_edit_video_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return au main activity
                Intent mainActivityIntent = new Intent(context, MainActivity.class);
                startActivity(mainActivityIntent);
                finish();


            }
        });
    }

    private void editVideo() {
        String title = ((EditText)findViewById(R.id.et_edit_video_title)).getText().toString();
        String description = ((EditText)findViewById(R.id.et_edit_video_description)).getText().toString();
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            Toast toast = Toast.makeText(context,"Please complete the form before editing video",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        youtubeVideo.setDescription(description);
        youtubeVideo.setTitle(title);
        YoutubeVideoDataBase.getDb(context).youtubeVideoDAO().updateYoutubeVideo(youtubeVideo);
        // return au main activity
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    private void initSpinner(){
        Spinner spinner = findViewById(R.id.sp_edit_video_category);
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
        spinner.setSelection(Arrays.asList(arraySpinner).indexOf(youtubeVideo.getCategory()));
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        switch (position){
                            case 0:
                                youtubeVideo.setCategory((String)getResources().getText(R.string.comedy_category));
                                break;
                            case 1:
                                youtubeVideo.setCategory((String)getResources().getText(R.string.meme_category));
                                break;
                            case 2:
                                youtubeVideo.setCategory((String)getResources().getText(R.string.cooking_category));
                                break;
                            case 3:
                                youtubeVideo.setCategory((String)getResources().getText(R.string.gaming_category));
                                break;
                            case 4:
                                youtubeVideo.setCategory((String)getResources().getText(R.string.music_category));
                                break;
                            case 5:
                                youtubeVideo.setCategory((String)getResources().getText(R.string.sport_category));
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }
}