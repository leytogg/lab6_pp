import commands.Command;
import commands.admin.*;
import commands.user.*;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Interface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<Integer, Command> adminCommands = new LinkedHashMap<>();
        LinkedHashMap<Integer, Command> userCommands = new LinkedHashMap<>();

        // Додаємо команди адміністратора
        adminCommands.put(1, new CatalogCommand());
        adminCommands.put(2, new AddRoomCommand());
        adminCommands.put(3, new AddToyCommand());
        adminCommands.put(4, new DeleteRoomCommand());
        adminCommands.put(5, new DeleteToyCommand());
        adminCommands.put(6, new EditRoomCommand());
        adminCommands.put(7, new EditToyCommand());
        adminCommands.put(8, () -> System.out.println("Логаут..."));
        adminCommands.put(0, () -> System.exit(0));

        // Додаємо команди користувача
        userCommands.put(1, new CatalogCommand());
        userCommands.put(2, new UpdateBalanceCommand());
        userCommands.put(3, new ViewCartCommand());
        userCommands.put(4, new CreateBookingCommand());
        userCommands.put(5, new PayCartCommand());
        userCommands.put(6, new ClearCartCommand());
        userCommands.put(7, () -> System.out.println("Логаут..."));
        userCommands.put(0, () -> System.exit(0));

        while (true) {
            // Вибір ролі
            System.out.println("1. Користувач");
            System.out.println("2. Адміністратор");
            System.out.println("0. Вийти з програми");
            System.out.print("Виберіть опцію: ");
            int roleChoice = scanner.nextInt();

            if (roleChoice == 0) {
                System.out.println("Вихід з програми....");
                break;
            }

            // Розподіл меню за роллю
            if (roleChoice == 1) {
                System.out.println("Меню користувача:");
                userMenu(userCommands, scanner);
            } else if (roleChoice == 2) {
                System.out.println("Меню адміністратора:");
                adminMenu(adminCommands, scanner);
            } else {
                System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    // Меню адміністратора
    private static void adminMenu(LinkedHashMap<Integer, Command> commands, Scanner scanner) {
        while (true) {
            System.out.println("1. Каталог");
            System.out.println("2. Додати кімнату");
            System.out.println("3. Додати іграшку");
            System.out.println("4. Видалити кімнату");
            System.out.println("5. Видалити іграшку");
            System.out.println("6. Редагувати кімнату");
            System.out.println("7. Редагувати іграшку");
            System.out.println("8. Log out");
            System.out.println("0. Закрити програму");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            Command command = commands.get(choice);

            if (command != null) {
                command.execute();
                if (choice == 8) break; // Логаут адміністратора
            } else {
                System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    // Меню користувача
    private static void userMenu(LinkedHashMap<Integer, Command> commands, Scanner scanner) {
        while (true) {
            System.out.println("1. Каталог");
            System.out.println("2. Поповнення");
            System.out.println("3. Моя корзина");
            System.out.println("4. Створити кімнату");
            System.out.println("5. Оплатити корзину");
            System.out.println("6. Очистити корзину");
            System.out.println("7. Log out");
            System.out.println("0. Закрити програму");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            Command command = commands.get(choice);

            if (command != null) {
                command.execute();
                if (choice == 7) break; // Логаут користувача
            } else {
                System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }
}
