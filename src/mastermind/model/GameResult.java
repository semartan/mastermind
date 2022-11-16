package mastermind.model;

// classe utilizzata come modello per invocare il risultato
public class GameResult {
    private final int correctPosition;
    private final int wrongPosition;

    // costruttore del risultato del gioco
    public GameResult(int samePosition, int correctNoPosition) {
        this.correctPosition = samePosition;
        this.wrongPosition = correctNoPosition;
    }

    // accesso public del correct position che verrà utilizzata in controller
    public int getCorrectPosition() {
        return correctPosition;
    }

    // trasformazione in stringa del risultato
    @Override
    public String toString() {
        return "posizione corretta: " + correctPosition +
                "\nposizione sbagliata: " + wrongPosition;
    }
}
