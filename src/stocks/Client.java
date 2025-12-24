//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package stocks;

import java.util.Random;

public class Client implements Runnable {
    private String name;
    private String lastName;
    private StockServer stock;

    public Client(String _name, String _lastName, StockServer _stock){
        this.name = _name;
        this.lastName = _lastName;
        this.stock = _stock;
    }

    public String toPrint() throws Exception {

        String[] options = {"MICROSOFT", "APPLE", "GOOGLE"};
        int StockValue=0;
        Random rand = new Random();
        String chosen = options[rand.nextInt(options.length)];
        switch(chosen) {
            case "MICROSOFT":
                StockValue = stock.GetStock(Stock.MICROSOFT);
            case "APPLE":
                StockValue = stock.GetStock(Stock.APPLE);
            case "GOOGLE":
                StockValue = stock.GetStock(Stock.GOOGLE);
        }
        String str = String.valueOf(StockValue);
        return  "Name: " + this.name + " " +  this.lastName + ", " + chosen + " Stock: " + str + " USD";

    }

    public void run() {
        for(int i=0; i<10; i++){
            Random rand = new Random();
            try {
                System.out.println(this.toPrint());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int sleepTime = rand.nextInt(2001) + 1000;
            try{
                Thread.sleep(sleepTime);
            }
            catch(InterruptedException e)
            {
                System.err.println("[" + this.name + " " + this.lastName + "]"+ " disturb");
                Thread.currentThread().interrupt();
                return;
            }



        }
    }
}
