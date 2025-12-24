//Afik Ifrach - 314940081 , Shilat Maymon - 213249618
package XO;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    protected final PlayerType[][] gameBoard=new PlayerType[5][5];
    private PlayerType turn=PlayerType.X;

    private volatile PlayerType winner=null;
    private volatile boolean boardFullPrinted=false;

    public synchronized PlayerType getTurn(){
        return this.turn;
    }

    protected synchronized void switchTurn(){
        if (turn == PlayerType.X) {
            turn = PlayerType.O;
        } else {
            turn = PlayerType.X;
        }
    }

    public synchronized List<Cell> getFreeCells(){
        List<Cell> free=new ArrayList<>();
        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                if(gameBoard[r][c]==null){
                    free.add(new Cell(r,c));
                }
            }
        }
        return free;
    }

    public synchronized boolean isCellFree(int r, int c){
        return inBounds(r,c) && gameBoard[r][c]==null;
    }

    public synchronized boolean placeMark(int r, int c, PlayerType type){
        if(winner!=null) return false;
        if(!inBounds(r,c)) return false;
        if(gameBoard[r][c]!=null) return false;
        if(type!=turn) return false;

        gameBoard[r][c]=type;

        if(checkWinner(type)){
            winner=type;
        } else if (isBoardFullUnsafe()) {
            //board is full and no winner
        } else {
            switchTurn();
        }
        return true;
    }


    public synchronized PlayerType getWinner(){
        return this.winner;
    }

    public synchronized boolean isBoardFull(){
        return isBoardFullUnsafe();
    }

    private boolean isBoardFullUnsafe(){
        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                if(gameBoard[r][c]==null) return false;
            }
        }
        return true;
    }

    public boolean isGameOver(){
        return getWinner()!=null||isBoardFull();
    }

    public void printBoard(){
        synchronized (this){
            System.out.println("----- BOARD -----");
            for(int r=0; r<5; r++){
                for(int c=0; c<5; c++){
                    System.out.print(gameBoard[r][c]==null?".":gameBoard[r][c]);
                    if(c<4) System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("----------------");
        }
    }

    public void printBoardFullOnceIfNeeded(){
        synchronized (this){
            if(isBoardFullUnsafe() && winner==null && !boardFullPrinted){
                System.out.println("Board is full");
                boardFullPrinted=true;
            }
        }
    }

    private boolean inBounds(int r, int c){
        return r>=0 && r<5 && c>=0 && c<5;
    }

    private boolean checkWinner(PlayerType type){
        for(int r=0; r<5; r++){
            for(int c=0; c<=1; c++){
                if(allEqual(type,r,c,0,1)) return true;
            }
        }

        for(int c=0; c<5; c++){
            for(int r=0; r<=1; r++){
                if(allEqual(type,r,c,1,0)) return true;
            }
        }

        for(int r=0; r<=1; r++){
            for(int c=0; c<=1; c++){
                if(allEqual(type,r,c,1,1)) return true;
            }
        }

        for(int r=3; r<5; r++){
            for(int c=0; c<=1; c++){
                if(allEqual(type,r,c,-1,1)) return true;
            }
        }
        return false;
    }

    private boolean allEqual(PlayerType type, int startR, int startC, int dr, int dc){
        for(int i=0; i<4; i++){
            int rr=startR+i*dr;
            int cc=startC+i*dc;
            if(gameBoard[rr][cc]!=type) return false;
        }
        return true;
    }
}
