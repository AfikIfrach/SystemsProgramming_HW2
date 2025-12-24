//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
//https://github.com/AfikIfrach/SystemsProgramming_HW2.git
package stocks;

public class Main {
    public static void main(String[] args) {
        StockServer server = new StockServer();

        StockReaderThread tami=new StockReaderThread("Tami Tan",Stock.MICROSOFT,server);
        StockReaderThread tim=new StockReaderThread("Tim Seruli",Stock.APPLE,server);
        StockReaderThread sima=new StockReaderThread("Sima Didas",Stock.GOOGLE,server);

        StockUpdateThread updater=new StockUpdateThread(server);

        tami.start();
        tim.start();
        sima.start();
        updater.start();
    }
}
