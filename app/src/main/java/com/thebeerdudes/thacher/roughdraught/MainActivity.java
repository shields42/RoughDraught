package com.thebeerdudes.thacher.roughdraught;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout sideDrawer;
    private ActionBarDrawerToggle sideToggle;
    protected ArrayList<Beer> beersList;
    protected ListView lvMain;
    protected MenuItem btnSort;
    protected ArrayList<String> navItems;
    protected ListView navList;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menu Items
        navItems = new ArrayList<>();
        navItems.add("Sort");
        navItems.add("Load sample data");
        navItems.add("Clear");

        //Side Drawer
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        navList = (ListView)findViewById(R.id.left_drawer);



        //Main List
        lvMain = (ListView) findViewById(R.id.lvBeers);



        btnSort = (MenuItem)findViewById(R.id.btnSort);

        //Enable Side Drawer
        sideDrawer = (DrawerLayout)findViewById(R.id.drawer);
        sideToggle = new ActionBarDrawerToggle(this, sideDrawer, R.string.open, R.string.close);
        sideDrawer.addDrawerListener(sideToggle);
        sideToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onOptionsItemSelected(MenuItem item){
        if(sideToggle.onOptionsItemSelected(item)){

            if(item.getItemId()==1){
                Collections.sort(beersList);
                BeerAdapter adapter = new BeerAdapter(this, R.layout.beer_item, beersList);
                lvMain.setAdapter(adapter);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}