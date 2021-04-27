package com.example.electronicdictionary.ui.vocab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.repository.vocab.VocabRepository;
import com.example.electronicdictionary.data.repository.vocab.VocabRepositoryImpl;

public class VocabActivity extends AppCompatActivity
        implements VocabContract.View {

    private VocabContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        initPresenter();
    }

    private void initPresenter() {
        VocabRepository repository = new VocabRepositoryImpl();
        presenter = new VocabPresenter(this, repository);
    }
}