package delivery;

import java.util.ArrayList;

public class DeliveryService{
    private final int MAXCOUNT = 2;
    private static int index = 0;

    private ArrayList<Delivery> deliverys = new ArrayList<>();


    public Delivery makeDelivery(int index){
        Delivery delivery = new Delivery(index);
        deliverys.add(delivery);
        return delivery;
    }


    public ArrayList<Delivery> getDeliverys(){
        return deliverys;
    }

    public void refreshDelivery(){
        for(Delivery delivery : deliverys){
            if( delivery.getStatus().equals(Delivery.STATUS.ING)){
                if(delivery.getArrivalTime() == 0) {
                    delivery.updateStatus();
                    index--;
                }
                delivery.updateArrivalTime();
            }else if(index<MAXCOUNT && delivery.getStatus().equals(Delivery.STATUS.WAIT)) {
                delivery.updateStatus();
                index++;
            }
            if (index>MAXCOUNT) break;
        }
    }
}