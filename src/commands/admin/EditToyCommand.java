package commands.admin;

import commands.Command;

import java.io.*;
import java.util.Scanner;

public class EditToyCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Введіть назву іграшки, яку хочете редагувати (або 0 для виходу): ");
            String toyNameToFind = scan.nextLine();

            // Перевірка на повернення назад
            if (toyNameToFind.equals("0")) {
                return;
            }

            File file = new File("toys.txt");
            StringBuilder fileContent = new StringBuilder();
            boolean toyFound = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append(System.lineSeparator());
                }
            } catch (IOException e) {
                System.out.println("Помилка при читанні файлу.");
                return;
            }

            if (fileContent.indexOf(toyNameToFind) != -1) {
                toyFound = true;
                System.out.println("Знайдена іграшка: " + toyNameToFind);
                System.out.println("Що ви хочете редагувати?");
                System.out.println("1. Назву іграшки\n2. Тип іграшки\n3. Розмір\n4. Ціну\n0. Назад");

                int choice = scan.nextInt();
                scan.nextLine(); // Очищаємо буфер

                String[] lines = fileContent.toString().split(System.lineSeparator());

                for (int i = 0; i < lines.length; i++) {
                    String[] toyDetails = lines[i].split(":");
                    if (toyDetails[0].equalsIgnoreCase(toyNameToFind)) {
                        switch (choice) {
                            case 1:
                                System.out.print("Введіть нову назву іграшки: ");
                                toyDetails[0] = scan.nextLine();
                                break;
                            case 2:
                                System.out.print("Введіть новий тип іграшки (освітня, розважальна): ");
                                toyDetails[1] = scan.nextLine();
                                break;
                            case 3:
                                System.out.print("Введіть новий розмір (M, C, В): ");
                                toyDetails[2] = scan.nextLine();
                                break;
                            case 4:
                                System.out.print("Введіть нову ціну: ");
                                toyDetails[3] = scan.nextLine();
                                break;
                            case 0:
                                return;
                            default:
                                System.out.println("Невірний вибір.");
                                return;
                        }
                        lines[i] = String.join(":", toyDetails);
                        break;
                    }
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                    System.out.println("Іграшка успішно відредагована.");
                } catch (IOException e) {
                    System.out.println("Помилка при записі в файл.");
                }
            } else {
                System.out.println("Іграшка з такою назвою не знайдена, попробуйте ще раз!");
            }
        }
    }
}
