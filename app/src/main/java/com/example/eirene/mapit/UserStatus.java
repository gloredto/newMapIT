package com.example.eirene.mapit;

public class UserStatus {
    public Double userLatitude;
    public Double userLongitude;

    public UserStatus(){

    }

    public UserStatus(Double userLatitude, Double userLongitude){
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }
}
