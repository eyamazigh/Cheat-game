
package com.mycompany.cheatergame;

/**
 *
 * @author Eya
 */
import java.util.*;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean hasNoCards() {
        return hand.isEmpty();
    }

    public void removeCards(List<Card> cards) {
        hand.removeAll(cards);
    }

    public void showHand() {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }
}

