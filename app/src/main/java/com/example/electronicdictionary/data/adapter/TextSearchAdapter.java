package com.example.electronicdictionary.data.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.electronicdictionary.R;

import java.util.ArrayList;
import java.util.List;

public class TextSearchAdapter extends BaseAdapter {
    public TextSearchAdapter(List<String> vocabs) {
        this.vocabs = vocabs;
    }

    private List<String> vocabs = new ArrayList<>();

    @Override
    public int getCount() {
        return vocabs == null ? 0 : vocabs.size();
    }

    @Override
    public Object getItem(int position) {
        return vocabs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewTextSearch;
        if (convertView == null) {
            viewTextSearch = View.inflate(parent.getContext(), R.layout.item_text_search, null);
        } else viewTextSearch = convertView;

        TextView textResult = viewTextSearch.findViewById(R.id.text_search_result);
        textResult.setText(vocabs.get(position));

        return viewTextSearch;
    }
}
