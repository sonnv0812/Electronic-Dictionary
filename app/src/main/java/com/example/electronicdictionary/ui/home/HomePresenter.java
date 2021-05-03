package com.example.electronicdictionary.ui.home;

import android.util.Log;

import com.example.electronicdictionary.data.base.OnDataLoadedListener;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.model.VocabModel;
import com.example.electronicdictionary.data.repository.home.HomeRepository;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private HomeRepository repository;

    public HomePresenter(HomeContract.View view, HomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getData() {
        repository.getData(new OnDataLoadedListener<List<CategoryModel>>() {
            @Override
            public void onSuccess(List<CategoryModel> categoryModels) {
                view.updateData(categoryModels);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }

    @Override
    public void handleSearch(List<CategoryModel> categoryModelList, String key) {
        List<String> vocabs = new ArrayList<>();
        for (CategoryModel category : categoryModelList) {
            for (VocabModel vocab : category.getVocabs()) {
                vocabs.add(vocab.getWord());
            }
        }

        List<String> searchResult = new ArrayList<>();
        if (key != null && key.length() >= 2) {
            String regexSearch = "(" + key + ")" + "([a-z])+";
            Log.v("SEARCH", regexSearch);
            for (String string : vocabs) {
                if (string.toLowerCase().matches(regexSearch)) {
                    searchResult.add(string);
                    Log.v("SEARCH", string);
                }
            }
        }
        Log.v("SEARCH", searchResult.toString());
        view.searchResult(searchResult);
    }
}
