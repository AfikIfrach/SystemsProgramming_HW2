//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
//https://github.com/AfikIfrach/SystemsProgramming_HW2.git
package XO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        Scanner scanner=new Scanner(System.in);

        System.out.println("Choose game mode:");
        System.out.println("1 - Self vs Self");
        System.out.println("2 - Self vs User");
        int choise=scanner.nextInt();

        Game game;
        Player p1;
        Player p2;

        if(choise==1){
            game=new SelfGame();
            p1=new SelfPlayer(PlayerType.X, game);
            p2=new SelfPlayer(PlayerType.O, game);
        } else {
            game=new UserGame();
            p1=new SelfPlayer(PlayerType.X, game);
            p2=new UserPlayer(PlayerType.O, game, scanner);
        }

        game.printBoard();

        p1.start();
        p2.start();

        p1.join();
        p2.join();

        PlayerType winner=game.getWinner();
        if(winner!=null){
            System.out.println("Winner is: "+winner);
        } else {
            System.out.println("No winner.");
        }
    }
}
