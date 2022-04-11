package com.rajat.firebasecrud;

public class MyPetModel {
    String name;
    String purl;
    int age;

    public MyPetModel() {
    }

    public MyPetModel(String name, String purl, int age) {
        this.name = name;
        this.purl = purl;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
