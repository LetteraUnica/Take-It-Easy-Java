package beans;

public class Round {

    private final int currentPlayer;

    private final Tile currentTile;


    public Round(int currentPlayer, Tile currentTile) {
        this.currentPlayer = currentPlayer;
        this.currentTile = currentTile;

    }

    public int getcurrentPlayer() {return currentPlayer;}

    public Tile getcurrentTile() {return currentTile;}


    }

