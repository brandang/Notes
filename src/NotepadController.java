
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
     * Loads the save data from Google Drive and put it on the GUI.
     */
    public void loadData() {
        // Sign in successful.
        if (this.signIn()) {
            // Get the Save Data. Unpackage using SaveData class.
            SaveData saveData = new SaveData(this.appData.downloadData());
            int fontSize = saveData.getFontSize();
            String text = saveData.getText();
            // Update GUI.
            this.frontend.setTextFontSize(fontSize);
            this.frontend.setText(text);
            this.frontend.sendMessage(Constants.LOADED_DATA_MSG);
        }
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
            // Save data and send message when complete.
            this.saveData(data);
            this.frontend.sendMessage(Constants.SAVED_DATA_MSG);
        }
    }
}