//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package stocks;

public class StockUpdateThread extends Thread {
    private StockServer server;

    public StockUpdateThread(StockServer server){
        this.server=server;
    }

    @Override
    public void  run(){
        for (int i=0; i<10; i++){
            for (Stock stock:Stock.values()){
                server.UpdateStock(stock,0);
            }

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
