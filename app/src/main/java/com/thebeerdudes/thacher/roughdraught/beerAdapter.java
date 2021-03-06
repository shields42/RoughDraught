package com.thebeerdudes.thacher.roughdraught;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by thach on 10/24/2017.
 */

public class BeerAdapter extends ArrayAdapter<Beer> {

    public BeerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Beer> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Beer beer = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.beer_item, parent, false);
        }

        TextView lblName = (TextView)convertView.findViewById(R.id.lblBeerName);
        TextView lblBrewery = (TextView)convertView.findViewById(R.id.lblDetailBrewery);
        TextView lblRating = (TextView)convertView.findViewById(R.id.lblDetailRating);
        TextView lblAbv = (TextView)convertView.findViewById(R.id.lblAbvItem);

        lblName.setText(beer.getName());
        lblBrewery.setText(beer.getBrewery());
        lblRating.setText(Long.toString(beer.getRating()));
        String abvText = beer.getAbv() + "%";
        lblAbv.setText(abvText);

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

        return convertView;
    }
}
