package com.mycompany.cheatergame;
import java.util.*;

public class CheaterGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.print("Enter number of players (2+): ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            players.add(new Player(scanner.nextLine()));
        }

        Game game = new Game(players);
        game.start();
    }
}

    

