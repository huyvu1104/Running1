package com.example.running1;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class User {

    public int age;
    public int weight;
    public int height;
    int total;
    String Id;
    HashMap<String,Integer> daily;

    public User() {

    }

    public HashMap<String, Integer> getDaily() {
        return daily;
    }

    public void setDaily(HashMap<String, Integer> daily) {
        this.daily = daily;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    public void createDaily() {
        daily = new HashMap<>();
    }

    @NonNull
    @Override
    public String toString() {
        return "Total: " + total + "\n" + "daily: " + daily.values().toString();
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
