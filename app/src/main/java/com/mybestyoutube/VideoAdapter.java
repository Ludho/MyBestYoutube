package com.mybestyoutube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mybestyoutube.data.YoutubeVideoDataBase;
import com.mybestyoutube.model.YoutubeVideo;

import java.util.List;

public class VideoAdapter  extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    private List<YoutubeVideo> videos;
    private final OnItemClickListener listener;
    private YoutubeVideoDataBase VideoDatabase;

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        YoutubeVideo video = videos.get(position);
        TextView tv_title = holder.tvTitle;
        TextView tv_description = holder.tvDescription;

        tv_title.setText(video.getTitle());
        tv_description.setText(video.getDescription());
        holder.bind(videos.get(position), listener);
    }


    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvDescription;

        public VideoViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.video_title);
            tvDescription = itemView.findViewById(R.id.video_description);
        }

        public void bind(YoutubeVideo youtubeVideo, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(youtubeVideo);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo youtubeVideo);
    }

    public VideoAdapter(List<YoutubeVideo> videos, OnItemClickListener listener){
        this.videos = videos;
        this.listener = listener;
    }

}
