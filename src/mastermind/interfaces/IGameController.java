package mastermind.interfaces;

import mastermind.model.GameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface IGameController { // interfaccia generica per i Controller

    // ritorna un ENUM random da quelli disponibili definiti in essi
    ISecretType random(); // metodo astratta che verrà implementata da ogni Controller che implementa IGameController

    // legge input facendo validazione e ritorna ENUM in base al Controller
    ISecretType readValidInput(Scanner input); // metodo astratta che verrà implementata da ogni Controller che implementa IGameController

    /**
     * Il metodo che verifica i valori inseriti dall'utente con quello segreto generato dall'algoritmo
     *
     * @param userInput i valori dell'utente
     * @param expected  i valori dell'algoritmo
     * @return il Risultato del gioco in formato GameResult ovvero in un altro oggetto che ha solo due proprietà
     */
    default GameResult compare(List<ISecretType> userInput, List<ISecretType> expected) {
        List<Integer> samePositions = new ArrayList<>();
        List<Integer> wrongPositions = new ArrayList<>();

        // controllo prima solo quelli che si trovano nella stessa posizione
        for (int x = 0; x < userInput.size(); x++) {
            if (userInput.get(x).equals(expected.get(x))) {
                // salvo gli indici di quelli che si trovano nella stessa posizione
                samePositions.add(x);
            }
        }

        // controllo gli altri saltando le posizioni corrette
        for (int x = 0; x < userInput.size(); x++) {
            // se non sono all'indice di quelli salvati precedentemente e il valore dell'utente si trova nel segreto
            if (!samePositions.contains(x) && expected.contains(userInput.get(x))) {
                // recupero l'indice del valore in cui si trova nell'array del segreto
                int idx = expected.indexOf(userInput.get(x));
                // se questo indice non si trova in quelli del samePosition e non si trova di quelli salvati come wrongPositions
                if (!samePositions.contains(idx) && !wrongPositions.contains(idx)) {
                    // allora salvo in wrongPositions
                    wrongPositions.add(idx);
                }
            }
        }

        // ritorno il risultato in forma Oggetto composto da correctPosition e wrongPosition
        return new GameResult(samePositions.size(), wrongPositions.size());
    }

}
