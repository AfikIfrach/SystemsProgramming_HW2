//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package Race;

public class Track {
    private int finishedRacers=0;

    public synchronized int finishAndGetPlace(){
        finishedRacers++;
        return finishedRacers;
    }

    public synchronized int getFinishedRacers(){
        return this.finishedRacers;
    }
}
