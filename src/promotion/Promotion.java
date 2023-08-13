package promotion;

import cart.CartItem;

public class Promotion {
    private final int promotionID;

    private String promotion_name;
    private String promotion_contents;
    private int promotionDiscount;
    private boolean isOptional;

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public Promotion(int promotionID, String promotion_name, String promotion_contents, int promotionDiscount, boolean isOptional) {
        this.promotionID = promotionID;
        this.promotion_name = promotion_name;
        this.promotion_contents = promotion_contents;
        this.promotionDiscount=promotionDiscount;
    }

    public int getpromotionID() {
        return promotionID;
    }

    public String getpromotion_name() {
        return promotion_name;
    }

    public String getPromotion_contents() {
        return promotion_contents;
    }

    public boolean isOptional() {
        return isOptional;
    }
}