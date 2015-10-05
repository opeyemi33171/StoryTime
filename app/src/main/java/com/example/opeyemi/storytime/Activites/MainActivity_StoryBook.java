package com.example.opeyemi.storytime.Activites;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.example.opeyemi.storytime.Adapters.listViewBookAdapter;
import com.example.opeyemi.storytime.DataModels.Book;
import com.example.opeyemi.storytime.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;



public class MainActivity_StoryBook extends ActionBarActivity  {
    public static Book selectedBook;
    public static listViewBookAdapter adapter;
    public ListView bookListView;
    public static ArrayList<Book>books;
    protected RealmResults<Book>bookResults;
    protected bookDialog dialogG;
    protected  FloatingActionButton floatButton;

    protected Toolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setTitle("Story Book");
         setContentView(R.layout.activity_main_activity__story_book);
          //Realm.deleteRealmFile(MainActivity_StoryBook.this);

        toolBar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);


         final Realm realm=Realm.getInstance(MainActivity_StoryBook.this);
         floatButton=(FloatingActionButton)findViewById(R.id.addButton);
         books=new ArrayList<Book>();
         adapter=new listViewBookAdapter(MainActivity_StoryBook.this,books);
         bookListView=(ListView)findViewById(R.id.bookListView);
         registerForContextMenu(bookListView);
         RealmQuery<Book>bookQuery=realm.where(Book.class);

         bookResults=bookQuery.findAll();
        for(Book book:bookResults){
            adapter.add(book);
        }
         bookListView.setAdapter(adapter);
         bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectedBook = adapter.getItem(position);
                 Intent characterActivityNavigator = new Intent(MainActivity_StoryBook.this, AboutBookActivity.class);
                 startActivity(characterActivityNavigator);
             }
         });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogG = new bookDialog();
                dialogG.show(getFragmentManager(), "Dialog");
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.delete_context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        Realm realm=Realm.getInstance(MainActivity_StoryBook.this);
        realm.beginTransaction();
        Book deleteBook=bookResults.get(index);
        deleteBook.removeFromRealm();
        books.remove(index);
        realm.commitTransaction();
        adapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity__story_book, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }



}
