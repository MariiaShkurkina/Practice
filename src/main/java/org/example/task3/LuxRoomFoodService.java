package org.example.task3;

public class LuxRoomFoodService<T extends LuxRoom> implements RoomService<T>,LuxRoomService<T> {

    @Override
    public void clean(T room) {
        System.out.println("Комната "+room.numberRoom +" убрана");
    }

    @Override
    public void reserve(T room) {
        if (room.is_booking) {
            throw new RoomAlreadyBookedException("Комната "+room.numberRoom +" уже забронирована!");
        }
        room.is_booking=true;
        System.out.println("Комната "+room.numberRoom +" забронирована");
    }

    @Override
    public void free(T room) {
        room.is_booking=false;
        System.out.println("Комната "+room.numberRoom +" освобождена");
    }
    @Override
    public void foodDelivery(T room) {
        System.out.println("Доставка еды в комнату " + room.numberRoom);
    }
}
