package org.example.task3;

public interface RoomService<T extends Room> {
    void clean(T room);      // Метод для очистки комнаты
    void reserve(T room);    // Метод для резервирования комнаты
    void free(T room);       // Метод для освобождения комнаты
}
