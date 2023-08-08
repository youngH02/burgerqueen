package product;

public class Drink extends Menu{
    public Drink() {
        super("기본음료수", "음료", 1000, 100);
    }

    public Drink(String name, int price, int calorie) {
        super(name, "음료", price, calorie);
    }
}