package castaway_chronicles.controller.game.locationControllers;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.game.Commands.Command;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class HandController implements ControllerState {
    private final GameController gameController;
    private String toGive = "";
    public HandController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setToGive(String toGive) {
        this.toGive = toGive;
    }

    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException {
        if (!toGive.isEmpty()) {
            for (Interactable e : gameController.getModel().getCurrentLocation().getVisibleInteractables()) {
                if (e instanceof NPC && e.contains(position) && e.getName().equalsIgnoreCase(toGive)) {
                    CommandInvoker invoker = (CommandInvoker) gameController.getCommandInvoker();
                    Command effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getTextDisplay().getInteractable().getEffects(), application);
                    invoker.setCommand(effects);
                    invoker.execute();
                    gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                    if (gameController.getModel().getTextDisplay().isActiveTextBox()) {
                        gameController.setControllerState(gameController.getDialogController());
                    } else gameController.setControllerState(gameController.getLocationController());
                    return;
                }
            }
        }
        ((ItemBackpack)gameController.getModel().getTextDisplay().getInteractable()).setInHand(true);
        gameController.getModel().getTextDisplay().activateTextBox();
        gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
        gameController.setControllerState(gameController.getNarratorController());
    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        if (key == KeyEvent.VK_ESCAPE) {
            gameController.getModel().setCurrentScene(Game.SCENE.BACKPACK);
            gameController.setControllerState(gameController.getBackpackController());
            gameController.getCommandInvoker().execute();
        }
    }
}
