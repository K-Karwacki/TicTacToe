package dk.easv.tictactoe.bll;

public class MiniMax
{
  private final int MAX_DEPTH = 6;

  public MiniMax(){}

  public int minimax(IGameBoard board, int depth, boolean isMax){
      int boardVal = evaluateBoard(board);
      if(Math.abs(boardVal) == 10 || board.isBoardFull() || depth ==0){
        return boardVal;
      }


      if(isMax){
        int highestVal = Integer.MIN_VALUE;
        for(int row = 0; row< board.getBoardWidth(); row++){
          for(int col = 0; col<board.getBoardWidth(); col++){
            if(!board.isPositionTaken(row,col)){

            }
          }
        }
      }
      return 0;
  }

  private int evaluateBoard(IGameBoard board)
  {
    return 1;
  }
}
