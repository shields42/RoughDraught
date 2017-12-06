package com.thebeerdudes.thacher.roughdraught;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SortActivity extends AppCompatActivity {

    protected final int CANCEL = 0;
    protected final int SORT_RATING = 1;
    protected final int SORT_NAME = 2;
    protected final int SORT_BREWERY = 3;
    protected final int SORT_ABV = 4;
    protected final int SORT_IBU = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_addbeertoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnNameA = (Button)findViewById(R.id.btnNameSortA);
        Button btnNameD = (Button)findViewById(R.id.btnNameSortD);
        Button btnRatingA = (Button) findViewById(R.id.btnRatingSortA);
        Button btnRatingD = (Button) findViewById(R.id.btnRatingSortD);
        Button btnBreweryA = (Button)findViewById(R.id.btnBrewerySortA);
        Button btnBreweryD = (Button)findViewById(R.id.btnBrewerySortD);
        Button btnAbvA = (Button)findViewById(R.id.btnAbvSortA);
        Button btnAbvD = (Button)findViewById(R.id.btnAbvSortD);


        btnRatingA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_RATING, getIntent());
                finish();
            }
        });

        btnRatingD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_RATING + 10, getIntent());
                finish();
            }
        });

        btnNameA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_NAME, getIntent());
                finish();
            }
        });

        btnNameD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_NAME + 10, getIntent());
                finish();
            }
        });

        btnBreweryA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_BREWERY, getIntent());
                finish();
            }
        });

        btnBreweryD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_BREWERY + 10, getIntent());
                finish();
            }
        });

        btnAbvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_ABV, getIntent());
                finish();
            }
        });

        btnAbvD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_ABV + 10, getIntent());
                finish();
            }
        });
    }
}
