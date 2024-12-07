package obj;

public class Room {
    // Поля
    private String roomName; // назва кімнати
    private String ageGroup; // вікова група
    private String roomType; // тип кімнати
    private double price; // базова ціна

    // Конструктор для ініціалізації полів
    public Room(String roomName, String ageGroup, String roomType, double price) {
        this.roomName = roomName;
        this.ageGroup = ageGroup;
        this.roomType = roomType;
        this.price = price;
    }

    @Override
    public String toString() {
        return roomName + ":" + ageGroup + ":" + price + ":" + roomType;
    }


    public String getRoomName() {return roomName;}
    public String getAgeGroup() {return ageGroup;}
    public double getPrice() {return price;}
    public String getRoomType() {return roomType;}
}
