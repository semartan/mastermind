package mastermind.controller;

import mastermind.enums.Numeri;
import mastermind.interfaces.IGameController;

import java.util.Random;
import java.util.Scanner;

// Controller per tipologia Numeri il quale implementa Interfaccia IGameController
public class NumberTypeController implements IGameController {
    @Override
    public Numeri random() {
        return new Numeri(new Random().nextInt(9));
    }

    @Override
    public Numeri readValidInput(Scanner input) {
        Numeri numeri = null;

        while (numeri == null) {
            try {
                numeri = new Numeri(input.nextInt());
            } catch (Exception e) {
                System.err.println("Numero inserita non valida");
            }
        }
        return numeri;
    }
}
