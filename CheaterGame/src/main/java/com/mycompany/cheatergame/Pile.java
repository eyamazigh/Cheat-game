
package com.mycompany.cheatergame;

import java.util.*;

public class Pile {
    private final List<Card> pileCards = new ArrayList<>();

    public void addCards(List<Card> cards) {
        pileCards.addAll(cards);
    }

    public List<Card> revealPile() {
        return new ArrayList<>(pileCards);
    }

    public List<Card> takePile() {
        List<Card> toReturn = new ArrayList<>(pileCards);
        pileCards.clear();
        return toReturn;
    }

    public boolean isEmpty() {
        return pileCards.isEmpty();
    }
}

