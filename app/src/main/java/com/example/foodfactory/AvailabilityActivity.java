package com.example.foodfactory;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AvailabilityActivity extends AppCompatActivity {
    ArrayList<String> checkedItems1 = new ArrayList<>();

    ListView LTavailability;
    Button btSave;
    CheckedTextView checked;
    DataBase mydb;
    DisplayProductActivity input=new DisplayProductActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        //checked.setChecked(true);
       // checked.isChecked();

        LTavailability = (ListView) findViewById(R.id._dynamicAvailability);
        btSave = (Button) findViewById(R.id.buttonSave);
        LTavailability.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mydb = new DataBase(this);
        populateListView();
    }
//display available data list
    public void populateListView(){

        Cursor data = mydb.displayAvailability();
        ArrayList<String> listData1 = new ArrayList<>();
        while (data.moveToNext()) {
            listData1.add(data.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkbox, R.id.txt_Availability, listData1);
        LTavailability.setAdapter(adapter);
        LTavailability.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if (checkedItems1.contains(selectedItem))
                    checkedItems1.remove(selectedItem);
                else
                    checkedItems1.add(selectedItem);

            }
        });


    }
//add checked items to database
    public void saveFood(View view){

        String checkItems = "";
        for (String item : checkedItems1) {
            mydb.addAvailabilityList(item);

            if (checkItems == "") {
                checkItems = item;

            }
            else
                checkItems += "/" + item;

        }
        Toast.makeText(this, checkItems+" "+"added", Toast.LENGTH_LONG).show();


    }


}
