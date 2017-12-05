package com.thebeerdudes.thacher.roughdraught;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class EditBeerActivity extends AppCompatActivity {

    TextView lblName;
    TextView lblBrewery;
    TextView lblRating;
    TextView lblAbv;
    TextView lblIbu;
    TextView lblStyle;
    TextView lblDescription;

    private Button btnBack;
    private Button btnEdit;

    private Beer currentBeer;
    private Beer newBeer;

    protected int updateResult = 11;
    protected boolean isUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_beer);

        lblName = (TextView)findViewById(R.id.lblDetailName);
        lblBrewery = (TextView)findViewById(R.id.lblDetailBrewery);
        lblRating = (TextView)findViewById(R.id.lblDetailRating);
        lblAbv = (TextView)findViewById(R.id.lblDetailAbv);
        lblIbu = (TextView)findViewById(R.id.lblDetailIbu);
        lblStyle = (TextView)findViewById(R.id.lblDetailStyle);
        lblDescription = (TextView)findViewById(R.id.lblDetailDescription);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnEdit = (Button)findViewById(R.id.btnDetailEdit);

        Intent data = getIntent();
        final Beer beer = (Beer)data.getSerializableExtra("beer");
        updateViews(beer);
        setResult(10, getIntent());



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isUpdated==false) {
                    setResult(10, getIntent());
                    finish();
                }
                else{
                    setResult(11, getIntent());
                    getIntent().putExtra("new beer", newBeer);
                    finish();
                }

            }

        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(EditBeerActivity.this, AddBeerActivity.class);
                editIntent.putExtra("edit beer", beer);
                startActivityForResult(editIntent, 50);
            }
        });

    }

    private void updateViews(Beer beer){
        lblName.setText(beer.getName());
        lblBrewery.setText(beer.getBrewery());
        String ratingString = String.valueOf(beer.getRating());
        lblRating.setText(ratingString);
        lblStyle.setText(beer.getStyle());
        String abvString = beer.getAbv() + "%";
        lblAbv.setText(abvString);
        String ibuString = beer.getIbu() + " IBU";
        lblIbu.setText(ibuString);
        lblDescription.setText(beer.getDescription());

        if(beer.getRating()>=90){
            lblRating.setTextColor(Color.parseColor("#2196f3"));
        }
        else if(beer.getRating()<90 && beer.getRating()>=70){
            lblRating.setTextColor(Color.parseColor("#4caf50"));
        }
        else if(beer.getRating()<70 && beer.getRating()>=50){
            lblRating.setTextColor(Color.parseColor("#ff9800")); //Orange
        }
        else if(beer.getRating()<50 && beer.getRating()>=30){
            lblRating.setTextColor(Color.parseColor("#ff5722")); //Orange
        }
        else if(beer.getRating()<30){
            lblRating.setTextColor(Color.parseColor("#f44336")); //Orange
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==50){
            if(resultCode==RESULT_CANCELED) {
                setResult(10, getIntent());
                System.out.println("-----Edit Beer canceled-----");

            }else if(resultCode==RESULT_OK){
                newBeer = (Beer)data.getSerializableExtra("Beer");
                setResult(11, getIntent());
                updateViews(newBeer);
                isUpdated = true;
                System.out.println("-----Beer Updated-----");
            }
        }
    }
}
