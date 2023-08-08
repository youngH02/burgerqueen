package product;

public class Burger extends Menu{

    public Burger() {
        super("기본햄버거", "햄버거", 1000, 100);
    }

    public Burger(String name, int price, int calorie) {
        super(name, "햄버거", price, calorie);
    }
}