package board;

import cart.CartItem;
import order.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private static final String PATH = "./receipt/";
    private static String FILE_NAME;
    private static StringBuffer stringBuffer;


    public static StringBuffer saveToFile(Order order){
        checkDir();
        stringBuffer = composeData(order);
        FILE_NAME = String.format("%d.txt", order.getOrderId());

        try {
            FileWriter fileWriter = new FileWriter(PATH + FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuffer.toString());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }


        return stringBuffer;
    }

    private static StringBuffer composeData(Order order){
        stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("주문 번호 : %2d\n"+PrintUtil.dash+"\n[주문 상품]\n", order.getOrderId()))
                .append("상품명                 단가   수량     금액\n");

        for(CartItem cart : order.getCart().getCart()){
            stringBuffer.append(String.format("%-15s %6d원 %4d %7d원\n",
                    cart.getProductName(), cart.getRetailPrice(), cart.getCount(), cart.getTotalPrice()));
        }
        stringBuffer.append(PrintUtil.dash)
                .append(String.format("\n결제 금액 : %d\n", order.getPaymentPrice()));
        return stringBuffer;
    }

    private static void checkDir(){
        File Folder = new File(PATH);
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더없으면 생성
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
    }

}