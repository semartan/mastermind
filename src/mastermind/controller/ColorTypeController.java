package mastermind.controller;

import mastermind.enums.Colori;
import mastermind.interfaces.IGameController;

import java.util.Random;
import java.util.Scanner;

// Controller per tipologia Colori il quale implementa Interfaccia IGameController
public class ColorTypeController implements IGameController {

    @Override
    public Colori random() {
        return Colori.values()[new Random().nextInt(Colori.values().length - 1)];
    }

    @Override
    public Colori readValidInput(Scanner input) {
        Colori colore = null;

        while (colore == null) {
            try {
                colore = Colori.valueOf(input.nextLine().toUpperCase());
            } catch (Exception e) {
                System.err.println("Colore inserita non valida");
            }
        }
        return colore;
    }
}
