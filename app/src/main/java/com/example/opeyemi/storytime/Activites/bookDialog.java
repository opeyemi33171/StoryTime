package com.example.opeyemi.storytime.Activites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.opeyemi.storytime.DataModels.Book;
import com.example.opeyemi.storytime.R;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by opeyemi on 23/08/2015.
 */
public class bookDialog extends DialogFragment {
    LayoutInflater inflater;
    View v;
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public  String bookName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        inflater=getActivity().getLayoutInflater();
        v=inflater.inflate(R.layout.dialog_layout, null);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            EditText bookNamer=(EditText)v.findViewById(R.id.Namer);
                bookName=bookNamer.getText().toString();
                Realm newBook=Realm.getInstance(getActivity());
                newBook.beginTransaction();
                Book newBookItem=newBook.createObject(Book.class);
                newBookItem.setName(getBookName());
                newBook.commitTransaction();
                MainActivity_StoryBook.books.add(newBookItem);
                MainActivity_StoryBook.adapter.notifyDataSetChanged();


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
