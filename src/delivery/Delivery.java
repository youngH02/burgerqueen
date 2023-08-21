package delivery;

import order.Order;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Delivery {
    // 배달 순서, 예상 시간(랜덤)
    private int deliveryNum;
    private int arrivalTime;
    private static int index=1;
    private STATUS status;
    private static TYPE deliveryType;
    enum STATUS {WAIT, ING, DONE};
    public static enum TYPE {
        SELF(1,0, "셀프픽업"), CAR(2, 500, "자동차"), MOTORCYCLE(3,1000,"오토바이"), BICYCLE(4, 300, "자전거");
        private final int typeID;
        private final int deliveryPrice;
        private final String delivaryTypeName;
        TYPE(int typeID, int deliveryPrice, String delivaryTypeName){
            this.typeID = typeID;
            this.deliveryPrice = deliveryPrice;
            this.delivaryTypeName = delivaryTypeName;}
        public int getDeliveryPrice(){return deliveryPrice;}
        private static final Map<Integer, TYPE> BY_NUMBER =
                Stream.of(values()).collect(Collectors.toMap(TYPE::getTypeID, Function.identity()));

        private static TYPE getTYPEByID(int id){
            return BY_NUMBER.get(id);

        }
        public String getDelivaryTypeName() {return delivaryTypeName;}
        public int getTypeID() {return typeID;};

    }
    private Order order;

    public Delivery(int deliveryTypeID) {
        this.deliveryNum = index++;
        this.status = STATUS.WAIT;
        this.deliveryType = TYPE.getTYPEByID(deliveryTypeID);
        this.arrivalTime = arrivalTime();
        //addDeliveryToOrder();
    }

    public void addDeliveryToOrder(Order order){
        this.order = order;
        order.getCart().addDelivery(this);
    }
    private int arrivalTime(){
        if(!deliveryType.equals(TYPE.SELF)) return (int)(Math.random()*50)+ 10;
        return 0;
    }

    public int getDeliveryNum() {
        return deliveryNum;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public STATUS getStatus() {
        return status;
    }

    public void updateStatus(){
        if(this.status.equals(STATUS.WAIT)) this.status = STATUS.ING;
        else if(this.status.equals(STATUS.ING)) this.status = STATUS.DONE;
    }

    public void updateArrivalTime(){
        this.arrivalTime = Math.max((arrivalTime - 10), 0);
    }
    public int getDeliveryPrice(){
        return deliveryType.getDeliveryPrice();
    }
    public TYPE getDeliveryType() {
        return deliveryType;
    }
    public String getDeliveryTypeName() {
        return deliveryType.getDelivaryTypeName();
    }
}