import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * Loads the save data from Google Drive and put it on the GUI.
     */
    public void loadData() {
        DataLoadTask task = new DataLoadTask(this.appData);
        task.setOnSucceeded(event -> {
            SaveData saveData = task.getValue();
            if (saveData != null && saveData.isValid()) {
                int fontSize = saveData.getFontSize();
                
                // A temporary way to load the data.
                ArrayList<ItemData> data = saveData.getData();
                String text = "";
                for (int i = 0; i < data.size(); i ++) {
                    text += data.get(i).getData();
                    if (i < data.size() - 1)
                        text += "\n";
                }

                // Update the GUI.
                this.frontend.setTextFontSize(fontSize);
                this.frontend.setText(text);
                this.frontend.sendMessage(Constants.LOADED_DATA_MSG);
                this.frontend.finishLoading();
            }
            // Failed to load the data.
            else {
                // Update the GUI.
                this.frontend.setTextFontSize((int) Constants.DEFAULT_FONT_SIZE);
                this.frontend.setText("");
                this.frontend.sendMessage(Constants.CANT_LOAD_DATA);
                this.frontend.finishLoading();
            }
        });
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(task);
        service.shutdown();
    }

    /**
     * Attempt to save data on to Google Drive.
     * @param data The data to save.
     */
    private void saveData(SaveData data) {
        // A temporary way to upload the data.
        ArrayList<ItemData> list = data.getData();
        DataUploadTask task = new DataUploadTask(list.get(0).getData(), this.appData);

        // Display messages whenever save was successful or unsuccessful.
        task.setOnSucceeded((succeededEvent) -> {
            AppData.Results results = task.getValue();
            if (results == AppData.Results.SUCCESS) {
                this.frontend.sendMessage(Constants.SAVED_DATA_MSG);
            } else if (results == AppData.Results.FAILED) {
                this.frontend.sendMessage(Constants.SAVE_DATA_FAILED_MSG);
            }
            // Process finished.
            this.frontend.finishLoading();
        });
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(task);
        service.shutdown();
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
        this.saveButtonPressed();
    }

    @Override
    public void saveButtonPressed() {

        // Encapsulate the Save Data.
        SaveData data = this.frontend.getSaveData();
        // Save data and send message when complete.
        this.saveData(data);
    }

    @Override
    public void syncButtonPressed() {
        this.loadData();
    }
}