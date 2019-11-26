package com.example.running1;

public class User {
   public String email;
   public int age;
   public int weight;
   public int height;
   public int token;
    public User() {

    }

    public User(String email, int age, int weight, int height, int token) {
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.token = token;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
