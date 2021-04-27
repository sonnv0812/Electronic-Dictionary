package com.example.electronicdictionary.ui.home;

import com.example.electronicdictionary.data.repository.home.HomeRepository;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private HomeRepository repository;

    public HomePresenter(HomeContract.View view, HomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }
}
