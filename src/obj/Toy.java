package obj;

public class Toy {
    // Поля
    private String toyName; // назва іграшки
    private String type; // тип іграшки (освітня, розважальна тощо)
    private String size; //розмір
    private double price; // ціна іграшки


    // Конструктор для ініціалізації полів
    public Toy(String toyName, String type, String size, double price) {
        this.toyName = toyName;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return toyName + ":" + type + ":" + size + ":" + price;
    }

    public String getToyName() {return toyName;}
    public String getType() {return type;}
    public String getSize() {return size;}
    public double getPrice() {return price;}

}
