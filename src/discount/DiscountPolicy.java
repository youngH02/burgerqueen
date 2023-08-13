package discount;

public class DiscountPolicy {
    int discountRate;
    String discountType; //%, 원
    public DiscountPolicy(int discountRate, String discountType){
        this.discountType = discountType;
        this.discountRate = discountRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public String getDiscountType() {
        return discountType;
    }

    public int calDiscountPrice(int price){
        int discountPrice = 0;

        if(discountType.equals("%")) discountPrice = (price*discountRate/100);
        else if(discountType.equals("원")) discountPrice = price - discountRate;

        if(discountPrice < 0) discountPrice = 0;
        return discountPrice;
    }
    public int calDiscountRetailPrice(int price){
        int total = price - calDiscountPrice(price);
        return (total<0)? 0 : total;
    }
}