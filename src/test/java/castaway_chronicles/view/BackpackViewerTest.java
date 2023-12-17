package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.TextDisplay;
import castaway_chronicles.view.game.BackpackViewer;
import castaway_chronicles.view.game.TextBoxViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class BackpackViewerTest {
    private GUI guiMock;
    private Backpack backpackMock;
    private BackpackViewer backpackViewer;
    private TextBoxViewer textBoxViewerMock;
    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
        backpackMock = Mockito.mock(Backpack.class);
        textBoxViewerMock = Mockito.mock(TextBoxViewer.class);
        backpackViewer = new BackpackViewer();
        Mockito.when(backpackMock.getVisibleInteractables()).thenReturn(List.of(new Item(0,0,0,0, "people"), new Icon(10,10, 10, 10, "forestRock")));
        Mockito.when(backpackMock.getBackground()).thenReturn(new Background(10,10,10, 10, "Menu", false));
    }
    @Test
    void locationViewer() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(backpackMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);

        backpackViewer.draw(backpackMock, guiMock);

        Mockito.verify(guiMock).drawImage(new Position(10,10), "Menu");
        Mockito.verify(guiMock).drawImage(new Position(0,0), "people");
        Mockito.verify(guiMock).drawImage(new Position(10,10), "forestRock");
        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }

    @Test
    void activeBox() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(backpackMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);
        backpackViewer.setTextBoxViewer(textBoxViewerMock);

        backpackViewer.draw(backpackMock, guiMock);
        Mockito.verify(textBoxViewerMock, Mockito.times(1)).drawTextBox(guiMock, backpackMock);
    }

    @Test
    void activeChoice() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(backpackMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(true);
        backpackViewer.setTextBoxViewer(textBoxViewerMock);

        backpackViewer.draw(backpackMock, guiMock);

        Mockito.verify(textBoxViewerMock, Mockito.times(1)).drawTextBox(guiMock, backpackMock);
    }

    @Test
    void resizeTerminal() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(backpackMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(true);

        backpackViewer.draw(backpackMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).resizeTerminal();
    }
}
