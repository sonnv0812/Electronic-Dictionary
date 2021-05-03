package com.example.electronicdictionary.ui.game.choose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.adapter.GameExpandableListAdapter;
import com.example.electronicdictionary.data.model.GameModel;
import com.example.electronicdictionary.data.model.GameUnitModel;
import com.example.electronicdictionary.ui.game.play.PlayGameActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseGameActivity extends AppCompatActivity {

    private ExpandableListView epdChooseGame;
    private GameExpandableListAdapter adapter;
    private List<GameUnitModel> games = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        initData();
        epdChooseGame = findViewById(R.id.epd_choose_game);

        adapter = new GameExpandableListAdapter(games);

        epdChooseGame.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        epdChooseGame.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(ChooseGameActivity.this, PlayGameActivity.class);
                intent.putExtra("game", games.get(groupPosition).getGames().get(childPosition).getGameUrl());
                startActivity(intent);
                return true;
            }
        });
    }

    private void initData() {
        List<GameModel> unitOne = new ArrayList<>();
        List<GameModel> unitTwo = new ArrayList<>();

        unitOne.add(new GameModel("Wordsearch", "https://wordwall.net/resource/15564509/unit-1-electrons"));
        unitOne.add(new GameModel("Find the match", "https://wordwall.net/resource/15563949"));
        unitOne.add(new GameModel("Maze chase", "https://wordwall.net/resource/15564611"));
        unitTwo.add(new GameModel("Cross the word", "https://wordwall.net/resource/15564237"));
        unitTwo.add(new GameModel("Flip titles", "https://wordwall.net/resource/15564675"));
        unitTwo.add(new GameModel("Gameshow quizz", "https://wordwall.net/resource/15564720"));
        games.add(new GameUnitModel("Unit 1", "#0c727d", unitOne ));
        games.add(new GameUnitModel("Unit 2", "#2b7a59", unitTwo ));
    }
}