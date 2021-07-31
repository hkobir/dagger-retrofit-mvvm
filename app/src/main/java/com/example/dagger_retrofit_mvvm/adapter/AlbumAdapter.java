package com.example.dagger_retrofit_mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger_retrofit_mvvm.R;
import com.example.dagger_retrofit_mvvm.model.AlbumData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private List<AlbumData> albumList;

    public void setAlbumAdapter(List<AlbumData> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        AlbumData albumData = albumList.get(position);
        holder.albumTitle.setText(albumData.getTitle());
        Picasso.get().load(albumData.getUrl()).placeholder(R.drawable.image_placeholder).fit().into(holder.albumImage);
    }

    @Override
    public int getItemCount() {
        if (albumList == null)
            return 0;
        else
            return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView albumImage;
        private AppCompatTextView albumTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.albumTitle);
            albumImage = itemView.findViewById(R.id.albumImage);
        }
    }
}
