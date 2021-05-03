package com.example.electronicdictionary.data.repository.topic;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.electronicdictionary.data.base.OnDataLoadedListener;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.model.VocabModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopicRepositoryImpl implements TopicRepository {

    private static final String ELECTRIC_CURRENT = "ELectric current";
    private static final String ELECTRONS = "Electrons";

    @Override
    public void getData(OnDataLoadedListener<List<CategoryModel>> callback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        List<CategoryModel> listCategory = new ArrayList<>();
        List<VocabModel> vocabElectricCurrent = new ArrayList<>();
        List<VocabModel> vocabElectron = new ArrayList<>();

        ref.child(ELECTRIC_CURRENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot vocabSnapshot: snapshot.getChildren()) {
                    VocabModel vocab = vocabSnapshot.getValue(VocabModel.class);
                    vocabElectricCurrent.add(vocab);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onFailure(error.toException());
            }
        });

        ref.child(ELECTRONS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot vocabSnapshot: snapshot.getChildren()) {
                    VocabModel vocab = vocabSnapshot.getValue(VocabModel.class);
                    vocabElectron.add(vocab);
//                    Log.v("CATEGORY", vocab.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onFailure(error.toException());
            }
        });

        listCategory.add(new CategoryModel("Electric Current", "#e0be24", vocabElectricCurrent));
        listCategory.add(new CategoryModel("Electron", "#660766", vocabElectron));

        callback.onSuccess(listCategory);
    }
}
