
package com.mycompany.cheatergame;

/**
 *
 * @author Eya
 */
import java.util.*;

public class Game {
    private final List<Player> players;
    private final Pile centralPile = new Pile();
    private int currentPlayerIndex = 0;
    private Card.Rank currentAnnouncedRank = Card.Rank.TWO;

    public Game(List<Player> players) {
        this.players = players;
        Deck deck = new Deck();
        deck.distribute(players);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\nCurrent rank to play: " + currentAnnouncedRank);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");

            currentPlayer.showHand();

            System.out.print("Enter indices of cards to play (comma-separated): ");
            String[] indices = scanner.nextLine().split(",");
            List<Card> selectedCards = new ArrayList<>();
            for (String index : indices) {
                selectedCards.add(currentPlayer.getHand().get(Integer.parseInt(index.trim())));
            }

            System.out.print("Announce the rank you're playing as (e.g., TWO, THREE, ...): ");
            String announced = scanner.nextLine().trim().toUpperCase();
            Card.Rank announcedRank = Card.Rank.valueOf(announced);

            currentPlayer.removeCards(selectedCards);
            centralPile.addCards(selectedCards);

            // Ask if any player wants to accuse
            boolean accusationMade = false;
            for (int i = 0; i < players.size(); i++) {
                if (i == currentPlayerIndex) continue;
                Player p = players.get(i);
                System.out.print(p.getName() + ", do you want to say 'Menteur!'? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    accusationMade = true;
                    boolean lied = selectedCards.stream().anyMatch(c -> c.getRank() != announcedRank);
                    if (lied) {
                        System.out.println(currentPlayer.getName() + " lied! They take the pile.");
                        currentPlayer.addCards(centralPile.takePile());
                    } else {
                        System.out.println(p.getName() + " was wrong! They take the pile.");
                        p.addCards(centralPile.takePile());
                    }
                    break;
                }
            }

            // Update current rank (regardless of lie)
            currentAnnouncedRank = nextRank(currentAnnouncedRank);
            if (!accusationMade) {
                System.out.println("No accusation. Game continues.");
            }

            if (currentPlayer.hasNoCards()) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        scanner.close();
    }

    private Card.Rank nextRank(Card.Rank current) {
        int nextOrdinal = (current.ordinal() + 1) % Card.Rank.values().length;
        return Card.Rank.values()[nextOrdinal];
    }
}

