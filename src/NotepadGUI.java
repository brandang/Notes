import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * The main GUI for the program.
 */
public class NotepadGUI implements ProgramFrontend {

    // The background of the GUI.
    private BorderPane background;

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
    }

    @Override
    public Scene getScene() {
        return new Scene(this.background, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setBackend(AbstractBackend backend) {

    }
}