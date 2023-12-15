package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.ControllerStates.NarratorController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.TextDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class NarratorControllerTest {
    private Game game;
    private GameController gameController;
    private NarratorController narratorController;


    @BeforeEach
    void init() {
        game = Mockito.mock(Game.class);
        gameController = new GameController(game);
        narratorController = (NarratorController) gameController.getNarratorController();
        gameController.setControllerState(narratorController);
    }
    @Test
    public void select() throws IOException, InterruptedException {
        Location currentLocation = Mockito.mock(Location.class);
        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocation);
        TextDisplay backpackAnswer = Mockito.mock(TextDisplay.class);
        Mockito.when(currentLocation.getTextDisplay()).thenReturn(backpackAnswer);

        narratorController.select(Mockito.mock(Application.class));
        Mockito.verify(backpackAnswer,Mockito.times(1)).closeTextBox();
        assertEquals(gameController.getCurrent(),gameController.getLocationController());
    }
}
