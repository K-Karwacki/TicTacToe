package dk.easv.tictactoe.bll;

/**
 * MiniMax implementation from
 * https://github.com/DavidHurst
 */

public class MiniMax
{
  private final int MAX_DEPTH = 6;

  public MiniMax(){}

  /**
   * Method simulates moves by switching between X and O and returns the highest value move for the X player. Recursion terminates and returns the highest value move when a terminal node or when the maximum search depth was reached.
   * @param board - IGameBoard interface to get board info and simulate play
   * @param depth - limit for the recursive minimax method
   * @param isMax - maximising or minimising player
   */
  private static int minimax(IGameBoard board, int depth,boolean isMax){
      int boardVal = evaluateBoard(board, depth);
      if(Math.abs(boardVal) > 0 || board.isBoardFull() || depth ==0){
        return boardVal;
      }


      if(isMax){
        int highestVal = Integer.MIN_VALUE;
        for(int row = 0; row< board.getBoardWidth(); row++){
          for(int col = 0; col<board.getBoardWidth(); col++){
            if(board.isPositionEmpty(row, col)){
              if(board.playMarkAt(row, col, 1)){
                highestVal = Math.max(highestVal, minimax(board, depth -1, false));
                board.resetPosition(row,col);
              };
            }
          }
        }
        return highestVal;
      }else{
        int lowestVal = Integer.MAX_VALUE;
        for(int row=0; row<board.getBoardWidth(); row++){
          for(int col=0; col<board.getBoardWidth(); col++){
            if(board.isPositionEmpty(row, col)){
              if(board.playMarkAt(row, col, 0)){
                lowestVal = Math.min(lowestVal, minimax(board, depth - 1, true));
                board.resetPosition(row,col);
              }
            }
          }
        }
        return lowestVal;
      }
  }



  /**
   * evaluate given board and returns:
   * (10 + depth) if computer wins in given combination,
   * (-10 - depth) if computer loses in given combination
   * @param board - IGameBoard interface to get board info
   * @param depth - limit for the recursive minimax method
   */
  private static int evaluateBoard(IGameBoard board, int depth)
  {
    int boardWidth = board.getBoardWidth();
    String rowCount = "";
    String xWin = "222";
    String oWin = "111";

    //Check if X or O wins on rows
    for(int row=0; row<boardWidth; row++){
      for(int col=0; col<boardWidth; col++){
        rowCount += board.getPosition(row,col);
        if(rowCount.equals(xWin)){
          return 10 + depth;
        }else if(rowCount.equals(oWin)){
          return -10 - depth;
        }
      }
      rowCount = "";
    }


    //checks if x or o wins on columns
    rowCount = "";
    for(int col=0; col<boardWidth; col++){
      for(int row=0; row<boardWidth; row++){
        rowCount += board.getPosition(row,col);
        if(rowCount.equals(xWin)){
          return 10 + depth;
        }else if(rowCount.equals(oWin)){
          return -10 - depth;
        }
      }
      rowCount = "";
    }


    //Diagonal from left top to right bottom
    rowCount ="";
    for(int row=0; row<boardWidth; row++){
      rowCount += board.getPosition(row, row);
      if(rowCount.equals(xWin)){
        return 10 + depth;
      }else if(rowCount.equals(oWin)){
        return -10 - depth;
      }
    }

    //Diagonal from left bottom to right top
    rowCount ="";
    for(int col=boardWidth-1; col>=0; col--){

      rowCount += board.getPosition(col, boardWidth -1 - col);
      if(rowCount.equals(xWin)){
        return 10+depth;
      }else if (rowCount.equals(oWin)){
        return -10-depth;
      }
    }

    return 0;
  }


  /**
   * public method to connect with game
   * @param board - IGameBoard interface to get board info, simulates the best move and then returns the best move
   */
  public static int[] getBestMove(IGameBoard board){
    int[] bestMove = new int[]{-1, -1};
    int bestValue = Integer.MIN_VALUE;

    for(int row=0; row<board.getBoardWidth(); row++){
      for (int col=0; col<board.getBoardWidth(); col++){
        if(board.isPositionEmpty(row,col)){
          board.playMarkAt(row,col, 1);
          int moveValue = minimax(board, 6, false);
          board.resetPosition(row,col);
          if(moveValue>bestValue){
            bestMove[0] = row;
            bestMove[1] = col;
            bestValue = moveValue;
          }
        }
      }
    }
    return bestMove;
  }
}
