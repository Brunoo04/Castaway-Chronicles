package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameViewer extends Viewer<Game> {
    public GameViewer(Game model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        switch (getModel().getScene()) {
            case PAUSE:
                new PauseMenuViewer(getModel().getPauseMenu()).draw(gui);
                break;
            case BACKPACK:
                new BackpackViewer(getModel().getBackpack()).draw(gui, new TextBoxViewer(getModel().getBackpack().getBackpackItemInfo().getElement()));
                break;
            case MAP:
                new MapViewer(getModel().getMap()).draw(gui);
                break;
            case LOCATION:
                new LocationViewer(getModel().getCurrentLocation()).draw(gui, new TextBoxViewer(getModel().getCurrentLocation().getTextDisplay().getElement()));
                break;
        }
    }
}
