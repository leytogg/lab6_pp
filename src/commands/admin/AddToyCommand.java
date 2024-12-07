package commands.admin;

import commands.Command;
import obj.Toy;
import file.FileDao;
import java.util.Scanner;

public class AddToyCommand implements Command {

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        // Запитуємо дані в адміністратора
        System.out.print("Введіть назву іграшки(або 0 для виходу): ");
        String toyName = scan.nextLine();

        if (toyName.equals("0")) {
            return;
        }

        System.out.print("Введіть тип (розважальна, освітня): ");
        String toyType = scan.nextLine();

        System.out.print("Введіть розмір (M - мала, С - середня, В - велика): ");
        String toySize = scan.nextLine();

        System.out.print("Введіть ціну іграшки: ");
        double toyPrice = scan.nextDouble();
        scan.nextLine(); // очищення буфера

        Toy toy = new Toy(toyName, toyType, toySize, toyPrice);
        FileDao.saveToy(toy);
    }
}
