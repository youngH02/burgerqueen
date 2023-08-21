package board;

import delivery.Delivery;
import delivery.DeliveryService;
import order.OrderService;
import product.Menu;
import product.MenuOnSale;
import cart.CartService;
import promotion.GeneralPromotion;
import promotion.Promotion;
import promotion.PromotionService;
import promotion.SetPromotion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HomeBoard {

    private Scanner sc;
    private String input;
    private int selectNum;
    private MenuOnSale menuOnSale;
    private CartService cartService;
    private PromotionService promotions;
    private OrderService orderService;
    private DeliveryService deliveryService;
    private final int MAXCOUNT = 20;

    public HomeBoard() {

        this.sc = new Scanner(System.in);
        this.menuOnSale = new MenuOnSale(); //판매중인 메뉴, 할인되는 메뉴 생성
        this.cartService = new CartService();
        this.promotions = new PromotionService(); //프로모션 정보 생성
        this.orderService = new OrderService();
        this.deliveryService = new DeliveryService();
    }

    public void moveToHome(){
        System.out.println(PrintUtil.introString);
        input = sc.nextLine();
        try{
        switch(input){
            case "1":
                viewMenyByCategory();
                break;
            case "2" :
                viewCartItems();
                break;
            case "3" :
                PrintUtil.printDiscountMenu();
                break;
            case "-":
                System.exit(0);
            case "5" : //배달현황
                viewDeliveryStatus();
                break;
            default:
                System.out.println(PrintUtil.inputError);
               // moveToHome();
        }}
        catch(Exception e){
            System.out.println("입력값 오류 : 숫자입력!!! "+e.getMessage());
        }
        moveToHome();
    }

    private void viewMenyByCategory() throws Exception{
        int displayIndex = PrintUtil.printMenuByCategory(PrintUtil.displayCategoryOrder, menuOnSale);

        System.out.print("메뉴 번호를 입력해주세요. ");

       // input = sc.nextLine();
       // selectNum = Integer.parseInt(input.trim());
        selectNum = validateNum(displayIndex);
        if(selectNum == displayIndex) {
            moveToHome(); //종료 입력했으면 홈 메뉴판으로 이동
        }

        Menu selectMenu = menuOnSale.getMenuByIndex(selectNum);
        if( selectMenu != null) {
            System.out.print("[📣] 수량을 입력해 주세요. ");
            selectNum = validateNum(MAXCOUNT);
            //input = sc.nextLine();
            //selectNum = Integer.parseInt(input.trim());
            cartService.addCart(selectMenu,selectNum );
            System.out.println("[📣] "+selectMenu.getName()+"*"+selectNum+"를(을) 장바구니에 담았습니다.");
        }else{
            System.out.println(PrintUtil.inputError);
        }

        viewMenyByCategory();
    }


    private void viewCartItems() throws Exception {
        PrintUtil.printCartItems(cartService.getCart());
        input  = sc.nextLine().trim();

        switch (input) {
            case "1":
                moveToHome();
                //break;
            case "2": // 상품 하나를 삭제
                System.out.println("삭제할 상품의 번호를 입력해주세요.");
                //input = sc.nextLine();
                //selectNum = Integer.parseInt(input.trim());
                selectNum = validateNum(cartService.cartSize());
                cartService.deleteCart(selectNum);
                //if(selectNum <= cartService.cartSize()) cartService.deleteCart(selectNum);
                //else System.out.println("장바구니에 없는 상품입니다.");
                break;
            case "3": // 장바구니 비우기
                cartService.clearCart();
                break;
            case "4": // 장바구니 상품 주문
                processOrder();
                break;
            case "-":
                System.exit(0);
            default:
                System.out.println(PrintUtil.inputError);
        }
        viewCartItems();
    }
    private void processOrder() throws Exception{ //상품 주문
        if(cartService.isEmpty()){
            System.out.println("장바구니가 비어있습니다. 상품을 담아주세요.");
            moveToHome();
        }
        PrintUtil.printAllPromotion(promotions.getGeneralPromotion());
        //input = sc.nextLine();
        //selectNum = Integer.parseInt(input.trim());
        selectNum = validateNum(promotions.getGeneralPromotion().size()+1);
        //배달 정보 선택
        int deliveryTypes = PrintUtil.printDeliveryInfo();
        deliveryTypes = validateNum(deliveryTypes);
        Delivery delivery = deliveryService.makeDelivery(deliveryTypes);
        //프로모션 적용 주문서 생성
        StringBuffer stringBuffer = null;
        stringBuffer = orderService.createOrder(cartService,promotions.findapplyPromotion(selectNum, cartService), delivery);
        //영수증 출력
        System.out.println(stringBuffer.toString());
        //장바구니 초기화
        cartService.clearCart();
        System.out.println("주문이 완료되었습니다. 홈으로 돌아갑니다.");
        input = sc.nextLine();
        moveToHome();
    }

    private void viewDeliveryStatus(){
        deliveryService.refreshDelivery();
        for(Delivery delivery: deliveryService.getDeliverys()){
            System.out.printf("%d번 [%s]배달 (%s): %d분 소요 ",delivery.getDeliveryNum(), delivery.getDeliveryTypeName(), delivery.getStatus(), delivery.getArrivalTime());
            System.out.println();
        }
    }
    //validation check 메서드
    private int validateNum(int possilbeNum){
        while(true) {
            try {
                input = sc.nextLine();
                selectNum = Integer.parseInt(input.trim());
                if (selectNum > 0 && selectNum <= possilbeNum) return selectNum;
                else {
                    //throw new outOfRange(String.format("1~%d"+PrintUtil.inputNumError,possilbeNum));
                    System.out.printf("1~%d" + PrintUtil.inputNumError, possilbeNum);
                }
            } catch (NumberFormatException e) {
                System.out.println(PrintUtil.inputError + e.getMessage());
                System.out.printf("1~%d 사이의 숫자로 다시 입력해 주세요. ", possilbeNum);
            }
            //System.out.printf("1~%d 사이의 숫자로 다시 입력해 주세요. ", possilbeNum);
        }
    }

}

class outOfRange extends Exception{
    public outOfRange(String message){
        super(message);
    }
        }