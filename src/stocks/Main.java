package stocks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        StockServer stock = new StockServer();
        int value = stock.GetStock(Stock.MICROSOFT);
        Runnable c1 = new Client("a", "b", stock);
        Runnable c2 = new Client("c", "d", stock);

        System.out.println(c1);
        Thread t1 =new Thread(c1);
        Thread t2 =new Thread(c2);
        t1.start();
        t2.start();
//    System.out.println(value);
    }
}
