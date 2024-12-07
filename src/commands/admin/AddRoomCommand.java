package commands.admin;

import commands.Command;
import obj.Room;
import file.FileDao;
import java.util.Scanner;

public class AddRoomCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        // Запитуємо дані в адміністратора
        System.out.print("Введіть назву кімнати (або 0 для виходу): ");
        String roomName = scan.nextLine();

        if (roomName.equals("0")) {
            return;
        }

        System.out.print("Вікова група (наприклад, Малі діти, Дошкільнята, Школярі): ");
        String ageGroup = scan.nextLine();

        System.out.print("Тип кімнати (наприклад, Велика кімната, Кімната-лабіринт): ");
        String roomType = scan.nextLine();

        System.out.print("Введіть базову ціну: ");
        double price = scan.nextDouble();
        scan.nextLine();  // очищення буфера після числа

        Room room = new Room(roomName, ageGroup, roomType, price);

        // Записуємо дані в файл
        FileDao.saveRoom(room);
    }
}
