package com.example.dagger_retrofit_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dagger_retrofit_mvvm.adapter.AlbumAdapter;
import com.example.dagger_retrofit_mvvm.model.AlbumData;
import com.example.dagger_retrofit_mvvm.viewmodel.MainActivityViewModel;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView albumRV;
    private AlbumAdapter albumAdapter;
    private AppCompatButton nextBtn;
    MainActivityViewModel viewModel;
    private ProgressBar progressBar;
    private int albumId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        loadAlbumData();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                albumId = albumId > 5 ? 1 : ++albumId;

                loadAlbumData();
            }
        });
    }

    private void initComponent() {
        albumRV = findViewById(R.id.albumRV);
        albumRV.setLayoutManager(new LinearLayoutManager(this));
        albumAdapter = new AlbumAdapter();
        albumRV.setAdapter(albumAdapter);
        progressBar = findViewById(R.id.progressBar);
        nextBtn = findViewById(R.id.nextBtn);
    }

    private void loadAlbumData() {
        progressBar.setVisibility(View.VISIBLE);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.makeAlbumApiCall(albumId);
        viewModel.getAlbumListObserver().observe(this, new Observer<List<AlbumData>>() {
            @Override
            public void onChanged(List<AlbumData> albumData) {
                if (albumData != null) {
                    albumAdapter.setAlbumAdapter(albumData);
                    albumAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}