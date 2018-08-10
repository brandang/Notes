
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
    private void saveData(SaveData data) {
        this.appData.uploadData(data.getSaveData());
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

    @Override
    public void saveButtonPressed() {

        // Sign in to Google was successful.
        if (this.signIn()) {
            // Encapsulate the Save Data.
            SaveData data = new SaveData(this.frontend.getText(), this.frontend.getTextFontSize());
            this.saveData(data);
        }
    }
}