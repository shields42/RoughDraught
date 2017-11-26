package com.thebeerdudes.thacher.roughdraught;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<Beer> beersList;
    protected ListView lvMain;
    protected MenuItem btnSort;
    private final int ADD_BEER = 1;
    private final int EDIT_BEER = 2;
    private final int SORT = 3;

    BeerDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new BeerDBHandler(getApplicationContext());


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Main List
        lvMain = (ListView) findViewById(R.id.lvBeers);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beersList = new ArrayList<>();
        beersList.add(new Beer("Celebration Ale", "Sierra Nevada", "IPA", 7.7, 0, 82, "I like it"));
        beersList.add(new Beer("Torpedo Extra", "Sierra Nevada", "Double IPA", 7.7, 37, 85, "I like it"));
        beersList.add(new Beer("Cavu", "NoDa Brewing Company", "Blonde Ale", 4.5, 12, 97, "I like it"));
        beersList.add(new Beer("Hop Drop n Roll", "NoDa Brewing Company", "IPA", 6.8, 12, 96, "I like it"));
        beersList.add(new Beer("Cavu", "NoDa Brewing Company", "Blonde Ale", 4.5, 12, 85, "I like it"));
        beersList.add(new Beer("Cavu", "NoDa Brewing Company", "Blonde Ale", 4.5, 12, 85, "I like it"));

        //dbHandler.addBeer(beersList.get(1));


        Collections.sort(beersList);
        BeerAdapter adapter = new BeerAdapter(this, R.layout.beer_item, beersList);
        lvMain.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("Request code: " + requestCode + "\nResult Code: " + resultCode);
        switch(requestCode) {
            case (ADD_BEER):
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    Beer beer = (Beer)data.getSerializableExtra("Beer");
                    ArrayList<Beer> newList = new ArrayList<>();
                }
                break;

            case (SORT):
                //Sort by Rating
                if(resultCode == 1){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if(currentBeer.getRating()<newBeer.getRating()){
                                return 1;
                            }
                            else if(currentBeer.getRating()==newBeer.getRating()){
                                return (currentBeer.getName().compareTo(newBeer.getName()));
                            }
                            else{
                                return -1;
                            }
                        }
                    });

                }
                //Sort by Name
                else if(resultCode==2){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            System.out.println(beersList);
                            return currentBeer.getName().compareTo(newBeer.getName());
                        }
                    });

                }
                //Reset List Adapter
                BeerAdapter adapter = new BeerAdapter(MainActivity.this, R.layout.beer_item, beersList);
                lvMain.setAdapter(adapter);
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {

            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_add:
                intent =  new Intent(MainActivity.this, AddBeerActivity.class);
                startActivityForResult(intent, ADD_BEER);
                return true;

            case R.id.action_sort:
                intent=  new Intent(MainActivity.this, SortActivity.class);
                startActivityForResult(intent, SORT);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}