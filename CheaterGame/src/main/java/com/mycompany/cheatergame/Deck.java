package com.mycompany.cheatergame;
import java.util.*;


public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public void distribute(List<Player> players) {
        int numPlayers = players.size();
        int i = 0;
        while (!cards.isEmpty()) {
            players.get(i % numPlayers).addCard(cards.remove(0));
            i++;
        }
    }
}
