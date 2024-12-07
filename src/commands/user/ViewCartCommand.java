package commands.user;

import commands.Command;

import java.io.*;

public class ViewCartCommand implements Command {
    @Override
    public void execute() {
        File cartFile = new File("cart.txt");

        if (cartFile.length() == 0) {
            System.out.println("Корзина пуста.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(cartFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");

                String roomName = parts[0];
                double roomPrice = Double.parseDouble(parts[1]);
                String toysList = parts[2];
                double total = Double.parseDouble(parts[3]);

                System.out.println("-Назва кімнати: " + roomName);
                System.out.println("  Ціна кімнати: " + roomPrice);
                System.out.println("-Іграшки:");

                toysList = toysList.substring(1, toysList.length() - 1);  // Видаляємо квадратні дужки
                String[] toyItems = toysList.split(", ");
                for (String toy : toyItems) {
                    String[] toyDetails = toy.split(";");
                    String toyName = toyDetails[0];
                    double toyPrice = Double.parseDouble(toyDetails[1]);

                    System.out.println("  Ім'я іграшки: " + toyName + " Ціна: " + toyPrice);
                }

                System.out.println("  Загальна сума: " + total);
                System.out.println("-----");
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу cart.txt");
        }
    }
}
