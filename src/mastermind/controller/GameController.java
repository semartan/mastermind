package mastermind.controller;

import mastermind.enums.Colori;
import mastermind.enums.Lettere;
import mastermind.enums.Tipologia;
import mastermind.interfaces.IGameController;
import mastermind.interfaces.ISecretType;
import mastermind.model.GameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    // lettore input dal console
    private final Scanner input;
    // ENUM delle tipologie di gioco possibili
    private final Tipologia tipologia;
    // difficoltà del gioco, ovvero da quanti combinazioni deve essere composto indovinello
    private final int difficolta;
    // numero tentativi disponibili
    private int tentatives = 10;
    // il segreto generato dall'algoritmo in base alla tipologia scelta dall'utente
    private final List<ISecretType> secret;
    private boolean won = false;
    // Controller principale: in base alla tipologia scelta dall'utente verrà instanziata quella corretta tra tutti quelli che implementano
    // Interfaccia IGameController
    private final IGameController controller;


    // All'invocare del Controller faccio tutto il setup necessario
    public GameController() {
        // instanzio Scanner per leggere dal console
        input = new Scanner(System.in);
        System.out.println("Benvenuti nel gioco MasterMind. \n");
        // imposto tipologia del gioco tra numeri - colori - lettere
        tipologia = selectGameType();
        // invoco il Controller principale in base alla tipologia scelta precedentemente
        controller = buildController();
        // faccio impostare la difficolta del gioco all'utente
        difficolta = selectGameDifficult();
        // genero il segreto da indovinare in base alla tipologia
        secret = generateSecret();
    }

    /**
     * visualizza le istruzioni del gioco e lo avvia
     */
    // l'unico metodo accessibile dall'esterno per avviare il gioco
    public void start() {
        // visualizzo le istruzioni del gioco
        displayGameInstructions();
        // inizio il gioco
        play();
    }

    private void play() {
        List<ISecretType> userInput = new ArrayList<>();
        while (tentatives > 0 && !won) {
            showRemainingTentatives();
            tentatives--;

            for (int x = 1; x <= difficolta; x++) {
                System.out.println("Inserisci " + x + "a " + tipologia.description() + " seguita da un invio");
                userInput.add(controller.readValidInput(input));
            }

            GameResult result = controller.compare(userInput, secret);
            System.out.println(result);
            if (result.getCorrectPosition() == secret.size()) {
                won = true;
            }
            userInput.clear();
        }

        if (won) {
            System.out.println("Bravo hai indovinato!");
        } else {
            System.out.println("Hai perso.");
        }
    }

    /**
     * Invoca il controller corretto in base alla tipologia scelta dall'utente
     */
    private IGameController buildController() {
        switch (tipologia) {
            case NUMERI:
                // invoco Controller per tipologia numeri
                return new NumberTypeController();
            case LETTERE:
                return new LetterTypeController();
            case COLORI:
            default:
                return new ColorTypeController();
        }
    }

    /**
     * Genero il codice segreto sempre in base alla tipologia
     */
    private List<ISecretType> generateSecret() {
        // costruisco la lista, in base alla difficoltà scelta dall'utente riempio con i numeri/colori/lettere casuali
        List<ISecretType> secrets = new ArrayList<>();
        for (int x = 0; x < difficolta; x++) {
            // la definizione del controller è nell'interfaccia IGameController poi sono implementati da ogni Controller che implementano questa interfaccia
            secrets.add(controller.random());
        }
        // ritorno il segreto generato, ad esempio [BLU, ROSSO, VERDE]
        return secrets;
    }

    private void displayGameInstructions() {
        System.out.println("Tipologia gioco scelto è per " + tipologia.name().toLowerCase());
        switch (tipologia) {
            case COLORI:
                System.out.println("Colori disponibili sono: ");
                // Visualizzo tutti colori definiti nel ENUM Colori
                // .values torna un array di tutti enum poi prendo i nomi e trasformo in minuscolo poi li concateno con un delimitatore virgola
                // Arrays.stream trasforma un Array in stream poi con il .map trasformo in formato desiderato poi con .collect raccolgo in formato stringa
                System.out.println(Arrays.stream(Colori.values()).map(c -> c.name().toLowerCase()).collect(Collectors.joining(", ")));
                break;
            case LETTERE:
                System.out.println("Lettere disponibili sono: ");
                System.out.println(Arrays.stream(Lettere.values()).map(c -> c.name().toLowerCase()).collect(Collectors.joining(", ")));
                break;
            case NUMERI:
                System.out.println("Tutti i numeri sono ammessi da 0 a 9");
        }
    }

    private Tipologia selectGameType() {
        System.out.println("Scegliere tipologia del gioco:");
        // Elenco tutte le tipologie definite
        for (Tipologia t : Tipologia.values()) {
            // ordinal torna l'indice del ENUM a partire da 0 invece la funzione name() torna il nome come è definito all'interno
            System.out.println(t.ordinal() + " per modalità " + t.name());
        }

        Tipologia tipologiaGioco = null;
        // ciclo finché l'utente non ha correttamente scelto la tipologia del gioco
        while (tipologiaGioco == null) {
            try {
                // leggo il numero inserito dall'utente se ha messo qualcosa diverso da 0,1,2 genero un eccezione
                tipologiaGioco = Tipologia.values()[Integer.parseInt(input.nextLine())];
            } catch (Exception e) {
                System.err.println("Valore non valido per favore inserire un numero tra 0 e " + (Tipologia.values().length - 1));
                // ritorno ricorsivamente stessa funzione fino a farlo selezionare correttamente
                return selectGameType();
            }
        }
        // ritorno la tipologia
        return tipologiaGioco;
    }

    private int selectGameDifficult() {
        // 3 facile - 4 medio - 5 difficile
        System.out.println("Seleziona difficoltà gioco");
        System.out.println("3 per facile");
        System.out.println("4 per medio");
        System.out.println("5 per difficile");
        int difficult = 0;

        while (difficult < 3) {
            try {
                difficult = Integer.parseInt(input.nextLine());
                if (difficult < 3 || difficult > 5) {
                    throw new Exception("Non valida");
                }
            } catch (Exception e) {
                return selectGameDifficult();
            }
        }
        return difficult;
    }

    private void showRemainingTentatives() {
        System.out.println(tentatives + " tentativi disponibili");
    }
}