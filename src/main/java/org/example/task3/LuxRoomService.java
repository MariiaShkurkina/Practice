package org.example.task3;

public interface LuxRoomService<T extends LuxRoom> {
    void foodDelivery(T room);
}
