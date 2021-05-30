package com.example.foodfactory;

import android.app.SearchManager;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    DataBase mydb;
    ListView ViewSearch;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayList<String> listData = new ArrayList<>();

        mydb = new DataBase(this);
        ViewSearch=findViewById(R.id.listViewContact);
        populateListView();
        loadData();

    }
    private void loadData() {
        DataBase databaseHelper = new DataBase(getApplicationContext());
        List<String> contacts = (List<String>) databaseHelper.displayFood();
        if (contacts != null) {
            //listViewContact.setAdapter(new ContactListAdapter(getApplicationContext(), contacts));
        }
    }
    private void populateListView() {

        Cursor data = mydb.displayFood();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(0));
        }
       // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_display, listData);
        //ViewSearch.setAdapter(adapter);
    }
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void searchContact(String keyword) {
        DataBase databaseHelper = new DataBase(getApplicationContext());
        List<String> contacts = (List<String>) databaseHelper.searchFood(keyword);
        if (contacts != null) {
           // ViewSearch.setAdapter(new ContactListAdapter(getApplicationContext(), contacts));
        }
    }

}
