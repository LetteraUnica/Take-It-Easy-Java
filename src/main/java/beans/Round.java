package beans;

public class Round {

    private final int currentPlayer;

    private final Tile currentTile;


    public Round(int currentPlayer, Tile currentTile) {
        this.currentPlayer = currentPlayer;
        this.currentTile = currentTile;

    }

    public int getCurrentPlayer() {return currentPlayer;}

    public Tile getCurrentTile() {return currentTile;}


    }

