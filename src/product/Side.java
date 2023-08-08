package product;

public class Side extends Menu{
    public Side() {
        super("기본사이드", "사이드", 1000, 100);
    }

    public Side(String name, int price, int calorie) {
        super(name, "사이드", price, calorie);
    }
}