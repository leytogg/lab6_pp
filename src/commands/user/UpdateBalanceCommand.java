package commands.user;

import commands.Command;

import java.io.*;
import java.util.Scanner;

public class UpdateBalanceCommand implements Command {

    @Override
    public void execute() {

        Scanner scan = new Scanner(System.in);
        double currentBalance = 0.0;

        // Зчитуємо поточний баланс з файлу
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                currentBalance = Double.parseDouble(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні файлу users.txt");
            return;
        }

        // Виводимо поточний баланс
        System.out.println("Ваш баланс: " + currentBalance);

        // Меню для вибору дії
        System.out.println("1. Поповнити\n0. Назад\nВиберіть опцію: ");
        int choice = scan.nextInt();

        // Використовуємо switch для вибору дії
        switch (choice) {
            case 1:  // Поповнити баланс
                System.out.print("Введіть суму на яку хочете поповнити: ");
                double amount = scan.nextDouble();

                // Додаємо введену суму до поточного балансу
                double newBalance = currentBalance + amount;

                // Перезаписуємо файл з новим балансом
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                    writer.write(String.valueOf(newBalance));
                    System.out.println("Баланс успішно оновлено. Новий баланс: " + newBalance);
                } catch (IOException e) {
                    System.out.println("Помилка при оновленні файлу users.txt");
                }
                break;

            case 0:  // Назад
                System.out.println("Вихід з меню поповнення балансу...");
                break;

            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
                break;
        }
    }
}
