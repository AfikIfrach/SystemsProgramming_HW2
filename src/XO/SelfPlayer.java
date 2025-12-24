//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package XO;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelfPlayer extends Player {

    public SelfPlayer(PlayerType type, Game game){
        super(type, game);
    }

    @Override
    public void run(){
        while(!game.isGameOver()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                return;
            }

            if(game.isGameOver()) break;

            if(game.getTurn()!=type) continue;


            List<Cell> free=game.getFreeCells();
            if(free.isEmpty()){
                game.printBoardFullOnceIfNeeded();
                break;
            }

            Cell chosen=free.get(ThreadLocalRandom.current().nextInt(free.size()));
            boolean placed=game.placeMark(chosen.row, chosen.col, type);
            if(placed){
                game.printBoard();
            }
        }

        game.printBoardFullOnceIfNeeded();
    }
}
