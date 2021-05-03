package com.example.electronicdictionary.ui.home;

import com.example.electronicdictionary.data.model.CategoryModel;

import java.util.List;

public interface HomeContract {
    interface View {
        void updateData(List<CategoryModel> data);

        void showMsg(String msg);

        void searchResult(List<String> searchData);
    }

    interface Presenter {
        void getData();

        void handleSearch(List<CategoryModel> categoryModelList, String key);
    }
}
