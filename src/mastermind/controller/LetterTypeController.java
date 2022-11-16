package mastermind.controller;

import mastermind.enums.Lettere;
import mastermind.interfaces.IGameController;

import java.util.Random;
import java.util.Scanner;

// Controller per tipologia Lettere il quale implementa Interfaccia IGameController
public class LetterTypeController implements IGameController {
    @Override
    public Lettere random() {
        return Lettere.values()[new Random().nextInt(Lettere.values().length - 1)];
    }

    @Override
    public Lettere readValidInput(Scanner input) {
        Lettere lettera = null;

        while (lettera == null) {
            try {
                lettera = Lettere.valueOf(input.nextLine().toUpperCase());
            } catch (Exception e) {
                System.err.println("Lettera inserita non valida");
            }
        }
        return lettera;
    }
}
