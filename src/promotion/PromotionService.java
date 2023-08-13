package promotion;

import cart.CartService;

import java.util.ArrayList;
import java.util.Arrays;

public class PromotionService {
    ArrayList<Promotion> allPromotions = new ArrayList<>(Arrays.asList(
            new GeneralPromotion(1, "코드스테이츠 수강생 프로모션", "[프로모션]아이스크림"),
            new GeneralPromotion(2, "어린이 프로모션", "[프로모션]장난감"),
            new SetPromotion(100,"햄버거 음료세트 할인","[프로모션]세트할인(새우&감튀)",500,new int[]{1,10}),
            new SetPromotion(110,"햄버거 음료세트 할인","[프로모션]세트할인(와퍼&콜라)",1000,new int[]{5,20})
            ));

    public ArrayList<Promotion> getAllPromotions() {
        return allPromotions;
    }
    public int getPromotionsSize(){
        return allPromotions.size();
    }

    public Promotion findPromotionById(int id){
        for(Promotion promotion : allPromotions){
            if(promotion.getpromotionID() == id) return promotion;
        }
        return null;
    }
    public ArrayList<Promotion> findapplyPromotion(int id, CartService cartService){
        ArrayList<Promotion> promotionsApply = new ArrayList<>();
        // 일반 프로모션 추가
        Promotion tmp  = findPromotionById(id);
        if(tmp !=null) promotionsApply.add(tmp);
        // set 프로모션 추가
        ArrayList<SetPromotion> tmpSets  = getSetPromotion();
        for(SetPromotion tmpSetpro : tmpSets){
            if(tmpSetpro.checkPromotion(cartService)){
                promotionsApply.add(tmpSetpro);
            }
        }
        return promotionsApply;
    }
    public ArrayList<SetPromotion> getSetPromotion(){
        ArrayList<SetPromotion> setPromotions = new ArrayList<>();
        for(Promotion promotion : allPromotions){
            if(promotion instanceof SetPromotion){
                setPromotions.add((SetPromotion) promotion);
            }
        }

        return setPromotions;
    }

    public ArrayList<Promotion> getGeneralPromotion(){
        ArrayList<Promotion> generalPromotion = new ArrayList<>();
        for(Promotion promotion : allPromotions){
            if(promotion instanceof GeneralPromotion){
                generalPromotion.add(promotion);
            }
        }
        return generalPromotion;
    }
}