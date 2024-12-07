package commands.user;

import commands.Command;

import java.io.*;
import java.util.*;

public class PayCartCommand implements Command {
    @Override
    public void execute() {
        double totalPrice = 0.0;
        List<String> cartLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                double lineTotal = Double.parseDouble(parts[3]);
                totalPrice += lineTotal;
                cartLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу cart.txt");
            return;
        }

        System.out.println("Загальна сума до оплати: " + totalPrice);

        double balance = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String balanceLine = reader.readLine();
            if (balanceLine != null) {
                balance = Double.parseDouble(balanceLine);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу users.txt");
            return;
        }

        System.out.println("Ваш баланс: " + balance);

        if (balance >= totalPrice) {
            System.out.println("1. Оплатити\n2. Вийти без оплати");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                balance -= totalPrice;
                System.out.println("Оплата успішна. Новий баланс: " + balance);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"))) {
                    writer.write("");
                } catch (IOException e) {
                    System.out.println("Помилка при очищенні файлу cart.txt");
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", false))) {
                    writer.write(String.valueOf(balance));
                } catch (IOException e) {
                    System.out.println("Помилка при оновленні файлу users.txt");
                }
            } else {
                System.out.println("Оплата скасована.");
            }
        } else {
            System.out.println("Недостатньо коштів на балансі.");
        }
    }
}
