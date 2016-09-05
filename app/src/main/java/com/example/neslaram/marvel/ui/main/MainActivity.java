package com.example.neslaram.marvel.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.neslaram.marvel.R;
import com.example.neslaram.marvel.data.model.Character;
import com.example.neslaram.marvel.data.model.responses.CharacterResponse;
import com.example.neslaram.marvel.data.remote.service.MarvelApi;
import com.example.neslaram.marvel.presenter.main.MainPresenter;
import com.example.neslaram.marvel.presenter.main.impl.MainPresenterImpl;
import com.example.neslaram.marvel.ui.main.adapters.CharacterAdapter;
import com.example.neslaram.marvel.ui.main.adapters.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener<Character> {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewHeroes)
    RecyclerView recyclerView;

    private CharacterAdapter adapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        setRecyclerView();
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.getCharacters("");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }


    @Override
    public void setItems(List<Character> items) {
        adapter.addItems(items);
    }


    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClicked(int position, Character item) {

    }

    private void setRecyclerView() {
        adapter = new CharacterAdapter(new ArrayList<Character>(), this);
        recyclerView.setAdapter(adapter);
    }


    private void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }
}
