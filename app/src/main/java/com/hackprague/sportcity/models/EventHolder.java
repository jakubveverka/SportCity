package com.hackprague.sportcity.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jakubveverka on 16.07.16.
 */
public class EventHolder extends RecyclerView.ViewHolder {
    View mView;

    public EventHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setSport(String sport) {
        TextView field = (TextView) mView.findViewById(android.R.id.text1);
        field.setText(sport);
    }

    public void setPlaceAndTime(String placeAndTime) {
        TextView field = (TextView) mView.findViewById(android.R.id.text2);
        field.setText(placeAndTime);
    }
}
