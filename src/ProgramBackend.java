/**
 * Interface that represent the backend code for the program. Used to allow the GUI to communicate
 * with the backend.
 */
public interface ProgramBackend {

    /**
     * Set the Frontend that this Frontend must communicate with.
     * @param frontend The Frontend.
     */
    void setFrontend(ProgramFrontend frontend);

    /**
     * User has clicked on the clear button.
     */
    void clearButtonPressed();

    /**
     * User has clicked on the close button.
     */
    void closeButtonPressed();

    /**
     * User has clicked on the 'Save' button.
     */
    void saveButtonPressed();

}
