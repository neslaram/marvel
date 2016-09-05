package com.example.neslaram.marvel.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.neslaram.marvel.R;
import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.presenter.main.MainPresenter;
import com.example.neslaram.marvel.presenter.main.impl.MainPresenterImpl;
import com.example.neslaram.marvel.ui.main.adapters.CharacterAdapter;
import com.example.neslaram.marvel.ui.main.adapters.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener<Character> {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewHeroes)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private CharacterAdapter adapter;
    private MainPresenter mainPresenter;
    private GridLayoutManager layoutManager;
    private boolean isLoading;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        setRecyclerView();
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.getCharacters(adapter.getItemCount());
        isLoading = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
        isLoading = false;
    }

    @Override
    public void setItems(List<Character> items) {
        isLoading = false;
        adapter.addItems(items);
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
        isLoading = false;
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClicked(int position, Character item) {

    }

    private void setRecyclerView() {
        adapter = new CharacterAdapter(new ArrayList<Character>(), this);
        recyclerView.setAdapter(adapter);
        layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.clearOnScrollListeners();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                        if (pastVisiblesItems + visibleItemCount >= totalItemCount) {
                            if (!isLoading) {
                                isLoading = true;
                                mainPresenter.getCharacters(adapter.getItemCount());
                            }

                        }
                        break;
                }
            }
        });
    }


    private void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }
}