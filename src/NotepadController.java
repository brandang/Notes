
import java.io.IOException;

/**
 * Controller for Notepad and the GUI.
 */
public class NotepadController implements ProgramBackend {

    // Class that can upload and download data from Drive.
    private AppData appData;

    // The Frontend that we must interact with.
    private ProgramFrontend frontend;

    /**
     * Creates a NotepadController.
     */
    public NotepadController() {
        this.appData = new AppData();
    }

    /**
     * Sign in to Google Drive.
     * @return Whether or not sign in was successful.
     */
    private boolean signIn() {
        try {
            this.appData.acquireCredentials();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Attempt to save data on to Google Drive.
     * @param data The data to save.
     */
    public void saveData(String data) {
        this.appData.uploadData(data);
    }

    @Override
    public void setFrontend(ProgramFrontend frontend) {
        this.frontend = frontend;
    }

    @Override
    public void clearButtonPressed() {

    }

    @Override
    public void closeButtonPressed() {

    }
}