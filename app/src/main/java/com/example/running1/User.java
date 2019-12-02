package com.example.running1;

public class User {
    public int step;
    public int age;
    public int weight;
    public int height;
    public int token;
    public int running;
    public String usename;


    public User(String usename, int running) {
        this.usename = usename;
        this.running = running;
    }

    public User() {

    }

    public User(int age, int weight, int height, int step, int token) {

        this.age = age;
        this.weight = weight;
        this.height = height;
        this.token = token;
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
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

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

}
