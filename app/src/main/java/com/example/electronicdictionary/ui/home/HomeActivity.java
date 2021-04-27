package com.example.electronicdictionary.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.repository.home.HomeRepository;
import com.example.electronicdictionary.data.repository.home.HomeRepositoryImpl;

public class HomeActivity extends AppCompatActivity
        implements HomeContract.View {

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initPresenter();
    }

    private void initPresenter() {
        HomeRepository repository = new HomeRepositoryImpl();
        presenter = new HomePresenter(this, repository);
    }
}
