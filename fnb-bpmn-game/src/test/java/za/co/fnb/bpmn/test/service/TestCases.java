package za.co.fnb.bpmn.test.service;

import org.junit.jupiter.api.Test;
import za.co.fnb.bpmn.test.service.model.MancalaGame;
import za.co.fnb.bpmn.test.service.service.MancalaGameService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MancalaGameServiceTest {

//    @Test
    void playGame() throws InterruptedException {
        // Create a mock MancalaGame instance
        MancalaGame mockGame = mock(MancalaGame.class);
        MancalaGameService gameService = new MancalaGameService();
c        // Call the playGame method
        assertThrows(InterruptedException.class, () -> gameService.playGame(mockGame));
    }

//    @Test
    void makeMove() {
        // Create a mock MancalaGame instance
        MancalaGame mockGame = mock(MancalaGame.class);
        MancalaGameService gameService = new MancalaGameService();

        // Call the makeMove method
        gameService.makeMove(mockGame, 3);

        // Verify that the markBoard method is called with the correct position
        verify(mockGame).markBoard(3);
    }
}
