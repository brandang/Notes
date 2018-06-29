/**
 * Interface that represent the backend code for the program. Used to allow the GUI to communicate
 * with the backend.
 */
public interface ProgramBackend extends AbstractBackend {

    /**
     * User has clicked on the clear button.
     */
    void clearButtonPressed();

    /**
     * User has clicked on the close button.
     */
    void closeButtonPressed();

}
