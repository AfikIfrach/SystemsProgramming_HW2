//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package stocks;

import java.util.Random;

public class StockReaderThread extends Thread {
    private String name;
    private Stock stock;
    private StockServer server;
    private Random random=new Random();

    public StockReaderThread(String name, Stock stock, StockServer server){
        this.name=name;
        this.stock=stock;
        this.server=server;
    }

    @Override
    public void run(){
        for(int i=0; i<10; i++){
            int value=server.GetStock(stock);
            System.out.println(
                    "Name: "+name+", "+stock+" Stock: "+value+" USD"
            );

            try{
                int sleepTime=random.nextInt(3)+1;
                Thread.sleep(sleepTime*1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
