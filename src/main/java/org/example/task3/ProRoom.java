package org.example.task3;

abstract class ProRoom extends Room {

    ProRoom(int numberRoom, int maxCountPeople,Prices priceCategory, boolean booking) {
        super(numberRoom, maxCountPeople, priceCategory, booking);
    }
}
