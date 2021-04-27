package com.example.electronicdictionary.ui.vocab;

import com.example.electronicdictionary.data.repository.vocab.VocabRepository;

public class VocabPresenter implements VocabContract.Presenter {

    private VocabContract.View view;
    private VocabRepository repository;

    public VocabPresenter(VocabContract.View view, VocabRepository repository) {
        this.view = view;
        this.repository = repository;
    }
}
