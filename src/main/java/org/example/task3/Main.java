package org.example.task3;

public class Main {
    public static void main(String[] args){
        RoomService<EconomyRoom> service1 = new GenericRoomService<>();
        EconomyRoom econRoom = new EconomyRoom(1,2,Prices.ECONOMY,true);
        service1.clean(econRoom);
        service1.reserve(econRoom);
        service1.free(econRoom);

        RoomService<StandardRoom> service2 = new GenericRoomService<>();
        StandardRoom stdRoom = new StandardRoom(2,3,Prices.STANDARD,false);
        service2.clean(stdRoom);
        service2.reserve(stdRoom);
        service2.free(stdRoom);

        RoomService<LuxRoom> service3 = new GenericRoomService<>();
        LuxRoom luxRoom = new LuxRoom(3,3,Prices.LUX,true);
        service3.clean(luxRoom);
        service3.reserve(luxRoom);
        service3.free(luxRoom);

        RoomService<UltraLuxRoom> service4 = new GenericRoomService<>();
        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom(4,2,Prices.ULTRA_LUX,false);
        service4.clean(ultraLuxRoom);
        service4.reserve(ultraLuxRoom);
        service4.free(ultraLuxRoom);

        // Можно: люксовый сервис для люксовой комнаты
        LuxRoomFoodService<LuxRoom> service = new LuxRoomFoodService<>();
        service.clean(luxRoom);
        service.reserve(luxRoom);
        service.foodDelivery(luxRoom);


        // Нельзя создать сервис для EconomyRoom, не скомпилируется
        //LuxRoomFoodService<EconomyRoom> invalidService = new LuxRoomFoodService<>();

        // Нельзя: доставка еды в economyRoom, не скомпилируется
        //service.foodDelivery(econRoom);


    }
}
