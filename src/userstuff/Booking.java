package userstuff;

import java.util.ArrayList;

public class Booking {
    private String roomName;       // Назва кімнати
    private double roomPrice;      // Ціна кімнати
    private ArrayList<String> toys; // Список іграшок у форматі "Назва;Ціна"

    public Booking(String roomName, double roomPrice, ArrayList<String> toys) {
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.toys = toys;
    }


    public String getRoomName() {return roomName;}
    public double getRoomPrice() {return roomPrice;}
    public ArrayList<String> getToys() {return toys;}
}
