package dk.easv.tictactoe.bll;

/**
 * MiniMax implementation from
 * https://github.com/DavidHurst
 */

public class MiniMax
{
  private final int MAX_DEPTH = 6;

  public MiniMax(){}

  public static int minimax(IGameBoard board, int depth,int alpha, int beta, boolean isMax){
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
                highestVal = Math.max(highestVal, minimax(board, depth -1, alpha, beta, false));
                board.resetPosition(row,col);
                alpha= Math.max(alpha,highestVal);
                if(alpha >= beta){
                  return highestVal;
                }
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
                lowestVal = Math.min(lowestVal, minimax(board, depth - 1, alpha, beta, true));
                board.resetPosition(row,col);
                beta = Math.min(beta, lowestVal);
                if(beta <= alpha){
                  return lowestVal;
                }
              }
            }
          }
        }
        return lowestVal;
      }
  }

  public static int[] getBestMove(IGameBoard board){
    int[] bestMove = new int[]{1, 1};
    int bestValue = Integer.MIN_VALUE;

    for(int row=0; row<board.getBoardWidth(); row++){
      for (int col=0; col<board.getBoardWidth(); col++){
        if(board.isPositionEmpty(row,col)){
          board.playMarkAt(row,col, 1);
          int moveValue = minimax(board, 6, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
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

  public static int evaluateBoard(IGameBoard board, int depth)
  {
    int boardWidth = board.getBoardWidth();
    String rowCount = "";
    String xWin = "222";
    String oWin = "111";
//    System.out.println(board[0[0]]);

    //Check if x or o wins on rows
    for(int row=0; row<boardWidth; row++){
      for(int col=0; col<boardWidth; col++){
//        System.out.println("Row: "+ row + " Column: " + col + " Player: " + board.getPosition(row, col));
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
}
