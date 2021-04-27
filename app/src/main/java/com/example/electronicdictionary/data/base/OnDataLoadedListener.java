package com.example.electronicdictionary.data.base;

public interface OnDataLoadedListener<T> {
    void onSuccess(T t);

    void onFailure(Exception exception);
}
