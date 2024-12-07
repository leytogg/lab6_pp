package commands.user;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import commands.Command;
import userstuff.*;

public class CreateBookingCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        String roomName = "";
        double roomPrice = 0;
        boolean roomFound = false;

        while (!roomFound) {
            System.out.print("Введіть назву кімнати, яку хочете додати (або 0 для виходу): ");
            roomName = scan.nextLine();

            if (roomName.equals("0")) {
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader("rooms.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] roomDetails = line.split(":");
                    if (roomDetails[0].equalsIgnoreCase(roomName)) {
                        roomPrice = Double.parseDouble(roomDetails[3]);
                        roomFound = true;
                        System.out.println("Кімната знайдена: " + roomName + ", ціна: " + roomPrice);
                        break;
                    }
                }
                if (!roomFound) {
                    System.out.println("Кімната з такою назвою не знайдена. Спробуйте ще раз.");
                }
            } catch (IOException e) {
                System.out.println("Помилка при читанні файлу rooms.txt");
            }
        }

        ArrayList<String> toys = new ArrayList<>();
        double totalPrice = roomPrice;

        while (true) {
            System.out.print("Введіть назву іграшки (для завершення введіть 0): ");
            String toyName = scan.nextLine();

            if (toyName.equals("0")) {
                break;
            }

            boolean toyFound = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("toys.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] toyDetails = line.split(":");
                    if (toyDetails[0].equalsIgnoreCase(toyName)) {
                        String toyEntry = toyDetails[0] + ";" + toyDetails[3];
                        toys.add(toyEntry);
                        totalPrice += Double.parseDouble(toyDetails[3]);
                        toyFound = true;
                        System.out.println("Іграшка додана: Ім'я: " + toyDetails[0] + ", Ціна: " + toyDetails[3]);
                        break;
                    }
                }

                if (!toyFound) {
                    System.out.println("Іграшка з такою назвою не знайдена. Спробуйте ще раз.");
                }
            } catch (IOException e) {
                System.out.println("Помилка при читанні файлу toys.txt");
            }
        }

        System.out.println("\nВи вибрали:");
        System.out.println("Кімната: " + roomName + ", ціна: " + roomPrice);
        System.out.println("Іграшки:");
        for (String toy : toys) {
            String[] toyParts = toy.split(";");
            System.out.println("- " + toyParts[0] + ", ціна: " + toyParts[1]);
        }
        System.out.println("Загальна ціна: " + totalPrice);

        System.out.print("\nДодати до корзини? (так/ні): ");
        String addToCart = scan.nextLine();

        if (addToCart.equalsIgnoreCase("так")) {
            Booking booking = new Booking(roomName, roomPrice, toys);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt", true))) {
                writer.write(booking.getRoomName() + ":" + booking.getRoomPrice() + ":[");

                for (int i = 0; i < booking.getToys().size(); i++) {
                    writer.write(booking.getToys().get(i));
                    if (i < booking.getToys().size() - 1) {
                        writer.write(", ");
                    }
                }

                writer.write("]:" + totalPrice);
                writer.newLine();
                System.out.println("Ваша комбінація успішно додана до корзини!");
            } catch (IOException e) {
                System.out.println("Помилка при записі в файл cart.txt");
            }
        } else {
            System.out.println("Замовлення не було додано до корзини.");
        }
    }
}
