package mastermind.enums;

// ENUM Tipologia definiti del gioco
public enum Tipologia {
    NUMERI, COLORI, LETTERE;

    public String description() {
        switch (this) {
            case COLORI:
                return "colore";
            case NUMERI:
                return "numero";
            case LETTERE:
            default:
                return "lettera";
        }
    }
}
