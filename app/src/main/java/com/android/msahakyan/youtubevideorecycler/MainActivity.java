package com.android.msahakyan.youtubevideorecycler;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.msahakyan.youtubevideorecycler.adapter.YoutubeVideoAdapter;
import com.android.msahakyan.youtubevideorecycler.datasource.VideoDataSource;
import com.android.msahakyan.youtubevideorecycler.util.VerticalItemDecorator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.youtube_recycler_view)
    protected RecyclerView mYoutubeVideoRecyclerView;

    private YoutubeVideoAdapter mYoutubeVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initLayoutManager();
        initAdapter();
    }

    private void initAdapter() {
        mYoutubeVideoAdapter = new YoutubeVideoAdapter(MainActivity.this, VideoDataSource.getVideoList());
        mYoutubeVideoRecyclerView.setAdapter(mYoutubeVideoAdapter);
    }

    private void initLayoutManager() {
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 1);

        // Set layout manager to recyclerView
        mYoutubeVideoRecyclerView.setLayoutManager(gridLayoutManager);
        mYoutubeVideoRecyclerView.addItemDecoration(new VerticalItemDecorator(20));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mYoutubeVideoAdapter != null) {
            mYoutubeVideoAdapter.releaseLoaders();
            mYoutubeVideoAdapter = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mYoutubeVideoAdapter != null) {
            mYoutubeVideoAdapter.releaseLoaders();
            mYoutubeVideoAdapter = null;
        }
    }
}
