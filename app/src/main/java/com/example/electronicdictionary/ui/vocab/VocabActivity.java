package com.example.electronicdictionary.ui.vocab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.model.VocabModel;
import com.example.electronicdictionary.data.repository.vocab.VocabRepository;
import com.example.electronicdictionary.data.repository.vocab.VocabRepositoryImpl;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class VocabActivity extends AppCompatActivity
        implements VocabContract.View, View.OnClickListener {

    private VocabContract.Presenter presenter;
    private CardView cvWord;
    private TextView textWord, textPronunciation, textExplain, textDescription, textShowDetail, textDidntKnow, textKnew;
    private ConstraintLayout constraintDetail, constraintFull;
    private ImageView imageVocab;
    private VocabModel vocab;
    private CategoryModel category;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        textWord = findViewById(R.id.text_word);
        textPronunciation = findViewById(R.id.text_pronunciation);
        textExplain = findViewById(R.id.text_explain);
        imageVocab = findViewById(R.id.image_vocab);
        textDescription = findViewById(R.id.text_description);
        textShowDetail = findViewById(R.id.text_show_detail);
        constraintDetail = findViewById(R.id.constrain_detail);
        textDidntKnow = findViewById(R.id.text_didnt_know);
        textKnew = findViewById(R.id.text_knew);
        constraintFull = findViewById(R.id.constraint_full);
        cvWord = findViewById(R.id.cv_word);

        vocab = (VocabModel) getIntent().getExtras().get("vocab");
        category = (CategoryModel) getIntent().getExtras().get("category");

        setupUI();

        initPresenter();
    }

    private void initPresenter() {
        VocabRepository repository = new VocabRepositoryImpl();
        presenter = new VocabPresenter(this, repository);
    }

    private void setupUI() {
        textWord.setText(vocab.getWord());
        textPronunciation.setText(vocab.getPronunciation());
        textExplain.setText(vocab.getMean());
        textDescription.setText(vocab.getDescription());
        Picasso.get().load(vocab.getImage()).into(imageVocab);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.text_show_detail:
                constraintFull.setLayoutTransition(new LayoutTransition());
                changeContent(false);
                break;
            case R.id.text_didnt_know:
                nextWord(false);
                break;
            case R.id.text_knew:
                nextWord(true);
//                Log.v("CATEGORY", vocab.toString());
                break;
        }
    }

    private void changeContent(boolean isExpanded) {
        if (isExpanded) {
            constraintDetail.setVisibility(View.GONE);
            textShowDetail.setVisibility(View.VISIBLE);
        } else {
            constraintDetail.setVisibility(View.VISIBLE);
            textShowDetail.setVisibility(View.GONE);
        }
    }

    private void loadData(List<VocabModel> list) {
        Random rand = new Random();
        VocabModel vocab = list.get(rand.nextInt(list.size()));
        textWord.setText(vocab.getWord());
        textPronunciation.setText(vocab.getPronunciation());
        textExplain.setText(vocab.getMean());
        textDescription.setText(vocab.getDescription());
        Picasso.get().load(vocab.getImage()).into(imageVocab);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    private void nextWord(boolean isKnown) {
        setAnimation(R.animator.animator_move_from_left);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                loadData(category.getVocabs());
                constraintFull.setLayoutTransition(null);
                changeContent(true);
                setAnimation(R.animator.animator_move_from_right);
            }
        });
    }

    private void setAnimation(int animation) {
        animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, animation);
        animatorSet.setTarget(cvWord);
        animatorSet.start();
    }
}