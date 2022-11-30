package engine.match;

import engine.board.Board;
import engine.board.BoardInterface;

import java.util.Map;

public class Match implements MatchInterface{
    private Map boards;

    public Match(Map boards) {
        this.boards = boards;
    }

    public void addBoard(Board board){

    }
    public void deleteBoard(String name){

    }

    public void renameBoard(String oldName, String newName){

    }

    public Board getBoard(String name){ return (Board) boards.get(name); }

    public Map getBoards(){return boards;}

    public int getNumberOfPlayers() {return boards.size();}
}
