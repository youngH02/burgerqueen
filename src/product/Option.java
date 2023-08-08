package product;

public class Option extends Menu{
    public Option() {
        super("기본옵션", "옵션", 1000, 100);
    }

    public Option(String name, int price, int calorie) {
        super(name, "옵션", price, calorie);
    }
}