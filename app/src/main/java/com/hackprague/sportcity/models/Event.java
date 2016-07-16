package com.hackprague.sportcity.models;

/**
 * Created by jakubveverka on 16.07.16.
 */
public class Event {
    String sport;
    String time;
    String place;

    public Event() {}

    public Event(String sport, String time, String place) {
        this.sport = sport;
        this.time = time;
        this.place = place;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSport() {
        return sport;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }
}
