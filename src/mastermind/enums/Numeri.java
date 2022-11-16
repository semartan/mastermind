package mastermind.enums;

import mastermind.interfaces.ISecretType;

// Classe Numeri che implementa interfaccia generica ISecretType
public class Numeri implements ISecretType {
    private final int number;

    // Costruttore dei numeri che accetta un numero di tipo int tra 0 e 9 altrimenti torna un eccezione
    public Numeri(int numero) {
        if (numero < 0 || numero > 9) {
            throw new RuntimeException("Numero non valido deve essere compreso tra 0-9");
        }
        this.number = numero;
    }

    @Override
    public String toString() {
        return number + "";
    }

    // metodo per controllare uguaglianze tra due numeri, in quanto accedo tramite interfaccia
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Numeri numeri = (Numeri) o;
        return number == numeri.number;
    }
}
