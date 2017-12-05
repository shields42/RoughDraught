package com.thebeerdudes.thacher.roughdraught;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class AddBeerActivity extends AppCompatActivity {

    protected Beer beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_addbeertoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnAdd = (Button) findViewById(R.id.btnSubmit);
        final TextView txtName = (TextView) findViewById(R.id.txtName);
        final TextView txtBrewery = (TextView) findViewById(R.id.txtBrewery);
        final TextView txtStyle = (TextView) findViewById(R.id.txtStyle);
        final TextView txtAbv = (TextView) findViewById(R.id.txtAbv);
        final TextView txtIbu = (TextView) findViewById(R.id.txtIbu);
        final TextView txtRating = (TextView) findViewById(R.id.txtRating);
        final TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        beer = null;

        if (getIntent().getSerializableExtra("edit beer") != null){
            Beer editBeer = (Beer)getIntent().getSerializableExtra("edit beer");
            txtName.setText(editBeer.getName());
            txtBrewery.setText(editBeer.getBrewery());
            txtRating.setText(String.valueOf(editBeer.getRating()));
            txtAbv.setText(String.valueOf(editBeer.getAbv()));
            txtIbu.setText(String.valueOf(editBeer.getIbu()));
            txtStyle.setText(editBeer.getStyle());
            txtDescription.setText(editBeer.getDescription());
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beer = new Beer();
                beer.setName(txtName.getText().toString());
                beer.setBrewery(txtBrewery.getText().toString());
                beer.setStyle(txtStyle.getText().toString());
                beer.setAbv(Double.parseDouble(txtAbv.getText().toString()));
                beer.setIbu(Integer.parseInt(txtIbu.getText().toString()));
                beer.setRating(Long.parseLong(txtRating.getText().toString()));
                beer.setDescription(txtDescription.getText().toString());

                Intent i = getIntent();
                i.putExtra("Beer", (Serializable)beer);
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addbeermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
