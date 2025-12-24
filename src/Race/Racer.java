package Race;

public class Racer implements Runnable {
    private static int globalId=1;

    private final int id;
    private final int speed;
    private final Track track;

    public Racer(int speed, Track track){
        if(speed<1||speed>10){
            System.out.println("Eror: speed must be between 1 and 10");
            speed=1;
        }
        this.speed=speed;
        this.track=track;

        synchronized (Racer.class){
            this.id=globalId++;
        }

    }

    public void go(){
        Thread.currentThread().setPriority(speed);

        for(int meters=1; meters<=100; meters++){
            System.out.println("Runner "+id+" ran "+meters+" meters");
        }

        int place=track.finishAndGetPlace();
        System.out.println("Runner "+id+" finished "+place+suffix(place));
    }

    private String suffix(int place){
        if(place==1) return "st";
        if(place==2) return "nd";
        if(place==3) return "rd";
        return "th";
    }

    @Override
    public void run(){
        go();
    }

}
