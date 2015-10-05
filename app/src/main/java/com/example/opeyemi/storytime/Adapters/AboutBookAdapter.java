package com.example.opeyemi.storytime.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opeyemi.storytime.DataModels.Character;
import com.example.opeyemi.storytime.R;

import java.util.ArrayList;

/**
 * Created by opeyemi on 03/08/2015.
 */
public class AboutBookAdapter extends ArrayAdapter<Character> {

    public AboutBookAdapter(Context context, ArrayList<Character>characters) {
        super(context, R.layout.about_book_row_layout,characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator=LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.about_book_row_layout, parent, false);
        Character currentCharacter=getItem(position);
        TextView characterNameLabel=(TextView)customView.findViewById(R.id.characterNameLabel);
        characterNameLabel.setText(currentCharacter.getName());
        return customView;
    }
}
