package product;

import discount.DiscountService;

import java.util.LinkedList;

public abstract class Menu {
    private String name;
    private String category;
    private int price;
    private int calorie;
    private boolean isDiscount = false;
    private int index=0; // 메뉴판에 보여지는 index, 변경될 수 있음.
    private int id;
    static private int idindex = 1;



    static LinkedList<Menu> allMenu = new LinkedList<>();

    public Menu() {
        this("기본 햄버거", "햄버거", 1000, 500);
    }

    public Menu(String name, String category, int price, int calorie) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.calorie = calorie;
        this.id = idindex++;
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

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public String getDiscountPrice() {
        System.out.println(name+isDiscount+id);
        if(!isDiscount) return "0원";
        String discount = "";
        discount = DiscountService.getDiscountMenus().get(id).getDiscountRate() +"";
        discount += DiscountService.getDiscountMenus().get(id).getDiscountType();
        if (discount.isEmpty()) return "0원";
        return discount;
    }

    public int getDisCalcultPrice() {
        if(!isDiscount) return 0;
        int discountMoney = DiscountService.getDiscountMenus().get(id).getDiscountRate();
        String discountType = DiscountService.getDiscountMenus().get(id).getDiscountType();
        int total=price;
        String discount = "";

        if(discountType.equals("%")) total = price*(100-discountMoney);
        else if(discountType.equals("원")) total = price - discountMoney;

        if(total < 0) total = 0;
        return total;
    }
}