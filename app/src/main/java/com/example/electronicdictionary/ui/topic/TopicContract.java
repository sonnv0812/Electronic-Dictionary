package com.example.electronicdictionary.ui.topic;

import com.example.electronicdictionary.data.model.CategoryModel;

import java.util.List;

public interface TopicContract {
    interface View {
        void updateData(List<CategoryModel> listCategory);

        void showMsg(String msg);
    }

    interface Presenter {
        void getData();
    }
}
