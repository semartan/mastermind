# MASTERMIND

## Traccia

Sviluppare il gioco Mastermind in c/java. Si ricorda che il Mastermind deve gestire i codici con possibili ripetizioni
di elementi (ad esempio ripetizioni di colori, rosso rosso blu verde, oppure di lettere, oppure di numeri), invece non
c'è un numero minimo fissato per i colori/numeri/lettere da implementare

## Descrizione del gioco

Il gioco mastermind consiste nell'indovinare di un segreto generato dal computer.

All'avvio viene richiesto tipologia di segreto da indovina tra numeri, colori e lettere.

Poi viene scelto la difficoltà, ovvero quanti combinazioni tra 3 e 5 dove il 5 è il più difficile

Una volta fatta queste scelte l'utente deve inserire i suoi tentativi, ad ogni lettura il computer gli dice quanti si
trovano nella posizione corretta e quanti sono presenti ma in posizione sbagliata.

I tentativi disponibili sono 10. Naturalmente lo scopo è quello di indovinare nel minor tentativo possibile.

## Come avviare il gioco

- Aprire con un IDE, ad esempio con IntelliJ poi aprire la classe Application.java e avviare premendo il play dell'IDE.
- Spostarsi con il terminale nella cartella mastermind/src/mastermind eseguire il comando
  javac Application.java
  poi java Application