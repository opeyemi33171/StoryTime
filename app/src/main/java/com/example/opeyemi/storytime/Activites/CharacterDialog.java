package com.example.opeyemi.storytime.Activites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.opeyemi.storytime.DataModels.*;
import com.example.opeyemi.storytime.DataModels.Character;
import com.example.opeyemi.storytime.R;

import io.realm.Realm;

/**
 * Created by opeyemi on 03/09/2015.
 */
public class CharacterDialog extends DialogFragment {
    LayoutInflater inflater;
    View v;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.character_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText CharacterNamer = (EditText) v.findViewById(R.id.CharNamer);
                String characterName = CharacterNamer.getText().toString();
                Realm newCharacter = Realm.getInstance(getActivity());
                newCharacter.beginTransaction();
                Character character = newCharacter.createObject(Character.class);
                character.setName(characterName);
                character.setStoryOfOrigin(MainActivity_StoryBook.selectedBook.getName());
                newCharacter.commitTransaction();
                AboutBookActivity.characters.add(character);
                AboutBookActivity.characterAdapter.notifyDataSetChanged();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();


            }
        });
        return builder.create();
    }
}
