package com.example.foodfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //DataBase mydb;

    private Button registerProduct;
    private Button displayProduct;
    private Button availability;
    private Button editProduct;
    private Button search;
    private Button recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mydb=new DataBase(this);

        registerProduct = (Button) findViewById(R.id.btn_reg_product);
        displayProduct = (Button) findViewById(R.id.btn_display_product);
        availability = (Button) findViewById(R.id.btn_availability);
        editProduct = (Button) findViewById(R.id.btn_edit_product);
        search = (Button) findViewById(R.id.btn_search);
        recipe = (Button) findViewById(R.id.btn_recipe);


        registerProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, RegisterProductActivity.class));

            }
        });
        displayProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, DisplayProductActivity.class));
                Intent i = new Intent(MainActivity.this, DisplayProductActivity.class);
                startActivity(i);
            }
        });
        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AvailabilityActivity.class));
            }
        });
        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditProductActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecipeActivity.class));
            }
        });


    }
}
