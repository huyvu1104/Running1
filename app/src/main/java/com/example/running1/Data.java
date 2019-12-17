package com.example.running1;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Data {
    int total;
    int target;
    HashMap<String,Integer> daily;

    public HashMap<String, Integer> getDaily() {
        return daily;
    }

    public void setDaily(HashMap<String, Integer> daily) {
        this.daily = daily;
    }

    public Data(){

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void createDaily() {
       daily = new HashMap<>();
    }

    @NonNull
    @Override
    public String toString() {
        return "Total: " + total + "\n" + "daily: " + daily.values().toString();
    }
}
