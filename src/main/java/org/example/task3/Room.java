package org.example.task3;

abstract class Room {
    int numberRoom;
    int maxCountPeople;
    Prices priceCategory;
    boolean is_booking=false;

    Room(int numberRoom, int maxCountPeople,Prices priceCategory, boolean booking){
        this.numberRoom=numberRoom;
        this.maxCountPeople=maxCountPeople;
        this.priceCategory=priceCategory;
        this.is_booking=booking;
    }
}
