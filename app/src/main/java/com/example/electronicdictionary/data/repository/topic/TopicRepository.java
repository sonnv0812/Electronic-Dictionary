package com.example.electronicdictionary.data.repository.topic;

import com.example.electronicdictionary.data.base.OnDataLoadedListener;
import com.example.electronicdictionary.data.model.CategoryModel;

import java.util.List;

public interface TopicRepository {
    void getData(OnDataLoadedListener<List<CategoryModel>> callback);
}
