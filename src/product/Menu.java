package product;

import java.util.LinkedList;

public abstract class Menu {
    private String name;
    private String category;
    private int price;
    private int calorie;
    private int index=0; // 메뉴판에 보여지는 index, 변경될 수 있음.
    static private int id = 1;



    static LinkedList<Menu> allMenu = new LinkedList<>();

    public Menu() {
        this("기본 햄버거", "햄버거", 1000, 500);
    }

    public Menu(String name, String category, int price, int calorie) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.calorie = calorie;
        id++;
    }
    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}