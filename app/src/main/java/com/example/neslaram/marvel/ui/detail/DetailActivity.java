package com.example.neslaram.marvel.ui.detail;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.neslaram.marvel.R;
import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.presenter.detail.DetailPresenter;
import com.example.neslaram.marvel.presenter.detail.impl.DetailPresenterImpl;
import com.example.neslaram.marvel.utils.Contants;
import com.example.neslaram.marvel.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @Bind(R.id.imgAvatar)
    ImageView imgAvatar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.txtDescription)
    TextView txtDescription;
    @Bind(R.id.txtComics)
    TextView txtComics;
    @Bind(R.id.txtEvents)
    TextView txtEvents;
    @Bind(R.id.txtSeries)
    TextView txtSeries;
    @Bind(R.id.txtStories)
    TextView txtStories;

    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra(Contants.KEY_CHARACTER_NAME);
        String imgPath = intent.getStringExtra(Contants.KEY_CHARACTER_IMG);
        int id = intent.getIntExtra(Contants.KEY_CHARACTER_ID, 0);
        setToolbar(name, imgPath);

        detailPresenter = new DetailPresenterImpl(this);
        if (id > 0) {
            if (Utils.isConnected(this)) {
                detailPresenter.getCharacter(id);
            } else {
                detailPresenter.getLocalCharacter(id);
            }
        }

    }

    @Override
    protected void onDestroy() {
        detailPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setItem(Character item) {
        toolbar.setTitle(item.getName());
        if (item.getDescription().isEmpty()) {

            txtDescription.setText(R.string.no_description);
        } else {
            txtDescription.setText(item.getDescription());
        }
        Resources resources = getResources();
        txtComics.setText(resources.getQuantityString(R.plurals.numberOfComicsAvailable, item.getComics().getAvailable(), item.getComics().getAvailable()));
        txtSeries.setText(resources.getQuantityString(R.plurals.numberOfSeriesAvailable, item.getSeries().getAvailable(), item.getSeries().getAvailable()));
        txtEvents.setText(resources.getQuantityString(R.plurals.numberOfEventosAvailable, item.getEvents().getAvailable(), item.getEvents().getAvailable()));
        txtStories.setText(resources.getQuantityString(R.plurals.numberOfHistoriasAvailable, item.getStories().getAvailable(), item.getStories().getAvailable()));

        if (!item.getThumbnail().isEmpty()) {
            Glide.with(imgAvatar.getContext())
                    .load(item.getThumbnail())
                    .crossFade()
                    .centerCrop()
                    .into(imgAvatar);

        }

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void setToolbar(String title, String imgPath) {
        if (title.isEmpty()) {
            toolbar.setTitle(R.string.app_name);
        } else {
            toolbar.setTitle(title);
        }

        if (!imgPath.isEmpty()) {
            Glide.with(imgAvatar.getContext())
                    .load(imgPath)
                    .crossFade()
                    .centerCrop()
                    .into(imgAvatar);
        }
        setSupportActionBar(toolbar);
    }
}
