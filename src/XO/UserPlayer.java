//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package XO;

import java.util.Scanner;

public class UserPlayer extends Player{
    private final Scanner scanner;

    public UserPlayer(PlayerType type, Game game, Scanner scanner){
        super(type, game);
        this.scanner=scanner;
    }

    @Override
    public void run(){
        while(!game.isGameOver()){
            if(game.isGameOver()) break;

            if(game.getTurn()!=type){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    return;
                }
                continue;
            }

            if(game.isGameOver()) break;

            if(game.isBoardFull()){
                game.printBoardFullOnceIfNeeded();
                break;
            }

            System.out.println("Your turn ("+type+"). Enter row and col (0-4):");

            int r, c;
            try {
                r=scanner.nextInt();
                c=scanner.nextInt();
            } catch (Exception e){
                scanner.nextLine();
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (!game.isCellFree(r, c)){
                System.out.println("Cell is not free/out of bounds. Try again.");
                continue;
            }

            boolean placed=game.placeMark(r, c, type);
            if(placed){
                game.printBoard();
            } else {
                System.out.println("Could not place mark. Try again.");
            }
        }
        game.printBoardFullOnceIfNeeded();
    }
}
