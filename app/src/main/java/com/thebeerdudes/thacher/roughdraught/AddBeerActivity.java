package com.thebeerdudes.thacher.roughdraught;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        TextView lblTitle = (TextView)findViewById(R.id.lblTitle);


        final TextView txtName = (TextView) findViewById(R.id.txtName);
        final TextView txtBrewery = (TextView) findViewById(R.id.txtBrewery);
        final TextView txtStyle = (TextView) findViewById(R.id.txtStyle);
        final TextView txtAbv = (TextView) findViewById(R.id.txtAbv);
        final TextView txtIbu = (TextView) findViewById(R.id.txtIbu);
        final TextView txtRating = (TextView) findViewById(R.id.txtRating);
        final TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        beer = new Beer("Name Missing", "Brewery Missing", "Style Missing", 0, 0, 0, "");

        final Toast myToast = Toast.makeText(AddBeerActivity.this, "Rating is required", Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.TOP, 0, 0);

        if (getIntent().getSerializableExtra("edit beer") != null){

            btnAdd.setText("Save");
            lblTitle.setText("Edit Beer");
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

                if(txtName.getText().toString().equals("")){
                    System.out.println("Missing Name");
                    myToast.setText("Name is required");
                    myToast.show();
                    txtName.requestFocus();
                }
                else if(txtBrewery.getText().toString().equals("")){
                    System.out.println("Missing Brewery");
                    myToast.setText("Brewery is required");
                    myToast.show();
                    txtBrewery.requestFocus();
                }
                else if(txtRating.getText().toString().equals("")){
                    System.out.println("missing Rating");
                    myToast.setText("Rating is required");
                    myToast.show();
                    txtRating.requestFocus();
                }
                else if(txtAbv.getText().toString().equals("")){
                    System.out.println("missing ABV");
                    myToast.setText("ABV is required");
                    myToast.show();
                    txtAbv.requestFocus();
                }
                else if(txtStyle.getText().toString().equals("")){
                    System.out.println("missing Style");
                    myToast.setText("Style is required");
                    myToast.show();
                    txtStyle.requestFocus();
                }
                else if(Integer.parseInt(txtRating.getText().toString()) > 100 || Integer.parseInt(txtRating.getText().toString()) < 0){
                    System.out.println("missing Rating");
                    myToast.setText("Rating must be 0-100");
                    myToast.show();
                    txtRating.requestFocus();
                }
                else {
                    beer.setName(txtName.getText().toString());
                    beer.setBrewery(txtBrewery.getText().toString());
                    beer.setStyle(txtStyle.getText().toString());
                    beer.setAbv(Double.parseDouble(txtAbv.getText().toString()));
                    if(txtIbu.getText().toString().equals("")){
                        beer.setIbu(0);
                    } else {
                        beer.setIbu(Integer.parseInt(txtIbu.getText().toString()));
                    }
                    beer.setRating(Long.parseLong(txtRating.getText().toString()));
                    if(txtDescription.getText().toString().equals("")){
                        beer.setDescription("");
                    }
                    else {
                        beer.setDescription(txtDescription.getText().toString());
                    }

                    Intent i = getIntent();
                    i.putExtra("Beer", (Serializable) beer);
                    setResult(RESULT_OK, i);
                    finish();
                }
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
