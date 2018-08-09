import javafx.scene.Scene;

/**
 * Interface that represents the frontend of the program. Allows the backend to tell the frontend what to display.
 */
public interface ProgramFrontend extends AbstractFrontend {

    /**
     * Set the Backend that this Frontend must communicate with.
     * @param backend The Backend.
     */
    void setBackend(ProgramBackend backend);

    /**
     * Returns the Scene that this Front-End represents.
     * @return The Scene object.
     */
    Scene getScene();

}
