package za.co.fnb.bpmn.test.service.service;

import org.springframework.stereotype.Service;
import za.co.fnb.bpmn.test.service.model.MancalaGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class MancalaGameService {

    public void playGame(MancalaGame game) throws InterruptedException {
        game.reset();

        // Game loop
        while (!game.isOver()) {
            game.printBoard();
            if (game.getTurn() == MancalaGame.Player.One) {
                int position = readValue();
                game.markBoard(position);
            } else {
                int position = choosePositionForComputer(game);
                game.markBoard(position);
            }

            // Switch turns
            game.switchTurn();
        }

        // Print game results
        MancalaGame.Player winner = game.getWinner();
        game.printBoard();
        game.printWin(winner);
    }

    public void makeMove(MancalaGame game, int position) {
        // Make a move in the game
        game.markBoard(position);
    }

    private int readValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player One, choose a position (1-6): ");
        return scanner.nextInt();
    }

    private int choosePositionForComputer(MancalaGame game) {
        // Get the valid positions for the computer
        List<Integer> validPositions = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (game.getBoard()[i] != 0) {
                validPositions.add(i + 1);  // Positions are 1-based, so we add 1
            }
        }

        // Choose a random position from the valid positions
        if (!validPositions.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(validPositions.size());
            return validPositions.get(randomIndex);
        } else {
            throw new IllegalStateException("No valid positions available for the computer to make a move.");
        }
    }
}
