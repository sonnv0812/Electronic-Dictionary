package com.example.electronicdictionary.data.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.model.CategoryModel;
import com.example.electronicdictionary.data.model.VocabModel;

import java.util.HashMap;
import java.util.List;

public class ElectronicExpandableListAdapter extends BaseExpandableListAdapter {

    private List<CategoryModel> categoryModelList;

    public ElectronicExpandableListAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @Override
    public int getGroupCount() {
        return categoryModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryModelList.get(groupPosition).getVocabs().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryModelList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoryModelList.get(groupPosition).getVocabs().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.item_list_category, parent, false);
        TextView textCategory = convertView.findViewById(R.id.text_category);
        ImageView imageArrow = convertView.findViewById(R.id.image_arrow);
        CardView cardCategory = convertView.findViewById(R.id.cv_category);
        CategoryModel categoryModel = (CategoryModel) getGroup(groupPosition);

        cardCategory.setCardBackgroundColor(Color.parseColor(categoryModel.getColor()));
        textCategory.setText(categoryModel.getName());
        if (isExpanded) {
            imageArrow.setImageResource(R.drawable.ic_arrow_up);
        } else {
            imageArrow.setImageResource(R.drawable.ic_arrow_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.item_list_vocab, parent, false);

        VocabModel vocab = (VocabModel) getChild(groupPosition, childPosition);

        TextView textVocab = convertView.findViewById(R.id.text_vocab_expandable);
        TextView textLastTime = convertView.findViewById(R.id.text_last_time);
        ProgressBar progressVocab = convertView.findViewById(R.id.progress_vocab);

        textVocab.setText(vocab.getWord());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
