package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics graphics;
    private AWTTerminalFrame terminalMock;

    @BeforeEach
    void setUp() throws IOException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        terminalMock = Mockito.mock(AWTTerminalFrame.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(terminalMock, screen);
    }

    @Test
    void drawImage() throws IOException {
        Position positionMock = Mockito.mock(Position.class);
        HashMap<String, Sprite> imagesTest = new HashMap<>();
        Sprite spriteMock = Mockito.mock(Sprite.class);
        imagesTest.put("1", spriteMock);
        LanternaGUI guitest = new LanternaGUI(terminalMock, screen) {
          @Override
          public HashMap<String, Sprite> getImages() {
              return imagesTest;
          }
        };
        guitest.drawImage(positionMock, "1");
        Mockito.verify(spriteMock).drawSprite(positionMock, graphics);
    }

    @Test
    void drawText() {
        gui.drawText(new Position(0,0),100,"Lorem ipsum ,.!?",false);

        TextCharacter c = new TextCharacter(' ', new TextColor.RGB(0, 0, 0), new TextColor.RGB(0, 0, 0));
        gui.drawText(new Position(0,0),67,"Lorem ipsum ,.!? j",true);
        gui.drawText(new Position(0,0),70,"Lorem ipsum ,.!? jjjjj", false);

        Mockito.verify(graphics, Mockito.times(3)).setCharacter(63,6, c);
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(1,10, c);
        Mockito.verify(graphics, Mockito.times(4)).setForegroundColor(new TextColor.RGB(255,255,255));
        Mockito.verify(graphics, Mockito.times(1)).drawLine(new TerminalPosition(28, 8), new TerminalPosition(53, 8), '_');
    }

    @Test
    void drawLine() {
        gui.drawLine(new Position(1, 1),30);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255,255,255));
        Mockito.verify(graphics, Mockito.times(1)).drawLine(new TerminalPosition(1, 1), new TerminalPosition(31, 1), '_');
    }

    @Test
    void refresh() throws IOException {
        gui.refresh();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void clear() {
        gui.clear();
        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    void close() throws IOException {
        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    void resizeTerminalBig() {
        Mockito.when(terminalMock.getWidth()).thenReturn(10);
        Mockito.when(screen.doResizeIfNecessary()).thenReturn(null).thenReturn(null).thenReturn(new TerminalSize(10,10));
        gui.setBigger(false);

        gui.resizeTerminal();

        Mockito.verify(screen, Mockito.times(3)).doResizeIfNecessary();
        Mockito.verify(terminalMock).setSize(10, 765);
        assertTrue(gui.isBigger());
    }

    @Test
    void resizeTerminalSmall() {
        Mockito.when(terminalMock.getWidth()).thenReturn(10);
        Mockito.when(screen.doResizeIfNecessary()).thenReturn(null).thenReturn(null).thenReturn(new TerminalSize(10,10));
        gui.setBigger(true);

        gui.resizeTerminal();

        Mockito.verify(screen, Mockito.times(3)).doResizeIfNecessary();
        Mockito.verify(terminalMock).setSize(10, 637);
        assertFalse(gui.isBigger());
    }

    @Test
    void getNextAction() {
        KeyEvent keyEventMock = Mockito.mock(KeyEvent.class);
        gui.setAction(keyEventMock);
        InputEvent returnAction = gui.getAction();

        assertEquals(gui.getNextAction(), returnAction);
        assertNull(gui.getAction());
    }

    @Test
    void imagesLoaded() throws IOException {
        HashMap<String, Sprite> imagesTest = new HashMap<>();
        Sprite spriteMock = Mockito.mock(Sprite.class);
        imagesTest.put("1", spriteMock);
        LanternaGUI guitest = new LanternaGUI(terminalMock, screen) {
            @Override
            public HashMap<String, Sprite> getImages() {
                return imagesTest;
            }
        };

        assertTrue(guitest.imageIsLoaded("1"));
        assertFalse(guitest.imageIsLoaded("2"));
        assertEquals(HashMap.class, gui.getImages().getClass());
    }
}

