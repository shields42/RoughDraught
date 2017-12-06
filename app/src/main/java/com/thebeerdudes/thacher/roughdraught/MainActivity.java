package com.thebeerdudes.thacher.roughdraught;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<Beer> beersList;
    protected ListView lvMain;
    protected MenuItem btnSort;
    private final int ADD_BEER = 1;
    private final int EDIT_BEER = 2;
    private final int SORT = 3;
    private final int VIEW_BEER = 4;

    protected int beerIndex = -1;

    private int removeIndex = 0;

    private TinyDB tinyDB = null;

    BeerDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new BeerDBHandler(getApplicationContext());

        tinyDB = new TinyDB(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Main List
        lvMain = (ListView) findViewById(R.id.lvBeers);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beersList = new ArrayList<>();

        beersList = getBeersList();


        Collections.sort(beersList);
        BeerAdapter adapter = new BeerAdapter(this, R.layout.beer_item, beersList);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(MainActivity.this, EditBeerActivity.class);
                detailIntent.putExtra("beer", beersList.get(i));
                beerIndex = i;
                detailIntent.putExtra("beer index", i);
                startActivityForResult(detailIntent, VIEW_BEER);
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeIndex = i;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Would you like to remove " + beersList.get(i).getName() + " from the list?");
                alertDialog.setTitle("Remove Entry");
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        beersList.remove(removeIndex);
                        updateDB(beersList);
                    }
                });
                alertDialog.show();
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("Request code: " + requestCode + "\nResult Code: " + resultCode);
        switch(requestCode) {
            case (ADD_BEER):
                if (resultCode == RESULT_OK) {
                    Beer beer = (Beer)data.getSerializableExtra("Beer");

                    System.out.println("Adding " + beer.getName() + " to database");

                    beersList.add(beer);
                    updateDB(beersList);

                    //Push beer to table
                    //Pull table into ArrayList<Beer> newList

                    Collections.sort(beersList);
                    BeerAdapter adapter = new BeerAdapter(MainActivity.this, R.layout.beer_item, beersList);
                    lvMain.setAdapter(adapter);
                }
                break;

            case (SORT):
                //Sort by Rating
                if(resultCode == 1){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if(currentBeer.getRating()<newBeer.getRating()){
                                return -1;
                            }
                            else if(currentBeer.getRating()==newBeer.getRating()){
                                return (currentBeer.getName().compareTo(newBeer.getName()));
                            }
                            else{
                                return 11;
                            }
                        }
                    });

                }

                else if(resultCode == 11){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if(currentBeer.getRating()<newBeer.getRating()){
                                return -1;
                            }
                            else if(currentBeer.getRating()==newBeer.getRating()){
                                return (currentBeer.getName().compareTo(newBeer.getName()));
                            }
                            else{
                                return 1;
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
                else if(resultCode==12){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            System.out.println(beersList);
                            return currentBeer.getName().compareTo(newBeer.getName());
                        }
                    });
                    Collections.reverse(beersList);
                }
                else if(resultCode==3){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if (currentBeer.getBrewery().compareTo(newBeer.getBrewery())==1) {
                                return 1;
                            } else if (currentBeer.getBrewery().equals(newBeer.getBrewery())) {
                                return (currentBeer.getName().compareTo(newBeer.getName()));
                            } else {
                                return -1;
                            }
                        }
                    });
                }
                else if(resultCode==13){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if (currentBeer.getBrewery().compareTo(newBeer.getBrewery())==1) {
                                return -1;
                            } else if (currentBeer.getBrewery().equals(newBeer.getBrewery())) {
                                return (currentBeer.getName().compareTo(newBeer.getName()));
                            } else {
                                return 1;
                            }
                        }
                    });
                }
                else if(resultCode==4){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if (currentBeer.getAbv() < newBeer.getAbv()) {
                                return 1;
                            } else if (currentBeer.getAbv() == newBeer.getAbv()) {
                                return (currentBeer.compareTo(newBeer));
                            } else {
                                return -1;
                            }
                        }
                    });
                }
                else if(resultCode==14){
                    Collections.sort(beersList,new Comparator<Beer>(){
                        @Override
                        public int compare(Beer currentBeer, Beer newBeer) {
                            if (currentBeer.getAbv() < newBeer.getAbv()) {
                                return -1;
                            } else if (currentBeer.getAbv() == newBeer.getAbv()) {
                                return (currentBeer.compareTo(newBeer));
                            } else {
                                return 1;
                            }
                        }
                    });
                }
            case (VIEW_BEER):
                if(resultCode==10){ //Beer not edited. Only viewed.
                    break;
                }
                else if(resultCode==11) {//Beer edited from activity
                    if(beerIndex!=-1) {
                        beersList.remove(beerIndex);
                        beersList.add((Beer) data.getSerializableExtra("new beer"));
                        updateDB(beersList);
                        beerIndex=-1;
                    }

                }

            break;
        }
        //Reset List Adapter
        //BeerAdapter adapter = new BeerAdapter(MainActivity.this, R.layout.beer_item, beersList);
        //lvMain.setAdapter(adapter);
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

    public void updateDB(ArrayList<Beer> list){
        tinyDB.remove("Beers");
        System.out.println("Updating preferences..");
        tinyDB.putListObject("Beers", list);
        System.out.println("Preferences updated!\nReloading List...");
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
        BeerAdapter adapter = new BeerAdapter(MainActivity.this, R.layout.beer_item, beersList);
        lvMain.setAdapter(adapter);
        System.out.println("Adapter updated!");
    }

    public ArrayList<Beer> getBeersList(){
        System.out.println("Getting data...");
        System.out.println("Data found!");
        return tinyDB.getListObject("Beers", Beer.class);
    }


}