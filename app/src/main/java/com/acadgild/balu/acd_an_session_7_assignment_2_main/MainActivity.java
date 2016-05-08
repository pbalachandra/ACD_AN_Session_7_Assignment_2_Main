package com.acadgild.balu.acd_an_session_7_assignment_2_main;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    TextView textView;
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();

        String[] product_string = {"Alpha","AngularJS","Beta","Bigdata", "Cupcake", "Donut",
                                   "Eclair", "Froyo", "GingerBread", "Honeycomb", "Hadoop",
                                   "IcecreamSandwich", "Java", "Jellybean", "Kitkat", "Lollipop",
                                   "Marshmallow", "Oracle", "PHP", "SQLite", "WebDesigning"};
        textView = (TextView) findViewById(R.id.textView);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        dbHelper.clear_table();
        for (int i = 0; i < product_string.length; i++)
        {
            dbHelper.add_new_product(product_string[i]);
        }
        arrayList = dbHelper.get_all_products();

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                            android.R.layout.simple_dropdown_item_1line, arrayList);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);

    }
}
