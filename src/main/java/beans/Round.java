package beans;

public class Round {

    private int currentPlayer;

    private Tile currentTile;


    public Round(int currentPlayer, Tile currentTile) {
        this.currentPlayer = currentPlayer;
        this.currentTile = currentTile;

    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Tile getCurrentTile() {
        return this.currentTile;
    }


    public void setCurrentPlayer(int newPlayer) {
        this.currentPlayer = newPlayer;
    }

    public void setCurrentTile(Tile newTile) {
        this.currentTile = newTile;
    }

}

