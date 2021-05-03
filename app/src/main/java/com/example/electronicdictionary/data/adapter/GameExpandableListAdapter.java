package com.example.electronicdictionary.data.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.electronicdictionary.R;
import com.example.electronicdictionary.data.model.GameModel;
import com.example.electronicdictionary.data.model.GameUnitModel;

import java.util.List;

public class GameExpandableListAdapter extends BaseExpandableListAdapter {

    private List<GameUnitModel> gameUnitModelList;

    public GameExpandableListAdapter(List<GameUnitModel> gameUnitModelList) {
        this.gameUnitModelList = gameUnitModelList;
    }

    @Override
    public int getGroupCount() {
        return gameUnitModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gameUnitModelList.get(groupPosition).getGames().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gameUnitModelList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gameUnitModelList.get(groupPosition).getGames().get(childPosition);
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
        convertView = inflater.inflate(R.layout.item_list_choose_game, parent, false);

        GameUnitModel gameUnitModel = (GameUnitModel) getGroup(groupPosition);

        TextView textUnit = convertView.findViewById(R.id.text_unit);
        ImageView imageArrow = convertView.findViewById(R.id.image_arrow_game);
        CardView cvChooseGame = convertView.findViewById(R.id.cv_choose_game);

        cvChooseGame.setCardBackgroundColor(Color.parseColor(gameUnitModel.getColor()));
        textUnit.setText(gameUnitModel.getName());
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
        convertView = inflater.inflate(R.layout.item_list_game, parent, false);
        TextView textGame = convertView.findViewById(R.id.text_game);
        GameModel gameModel = (GameModel) getChild(groupPosition, childPosition);
        int index = childPosition + 1;
        textGame.setText(index + ". " + gameModel.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
