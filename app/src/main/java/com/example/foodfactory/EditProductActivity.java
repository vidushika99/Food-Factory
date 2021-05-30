package com.example.foodfactory;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class EditProductActivity extends AppCompatActivity {

    private DataBase mydb;

    ArrayList<String> selectedItems = new ArrayList<>();
    ListView listView;

    EditText et_name, et_weight, et_price, et_description;
    Button reg_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        mydb = new DataBase(this);


        et_name = (EditText) findViewById(R.id.etName);
        et_weight = (EditText) findViewById(R.id.etWeight);
        et_price = (EditText) findViewById(R.id.etPrice);
        et_description = (EditText) findViewById(R.id.etDes);
        reg_sub = (Button) findViewById(R.id.btnRegSubmit);

       // mydb = new DataBase(this);

        listView = findViewById(R.id.listViewEdit);


        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mydb = new DataBase(this);
        populateListView();
    }

//display list of items
    private void populateListView() {

        Cursor data = mydb.displayFood();
        ArrayList<String> ltData = new ArrayList<>();
        final ArrayList<String> listData1 = new ArrayList<>();

        while (data.moveToNext()) {

            listData1.add(data.getString(0).toString()+data.getString(1).toString()+data.getString(2).toString()+data.getString(3).toString());
            ltData.add(data.getString(0).toString());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.edit_xml, R.id.txt_title, ltData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item

                //String selectedItem = listData1.get(listView.getSelectedItemPosition());
                String selectedItem=((TextView)view).getText().toString();
                if (selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem);

                else
                    selectedItems.add(selectedItem);

                String checkItems = "";
                for (String item : selectedItems) {
                    mydb.displayFood();
                    if (checkItems == ""){
                        checkItems = item;
                    }
                    else
                        checkItems += "/" + item;


                }
                Toast.makeText(getApplicationContext(), checkItems, Toast.LENGTH_LONG).show();;
            }

        });
        //clear edit texts

        et_name.setText("");
        et_weight.setText("");
        et_price.setText("");
        et_description.setText("");

    }
     //udate data
    public void updateData(View view) {
        boolean result = mydb.updateEditData(et_name.getText().toString(),Integer.valueOf(et_weight.getText().toString()),Integer.valueOf(et_price.getText().toString()),et_description.getText().toString());

        if (result){
            Toast.makeText(getApplicationContext(),"DATA UPDATED",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"SOMETHING WENT WRONG",Toast.LENGTH_SHORT).show();
        }
    }

}
