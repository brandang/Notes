import javafx.scene.Scene;

/**
 * Interface that represents the frontend of the program. Allows the backend to tell the frontend what to display.
 */
public interface ProgramFrontend extends AbstractFrontend {

    /**
     * Returns the Scene that this Front-End represents.
     * @return The Scene object.
     */
    Scene getScene();

}
