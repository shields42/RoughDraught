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

        Button btnName = (Button)findViewById(R.id.btnNameSort);
        Button btnRating = (Button) findViewById(R.id.btnRatingSort);
        Button btnBrewery = (Button)findViewById(R.id.btnBrewerySort);
        Button btnAbv = (Button)findViewById(R.id.btnAbvSort);
        Button btnIbu = (Button)findViewById(R.id.btnIbuSort);


        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_RATING);
                finish();
            }
        });

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(SORT_NAME);
                finish();
            }
        });
    }
}
