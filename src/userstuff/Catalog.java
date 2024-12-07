package userstuff;

import obj.*;
import java.util.ArrayList;

public class Catalog {

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Toy> toys = new ArrayList<>();

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static ArrayList<Toy> getToys() {
        return toys;
    }
}
