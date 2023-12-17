package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.view.SceneViewer;
import castaway_chronicles.view.SelectionPanelViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class LocationViewer extends SceneViewer<Location> {
    TextBoxViewer textBoxViewer;
    public LocationViewer() {
        this.textBoxViewer = new TextBoxViewer(new SelectionPanelViewer(new Position(6,155), 0, 10));
    }

    public void setTextBoxViewer(TextBoxViewer textBoxViewer) {
        this.textBoxViewer = textBoxViewer;
    }

    @Override
    public void draw(Location model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
        if (model.getMainChar() != null) drawElement(gui, model.getMainChar());
        if (model.getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, model);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
}
