import javafx.scene.Scene;

/**
 * Interface that represents the frontend of the program. Allows the backend to tell the frontend what to display.
 */
public interface ProgramFrontend {

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

    /**
     * Returns the String text that the user typed into the TextArea.
     * @return The text.
     */
    String getText();

    /**
     * Returns the size of the font of the TextArea.
     * @return The size of the font.
     */
    int getTextFontSize();

}
