package com.example.android.projecthtf;

public class User {
    private String name;
    private String gender;
    private String phone;
    private int age;
    private int bp;
    private String bloodGroup;
    private String previousIllness;
    private String covidStatus;
    private int bedNo;
    private int spo2;
    boolean visibility;

    private int temperature;



    public User() {
    }

    public User(String name, String gender, String phone, int age, int bp, int bedNo, int spo2, int temperature,String bloodGroup,String previousIllness,String covidStatus) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.age = age;
        this.bp = bp;
        this.bedNo = bedNo;
        this.spo2 = spo2;
        this.visibility=false;
        this.temperature = temperature;
        this.bloodGroup=bloodGroup;
        this.previousIllness=previousIllness;
        this.covidStatus=covidStatus;

    }

    public User(String name, String gender, String phone, int age, int bp, int bedNo, int spo2, boolean visibility, int temperature,String bloodGroup,String previousIllness,String covidStatus) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.age = age;
        this.bp = bp;
        this.bedNo = bedNo;
        this.spo2 = spo2;
        this.visibility = visibility;
        this.temperature = temperature;
        this.bloodGroup=bloodGroup;
        this.previousIllness=previousIllness;
        this.covidStatus=covidStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public int getBp() {
        return bp;
    }

    public int getBedNo() {
        return bedNo;
    }

    public int getSpo2() {
        return spo2;
    }




    public int getTemperature() {
        return temperature;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPreviousIllness() {
        return previousIllness;
    }

    public String getCovidStatus() {
        return covidStatus;
    }

    public boolean isVisibility() {
        return visibility;
    }


    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
