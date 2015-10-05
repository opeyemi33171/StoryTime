package com.example.opeyemi.storytime.Activites;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.opeyemi.storytime.Adapters.AboutBookAdapter;
import com.example.opeyemi.storytime.DataModels.Character;
import com.example.opeyemi.storytime.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AboutBookActivity extends ActionBarActivity {
    public Realm realm;
    public EditText bookDescriptionLabel;
    public static Character selectedCharacter;
    protected RealmResults<Character>characterResult;
    public static ArrayList<Character> characters;
    public static  AboutBookAdapter characterAdapter;

    protected FloatingActionButton addButton;
    protected Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Realm characterRealm=Realm.getInstance(this);
        setContentView(R.layout.activity_about_book);

        toolBar=(Toolbar)findViewById(R.id.app_bar);
        toolBar.setTitle(MainActivity_StoryBook.selectedBook.getName().toString());
        setSupportActionBar(toolBar);


        addButton=(FloatingActionButton)findViewById(R.id.characterAddButton);
        //setTitle(MainActivity_StoryBook.selectedBook.getName().toString());
        //MainActivity_StoryBook.adapter.notifyDataSetChanged();
        //TextView characterName=(TextView)findViewById(R.id.characterNameLabel);
        bookDescriptionLabel=(EditText)findViewById(R.id.bookDescriptionLabel);
       // final EditText characterNamer=(EditText)findViewById(R.id.characterNamer);

        final ListView characterListView=(ListView)findViewById(R.id.characterListView);
        characters=new ArrayList<Character>();
        characterAdapter=new AboutBookAdapter(AboutBookActivity.this,characters);
        characterListView.setAdapter(characterAdapter);
        RealmQuery characterQuery=characterRealm.where(Character.class).equalTo("storyOfOrigin", MainActivity_StoryBook.selectedBook.getName());
        characterResult=characterQuery.findAll();
        registerForContextMenu(characterListView);

        if(MainActivity_StoryBook.selectedBook.getDescription()!=null) {
            bookDescriptionLabel.setText(MainActivity_StoryBook.selectedBook.getDescription());
        }

        //realm object needs to be updated.
          realm=Realm.getInstance(this);
        bookDescriptionLabel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                 if (!hasFocus) {
                    realm.beginTransaction();
                    MainActivity_StoryBook.selectedBook.setDescription(bookDescriptionLabel.getText().toString());
                    realm.commitTransaction();

                }
            }
        });

        for(Character x: characterResult){
            characterAdapter.add(x);
        }


        characterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCharacter=(Character)characterListView.getItemAtPosition(position);
               Intent characterInfoNavigator = new Intent(AboutBookActivity.this,CharacterInfoActivity.class);
                startActivity(characterInfoNavigator);

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterDialog dialog=new CharacterDialog();
                dialog.show(getFragmentManager(),"dialog");

            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.delete_context_menu,menu);

    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        Character deleteCharcter=characters.get(index);
        Realm characterRealm =Realm.getInstance(AboutBookActivity.this);
        characterRealm.beginTransaction();
        deleteCharcter.removeFromRealm();
        characters.remove(index);
        characterAdapter.notifyDataSetChanged();
        characterRealm.commitTransaction();


        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id== R.id.favourite && MainActivity_StoryBook.selectedBook.isFavourite()==false){
            item.setIcon(R.drawable.ic_favorite_black_48dp);
            realm.beginTransaction();
            MainActivity_StoryBook.selectedBook.setFavourite(true);
            realm.commitTransaction();
        }
        else{
            item.setIcon(R.drawable.ic_favorite_border_black_48dp);
            realm.beginTransaction();
            MainActivity_StoryBook.selectedBook.setFavourite(false);
            realm.commitTransaction();

        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        realm.beginTransaction();
        MainActivity_StoryBook.selectedBook.setDescription(bookDescriptionLabel.getText().toString());
        realm.commitTransaction();
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        realm.beginTransaction();
        MainActivity_StoryBook.selectedBook.setDescription(bookDescriptionLabel.getText().toString());
        realm.commitTransaction();
        super.onPause();
    }
}
