package commands.user;

import commands.Command;
import file.*;
import obj.*;
import userstuff.Catalog;

import java.util.Scanner;


public class CatalogCommand implements Command {

    public void execute() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Кімнати\n2. Іграшки\n3. Назад");
        System.out.print("Виберіть опцію: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                FileDao.roomsCataloge(Catalog.getRooms()); // Завантажуємо дані в список кімнат
                System.out.println("1. Каталог кімнат\n2. Пошук кімнат по ціні\n3. Пошук кімнат по віковій категорії\n4. Назад");
                System.out.print("Виберіть опцію: ");
                int choice1 = scan.nextInt();

                switch (choice1) {
                    case 1:
                        // Перегляд кімнат
                        if (Catalog.getRooms().isEmpty()) {
                            System.out.println("Список кімнат порожній.");
                        } else {
                            System.out.println("Список кімнат:");
                            for (Room room : Catalog.getRooms()) {
                                System.out.printf("Назва кімнати: \"%s\"; Вікова група: %s; Тип: %s; Ціна: %.2f$\n",
                                        room.getRoomName(), room.getAgeGroup(), room.getRoomType(), room.getPrice());
                            }
                        }
                        break;
                    case 2:
                        // Пошук кімнат по ціні
                        System.out.println("Введіть мінімальну ціну:");
                        double minPrice = scan.nextDouble();
                        System.out.println("Введіть максимальну ціну:");
                        double maxPrice = scan.nextDouble();
                        boolean foundPrice = false;

                        for (Room room : Catalog.getRooms()) {
                            if (room.getPrice() >= minPrice && room.getPrice() <= maxPrice) {
                                System.out.printf("Назва кімнати: \"%s\"; Вікова група: %s; Тип: %s; Ціна: %.2f$\n",
                                        room.getRoomName(), room.getAgeGroup(), room.getRoomType(), room.getPrice());
                                foundPrice = true;
                            }
                        }

                        if (!foundPrice) {
                            System.out.println("Не знайдено кімнат в заданому діапазоні цін.");
                        } else {
                            System.out.println("Пошук завершено.");
                        }
                        break;
                    case 3:
                        // Пошук кімнат по віковій категорії
                        System.out.println("Введіть вікову категорію (наприклад, Малі діти, Школярі):");
                        scan.nextLine(); // Для очищення буфера після введення числа
                        String ageGroup = scan.nextLine();
                        boolean foundAgeGroup = false;

                        for (Room room : Catalog.getRooms()) {
                            if (room.getAgeGroup().equalsIgnoreCase(ageGroup)) {
                                System.out.printf("Назва кімнати: \"%s\"; Вікова група: %s; Тип: %s; Ціна: %.2f$\n",
                                        room.getRoomName(), room.getAgeGroup(), room.getRoomType(), room.getPrice());
                                foundAgeGroup = true;
                            }
                        }

                        if (!foundAgeGroup) {
                            System.out.println("Не знайдено кімнат для заданої вікової категорії.");
                        } else {
                            System.out.println("Пошук завершено.");
                        }
                        break;
                    case 4:
                        return;
                }
                break;
            case 2:
                // Завантажуємо іграшки з файлу
                FileDao.toysCataloge(Catalog.getToys());

                // Запитуємо користувача про вибір
                System.out.println("1. Каталог іграшок\n2. Пошук іграшок по діапазону цін\n3. Пошук по розміру\n4. Назад");
                System.out.print("Виберіть опцію: ");
                int choice2 = scan.nextInt();

                switch (choice2) {
                    case 1:
                        // Перевірка на порожність списку перед виведенням каталогу
                        if (Catalog.getToys().isEmpty()) {
                            System.out.println("Список іграшок порожній.");
                        } else {
                            System.out.println("Список іграшок:");
                            for (Toy toy : Catalog.getToys()) {
                                System.out.printf("Назва іграшки: \"%s\"; Тип: %s; Розмір: %s; Ціна: %.2f$\n",
                                        toy.getToyName(), toy.getType(), toy.getSize(), toy.getPrice());
                            }
                        }
                        break;
                    case 2:
                        // Пошук іграшок за ціною
                        System.out.print("Введіть мінімальну ціну: ");
                        double minPriceToy = scan.nextDouble();
                        System.out.print("Введіть максимальну ціну: ");
                        double maxPriceToy = scan.nextDouble();

                        System.out.println("Іграшки в межах цінового діапазону:");
                        boolean foundToyPrice = false;
                        for (Toy toy : Catalog.getToys()) {
                            if (toy.getPrice() >= minPriceToy && toy.getPrice() <= maxPriceToy) {
                                System.out.printf("Назва іграшки: \"%s\"; Тип: %s; Розмір: %s; Ціна: %.2f$\n",
                                        toy.getToyName(), toy.getType(), toy.getSize(), toy.getPrice());
                                foundToyPrice = true;
                            }
                        }
                        if (!foundToyPrice) {
                            System.out.println("Іграшок в цьому діапазоні цін не знайдено.");
                        }
                        break;
                    case 3:
                        // Пошук за розміром іграшок
                        System.out.print("Введіть розмір іграшки (Малий, Середній, Великий): ");
                        String size = scan.next();

                        System.out.println("Іграшки з розміром \"" + size + "\":");
                        boolean foundToySize = false;
                        for (Toy toy : Catalog.getToys()) {
                            if (toy.getSize().equalsIgnoreCase(size)) {
                                System.out.printf("Назва іграшки: \"%s\"; Тип: %s; Розмір: %s; Ціна: %.2f$\n",
                                        toy.getToyName(), toy.getType(), toy.getSize(), toy.getPrice());
                                foundToySize = true;
                            }
                        }
                        if (!foundToySize) {
                            System.out.println("Іграшок з таким розміром не знайдено.");
                        }
                        break;
                    case 4:
                        return;

                    default:
                        System.out.println("Невірний вибір.");
                        break;
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
                break;
        }
    }
}
