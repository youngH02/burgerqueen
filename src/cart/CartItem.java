package cart;

public class CartItem {
    // 상품 고유 번호, 상품 이름, 개당 단가, 수량, 금액(단가 * 수량)
    private final int productId;
    private final String productName;
    private final int cost;
    private int count;
    private int totalPrice;
    private final int discountPrice; // 할인금액
    private final int retailPrice;   // 소비자가
    public CartItem(int productId, String productName, int cost, int count, int discountPrice) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.count = count;
        this.discountPrice = discountPrice;
        this.retailPrice = cost-discountPrice;
        this.totalPrice = retailPrice * count;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }
    public void addCount(int count) {
        this.count +=count;
        this.totalPrice = this.count * this.retailPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }
}