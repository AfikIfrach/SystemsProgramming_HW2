package stocks;

import java.security.InvalidParameterException;
import java.util.concurrent.ThreadLocalRandom;

public class StockServer {
    private int microsoftValue = 220;
    private int appleValue = 110;
    private int googleValue = 1512;


    public synchronized int GetStock(Stock stock) {
        switch(stock) {
            case MICROSOFT:
                return microsoftValue;
            case APPLE:
                return appleValue;
            case GOOGLE:
                return googleValue;
            default:
                throw new InvalidParameterException("no such stock type");
        }
    }

    public synchronized void UpdateStock(Stock stock, int value){
        int newValue= ThreadLocalRandom.current().nextInt(100,501);
        switch(stock) {
            case MICROSOFT:
                microsoftValue=newValue;
                break;
            case APPLE:
                appleValue=newValue;
                break;
            case GOOGLE:
                googleValue=newValue;
                break;
            default:
                throw new InvalidParameterException("no such stock type");
        }
    }
}
