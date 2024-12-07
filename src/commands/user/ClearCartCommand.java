package commands.user;

import commands.Command;

import java.io.*;

public class ClearCartCommand implements Command {
    @Override
    public void execute() {
        try {
            System.out.println("Корзина очищається...");
            FileWriter writer = new FileWriter("cart.txt", false);
            writer.close();
            System.out.println("Корзина успішно очищена.");
        } catch (IOException e) {
            System.err.println("Помилка при очищенні файлу: ");
        }
    }
}
