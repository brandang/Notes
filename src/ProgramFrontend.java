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
     * Sets the text to display in the TextArea.
     * @param text The text.
     */
    void setText(String text);

    /**
     * Returns the String text that the user typed into the TextArea.
     * @return The text.
     */
    String getText();

    /**
     * Sets the size of the text for the TextArea.
     * @param size The new font size.
     */
    void setTextFontSize(int size);

    /**
     * Returns the size of the font of the TextArea.
     * @return The size of the font.
     */
    int getTextFontSize();

}
