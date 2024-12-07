package commands.admin;

import commands.Command;
import file.FileDao;
import java.util.Scanner;

public class DeleteToyCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        String filePath = "toys.txt";

        while (true) {
            System.out.print("Введіть назву іграшки, яку хочете видалити (або 0 для виходу): ");
            String toyNameToDelete = scan.nextLine();

            // Перевірка на повернення назад
            if (toyNameToDelete.equals("0")) {
                return;
            }

            // Викликаємо метод видалення й отримуємо результат
            boolean toyDeleted = FileDao.deleteObj(toyNameToDelete, filePath);

            if (!toyDeleted) {
                System.out.println("Іграшка з такою назвою не знайдена. Спробуйте ще раз.");
            } else {
                System.out.println("Іграшка успішно видалена.");
                return;
            }
        }
    }
}
