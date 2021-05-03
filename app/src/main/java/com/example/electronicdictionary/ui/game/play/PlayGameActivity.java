package com.example.electronicdictionary.ui.game.play;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.electronicdictionary.R;

public class PlayGameActivity extends AppCompatActivity {

    private WebView webGame;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        String gameUrl = getIntent().getStringExtra("game");

        webGame = findViewById(R.id.web_game);
        webGame.getSettings().setLoadsImagesAutomatically(true);
        webGame.getSettings().setJavaScriptEnabled(true);
        webGame.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);;
        webGame.loadUrl(gameUrl);
    }
}
