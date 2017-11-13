package com.thebeerdudes.thacher.roughdraught;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<Beer> beersList;
    protected ListView lvMain;
    protected MenuItem btnSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Main List
        lvMain = (ListView) findViewById(R.id.lvBeers);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beersList = new ArrayList<>();
        beersList.add(new Beer("Cavu", "Ale", "Blonde Ale", "NoDa Brewing Company", "missing", 3, 5, 100, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Corona Extra", "Lager", "Mexican Lager", "Modelo", "missing", 1, 3, 68, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Sierra Nevada Pale Ale", "Ale", "Pale Ale", "Sierra Nevada", "missing", 2, 5, 95, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Bud Light", "Ale", "Piss Water", "Budweiser", "missing", 1, 1, 12, "It's bad.", "missing", "missing"));
        beersList.add(new Beer("Pabst Blue Ribbon", "Lager", "Mexican Lager", "PBR", "missing", 1, 1, 8, "It's gross.", "missing", "missing"));
        beersList.add(new Beer("Torpedo Extra", "Ale", "Extra IPA", "Sierra Nevada", "missing", 1, 4, 88, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Hop Drop 'n Roll", "Ale", "IPA", "NoDa Brewing Company", "missing", 3, 5, 95, "It's a great beer.", "missing", "missing"));
        beersList.add(new Beer("Celebration Ale", "Ale", "Christmas Ale", "Sierra Nevada", "missing", 3, 4, 87, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Tropical IPA", "Ale", "West Coast IPA", "Sierra Nevada", "missing", 2, 4, 83, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("OMB Copper", "Ale", "German Altbeir", "Olde Mecklenburg Brewing", "missing", 1, 4, 87, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Oktoberfest", "Lager", "Oktoberfest Lager", "Sierra Nevada", "missing", 1, 4, 84, "It's a good beer.", "missing", "missing"));
        beersList.add(new Beer("Kukumber Kolsch", "Ale", "Kolsch", "NoDa Brewing Company", "missing", 1, 3, 71, "It's a good beer.", "missing", "missing"));
        Collections.sort(beersList);
        BeerAdapter adapter = new BeerAdapter(this, R.layout.beer_item, beersList);
        lvMain.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_add:
                Intent intent=  new Intent(MainActivity.this, AddBeerActivity.class);
                startActivityForResult(intent, 1);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}