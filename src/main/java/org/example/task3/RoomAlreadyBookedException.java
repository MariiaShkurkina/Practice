package org.example.task3;

public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
