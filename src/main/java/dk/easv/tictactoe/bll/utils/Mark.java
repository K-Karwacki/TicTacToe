package dk.easv.tictactoe.bll.utils;

public enum Mark
{
  X('X'),
  O('O'),
  BLANK(' ');

  final char mark;

  Mark(char initMark){
    mark = initMark;
  }

  public boolean  isMarked(){
    return this != BLANK;
  }

  public char getMark(){
    return this.mark;
  }
}
