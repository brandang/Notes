import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * The main GUI for the program.
 */
public class NotepadGUI implements ProgramFrontend {

    // The background of the GUI.
    private BorderPane background;

    // Menu bar.
    private HBox menuBar;

    /**
     * Create a new GUI.
     */
    public NotepadGUI() {
        this.setup();
    }

    /**
     * Sets up the initial contents of the GUI.
     */
    private void setup() {
        this.background = new BorderPane();
        this.background.setStyle(Constants.BACKGROUND_STYLE);

        this.menuBar = new HBox();
        this.menuBar.setStyle(Constants.MENU_STYLE);
        this.menuBar.setPrefHeight(Constants.MENU_HEIGHT);

        this.background.setTop(this.menuBar);
    }

    @Override
    public Scene getScene() {
        return new Scene(this.background, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setBackend(AbstractBackend backend) {

    }
}