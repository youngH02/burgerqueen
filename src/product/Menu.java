package product;

import discount.DiscountPolicy;
import discount.DiscountService;

public abstract class Menu {
    private int id;
    private String name;
    private String category;
    private int price;
    private int calorie;
    private boolean isDiscount;
    private DiscountPolicy discountPolicy;
    private int index; // 메뉴판에 보여지는 index, 변경될 수 있음.

    public Menu(int id, String name, String category, int price, int calorie) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.calorie = calorie;
        this.isDiscount = false;
        this.index = 0;
    }

    public int getId(){ return id;}

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getPrice() {
        return price;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDiscount(DiscountPolicy discountPolicy) {
        this.isDiscount=true;
        this.discountPolicy = discountPolicy;
    }
    public int getDiscountPrice() { //할인금액
        if(!isDiscount) return 0;
        int discountMoney = discountPolicy.getDiscountRate();
        String discountType = discountPolicy.getDiscountType();
        return discountPolicy.calDiscountPrice(price);
    }


}