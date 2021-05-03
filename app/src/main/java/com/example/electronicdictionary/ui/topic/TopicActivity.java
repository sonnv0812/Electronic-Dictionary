package com.example.electronicdictionary.ui.topic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.adapter.ElectronicExpandableListAdapter;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.model.VocabModel;
import com.example.electronicdictionary.data.repository.topic.TopicRepository;
import com.example.electronicdictionary.data.repository.topic.TopicRepositoryImpl;
import com.example.electronicdictionary.ui.vocab.VocabActivity;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity
        implements TopicContract.View {

    private TopicPresenter presenter;
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private ExpandableListView elvElectronic;
    private ElectronicExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        initPresenter();
        presenter.getData();

        elvElectronic = findViewById(R.id.expandable_list_category);
        adapter = new ElectronicExpandableListAdapter(categoryModelList);
        elvElectronic.setAdapter(adapter);
    }

    private void initPresenter() {
        TopicRepository repository = new TopicRepositoryImpl();
        presenter = new TopicPresenter(this, repository);
    }

    @Override
    protected void onResume() {
        super.onResume();
        elvElectronic.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                VocabModel vocab = categoryModelList.get(groupPosition).getVocabs().get(childPosition);
                Intent intent = new Intent(TopicActivity.this, VocabActivity.class);
                intent.putExtra("vocab", vocab);
                intent.putExtra("category", categoryModelList.get(groupPosition));
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public void updateData(List<CategoryModel> listCategory) {
        categoryModelList = listCategory;
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
