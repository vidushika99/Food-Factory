package com.example.foodfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterProductActivity extends AppCompatActivity {
    DataBase mydb;
    EditText et_name, et_weight, et_price, et_description;
    Button reg_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);

        mydb = new DataBase(this);


        et_name = (EditText) findViewById(R.id.etName);
        et_weight = (EditText) findViewById(R.id.etWeight);
        et_price = (EditText) findViewById(R.id.etPrice);
        et_description = (EditText) findViewById(R.id.etDes);
        reg_sub = (Button) findViewById(R.id.btnRegSubmit);
    }

//submit button method
   public void onClick(View view) {
//try {
    String name = et_name.getText().toString();
    Integer weight = Integer.valueOf(et_weight.getText().toString());
    Integer price = Integer.valueOf(et_price.getText().toString());
    String description = et_description.getText().toString();
//}catch(InputMismatchException e){}

       //insert data to database
        boolean bool = mydb.insertData(name, weight, price, description);

        if (bool == true) {
            Toast.makeText(RegisterProductActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegisterProductActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }
        et_name.setText("");
        et_weight.setText("");
        et_price.setText("");
        et_description.setText("");

       /*Intent i = new Intent(RegisterProductActivity.this, DisplayProductActivity.class);
       startActivity(i);*/

    }
    public void cancel(View v) {
        et_name.setText("");
        et_weight.setText("");
        et_price.setText("");
        et_description.setText("");
    }


}
