package com.example.electronicdictionary.ui.topic;

import com.example.electronicdictionary.data.base.OnDataLoadedListener;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.repository.topic.TopicRepository;

import java.util.List;

public class TopicPresenter implements TopicContract.Presenter {

    private TopicContract.View view;
    private TopicRepository repository;

    public TopicPresenter(TopicContract.View view, TopicRepository repository) {
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
                view.showMsg(exception.getMessage());
            }
        });
    }
}
