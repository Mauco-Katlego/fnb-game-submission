package za.co.fnb.bpmn.test.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import za.co.fnb.bpmn.test.service.model.MancalaGame;
import za.co.fnb.bpmn.test.service.service.MancalaGameService;

@Component
public class GameRunner implements CommandLineRunner {

    @Autowired
    private MancalaGameService mancalaGameService;

    @Override
    public void run(String... args) throws Exception {
        MancalaGame game = new MancalaGame();

        mancalaGameService.playGame(game);
    }

}