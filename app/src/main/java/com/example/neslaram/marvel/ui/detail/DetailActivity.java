package com.example.neslaram.marvel.ui.detail;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
import com.example.neslaram.marvel.utils.Constants;
import com.example.neslaram.marvel.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private static final String STATE_ID = "stateId";
    private static final String STATE_NAME = "stateName";
    private static final String STATE_PATH = "statePath";
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
    private int id;
    private String name;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        name = intent.getStringExtra(Constants.KEY_CHARACTER_NAME);
        imgPath = intent.getStringExtra(Constants.KEY_CHARACTER_IMG);
        id = intent.getIntExtra(Constants.KEY_CHARACTER_ID, 0);
        setToolbar(name, imgPath);

        detailPresenter = new DetailPresenterImpl(this);
        detailPresenter.onCreate();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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

        int comics = item.getComics().getAvailable();
        txtComics.setText(resources.getQuantityString(R.plurals.numberOfComicsAvailable, comics, comics));
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
        if (comics > 4) {

            Bundle bundle = new Bundle();
            bundle.putInt(Constants.KEY_CHARACTER_ID, item.getId());
            bundle.putString(Constants.KEY_CHARACTER_NAME, item.getName());
            bundle.putString(Constants.KEY_CHARACTER_IMG, item.getThumbnail());

            String characterName = item.getName();

            String body = String.format(resources.getString(R.string.number_of_comics), characterName, item.getComics().getAvailable());

            Bitmap largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
            Utils.createNotification(item.getId(), characterName, body, bundle, this, largeIcon);
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
        detailPresenter.getLocalCharacter(id);

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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_ID, id);
        outState.putString(STATE_NAME, name);
        outState.putString(STATE_PATH, imgPath);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        id = savedInstanceState.getInt(STATE_ID);
        name = savedInstanceState.getString(STATE_NAME);
        imgPath = savedInstanceState.getString(STATE_PATH);
    }
}
