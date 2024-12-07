package commands.admin;

import commands.Command;

import java.io.*;
import java.util.Scanner;

public class EditRoomCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Введіть ім'я кімнати, яку хочете редагувати (або 0 для виходу): ");
            String roomNameToFind = scan.nextLine();

            // Перевірка на повернення назад
            if (roomNameToFind.equals("0")) {
                return;
            }

            // Читаємо весь файл в список
            File file = new File("rooms.txt");
            StringBuilder fileContent = new StringBuilder();
            boolean roomFound = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append(System.lineSeparator());
                }
            } catch (IOException e) {
                System.out.println("Помилка при читанні файлу.");
                return;
            }

            if (fileContent.indexOf(roomNameToFind) != -1) {
                roomFound = true;
                System.out.println("Знайдена кімната: " + roomNameToFind);
                System.out.println("Що ви хочете редагувати?");
                System.out.println("1. Ім'я кімнати\n2. Вікова група\n3. Тип кімнати\n4. Ціна\n0. Назад");
                System.out.print("Виберіть опцію: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Очищаємо буфер

                String[] lines = fileContent.toString().split(System.lineSeparator());

                for (int i = 0; i < lines.length; i++) {
                    String[] roomDetails = lines[i].split(":");
                    if (roomDetails[0].equalsIgnoreCase(roomNameToFind)) {
                        switch (choice) {
                            case 1:
                                System.out.print("Введіть нове ім'я кімнати: ");
                                roomDetails[0] = scan.nextLine();
                                break;
                            case 2:
                                System.out.print("Введіть нову вікову групу: ");
                                roomDetails[1] = scan.nextLine();
                                break;
                            case 3:
                                System.out.print("Введіть новий тип кімнати: ");
                                roomDetails[2] = scan.nextLine();
                                break;
                            case 4:
                                System.out.print("Введіть нову ціну: ");
                                roomDetails[3] = scan.nextLine();
                                break;
                            case 0:
                                return;
                            default:
                                System.out.println("Невірний вибір.");
                                return;
                        }
                        lines[i] = String.join(":", roomDetails);
                        break;
                    }
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                    System.out.println("Кімната успішно відредагована.");
                } catch (IOException e) {
                    System.out.println("Помилка при записі в файл.");
                }
            } else {
                System.out.println("Кімната з таким ім'ям не знайдена, попробуйте ще раз!");
            }
        }
    }
}
