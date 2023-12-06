package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.HashMap;

public class Backpack extends Scene {
    private BackpackSelection backpackSelection = new BackpackSelection();
    public Backpack(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables) {
        super(background, interactables, visibleInteractables);
    }

    public BackpackSelection getBackpackSelection() {
        return backpackSelection;
    }
}
