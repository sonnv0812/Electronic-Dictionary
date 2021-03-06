package com.example.electronicdictionary.data.repository.home;

import com.example.electronicdictionary.data.base.OnDataLoadedListener;
import com.example.electronicdictionary.data.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public interface HomeRepository {
    void getData(OnDataLoadedListener<List<CategoryModel>> callback);
}
