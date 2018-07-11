import javafx.scene.Scene;

/**
 * The main GUI for the program.
 */
public class NotepadGUI implements ProgramFrontend {

    /**
     * Create a new GUI.
     */
    public NotepadGUI() {
        this.setup();
    }

    private void setup() {

    }

    @Override
    public Scene getScene() {
        return null;
    }

    @Override
    public void setBackend(AbstractBackend backend) {

    }
}