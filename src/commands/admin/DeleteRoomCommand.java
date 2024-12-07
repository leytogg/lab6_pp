package commands.admin;

import commands.Command;
import file.FileDao;
import java.util.Scanner;

public class DeleteRoomCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        String filePath = "rooms.txt";

        while (true) { // Цикл триватиме, поки кімната не буде видалена
            System.out.print("Введіть назву кімнати, яку хочете видалити (або 0 для виходу): ");
            String roomNameToDelete = scan.nextLine();

            if (roomNameToDelete.equals("0")) {
                return;
            }

            // Викликаємо метод видалення й отримуємо результат
            boolean roomDeleted = FileDao.deleteObj(roomNameToDelete, filePath);

            if (!roomDeleted) {
                System.out.println("Кімната з такою назвою не знайдена. Спробуйте ще раз.");
            }else {
                System.out.println("Кімната успішно видалена.");
                return;
            }
        }
    }
}
