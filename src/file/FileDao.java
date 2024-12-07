package file;

import obj.*;

import java.io.*;
import java.util.ArrayList;


public class FileDao {

    public static void saveRoom(Room room) {
        // Записуємо дані в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rooms.txt", true))) {
            writer.write(room.getRoomName() + ":" + room.getAgeGroup() + ":" + room.getRoomType() + ":" + room.getPrice());
            writer.newLine(); // додаємо новий рядок
            System.out.println("Кімната успішно додана!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл.");
        }
    }

    public static void saveToy(Toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("toys.txt", true))) {
            writer.write(toy.getToyName() + ":" + toy.getType() + ":" + toy.getSize() + ":" + toy.getPrice());
            writer.newLine(); // додаємо новий рядок
            System.out.println("Іграшка успішно додана!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл.");
        }
    }

    public static boolean deleteObj(String roomNameToDelete, String filePath) {
        boolean roomFound = false;
        // Читаємо поточний список кімнат з файлу
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> rooms = new ArrayList<>();
            String line;

            // Читаємо кожну кімнату з файлу і додаємо до списку, якщо це не та кімната, яку треба видалити
            while ((line = reader.readLine()) != null) {
                String[] roomDetails = line.split(":");
                String roomName = roomDetails[0];

                if (roomName.equalsIgnoreCase(roomNameToDelete)) {
                    roomFound = true;  // Знайшли кімнату, яку треба видалити
                } else {
                    rooms.add(line);  // Додаємо інші кімнати в список
                }
            }

            // Якщо кімнату знайшли, перезаписуємо файл без неї
            if (roomFound) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    for (String room : rooms) {
                        writer.write(room);
                        writer.newLine();
                    }
                }
                System.out.println("Об'єкт '" + roomNameToDelete + "' успішно видалений.");
            }
        } catch (IOException e) {
            System.out.println("Помилка при обробці файлу.");
        }
        return roomFound;
    }

    public static void roomsCataloge(ArrayList<Room> rooms) {
        rooms.clear(); // Очищаємо список перед завантаженням нових даних
        try (BufferedReader reader = new BufferedReader(new FileReader("rooms.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(":"); // Розбиваємо рядок на частини
                if (details.length == 4) { // Перевіряємо, чи всі частини присутні
                    String roomName = details[0];
                    String ageGroup = details[1];
                    String roomType = details[2];
                    double price = Double.parseDouble(details[3]);

                    // Створюємо об'єкт Room і додаємо в список
                    Room room = new Room(roomName, ageGroup, roomType, price);
                    rooms.add(room);
                } else {
                    System.out.println("Некоректний формат даних: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу з кімнатами.");
        } catch (NumberFormatException e) {
            System.out.println("Помилка при парсингу ціни. Перевірте формат файлу.");
        }
    }

    public static void toysCataloge(ArrayList<Toy> toys) {
        toys.clear(); // Очищаємо список перед завантаженням нових даних
        try (BufferedReader reader = new BufferedReader(new FileReader("toys.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(":"); // Розбиваємо рядок на частини
                if (details.length == 4) { // Перевіряємо, чи всі частини присутні
                    String toyName = details[0];
                    String type = details[1];
                    String size = details[2];
                    double price = Double.parseDouble(details[3]);

                    // Створюємо об'єкт Toy і додаємо в список
                    Toy toy = new Toy(toyName, type, size, price);
                    toys.add(toy);
                } else {
                    System.out.println("Некоректний формат даних: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу з іграшками.");
        } catch (NumberFormatException e) {
            System.out.println("Помилка при парсингу ціни. Перевірте формат файлу.");
        }
    }
}
