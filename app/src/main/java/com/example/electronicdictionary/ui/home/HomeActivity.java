package com.example.electronicdictionary.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.adapter.TextSearchAdapter;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.model.VocabModel;
import com.example.electronicdictionary.data.repository.home.HomeRepository;
import com.example.electronicdictionary.data.repository.home.HomeRepositoryImpl;
import com.example.electronicdictionary.ui.game.choose.ChooseGameActivity;
import com.example.electronicdictionary.ui.topic.TopicActivity;
import com.example.electronicdictionary.ui.vocab.VocabActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements HomeContract.View {

    private CardView cvLearning, cvPlayingGame;
    private List<String> searchResult = new ArrayList<>();
    private EditText editSearch;
    private HomeContract.Presenter presenter;
    private ListView listSearchResult;
    private List<CategoryModel> categoryModelList;
    private TextSearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvLearning = findViewById(R.id.cv_learning);
        cvPlayingGame = findViewById(R.id.cv_playing_game);
        editSearch = findViewById(R.id.edit_search);
        listSearchResult = findViewById(R.id.list_search_result);
        listSearchResult.setVisibility(View.GONE);

        initPresenter();
        presenter.getData();
    }

    private void initPresenter() {
        HomeRepository repository = new HomeRepositoryImpl();
        presenter = new HomePresenter(this, repository);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cvLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TopicActivity.class);
                startActivity(intent);
            }
        });

        cvPlayingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChooseGameActivity.class);
                startActivity(intent);
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = s.toString();
                presenter.handleSearch(categoryModelList, key.toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String wordClick = (String) adapter.getItem(position);
                for (CategoryModel category : categoryModelList) {
                    for (VocabModel vocab : category.getVocabs()) {
                        if (vocab.getWord().equals(wordClick)) {
                            Intent intent = new Intent(HomeActivity.this, VocabActivity.class);
                            intent.putExtra("vocab", vocab);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void updateData(List<CategoryModel> data) {
        categoryModelList = data;
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void searchResult(List<String> searchData) {
        if (searchData != null) {
            listSearchResult.setVisibility(View.VISIBLE);
            searchResult = searchData;
            adapter = new TextSearchAdapter(searchResult);
            listSearchResult.setAdapter(adapter);
        }
    }
}
