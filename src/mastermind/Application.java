package mastermind;

import mastermind.controller.GameController;

// punto di accesso del progetto dove abbiamo in metodo main
public class Application {

    public static void main(String[] args) {
        // Instanzio un GameController
        GameController masterMind = new GameController();
        // Faccio partire il gioco, perche durante costruzione del Controller ho fatto tutto il setup per farlo partire
        masterMind.start();
    }
}
