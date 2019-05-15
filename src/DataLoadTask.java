import javafx.concurrent.Task;

/**
 * Task that is responsible for downloading data.
 */
public class DataLoadTask extends Task<SaveData> {

    private AppData appData;

    /**
     * Initializes a new DataLoadTask that downloads the given data using the specified AppData object.
     * @param appData The AppData instance to use to upload.
     */
    public DataLoadTask(AppData appData) {
        this.appData = appData;
    }

    @Override
    protected SaveData call() {
        if (this.appData.signIn()) {
            // Get the Save Data. Unpackage using SaveData class.
            SaveData saveData = new SaveData(this.appData.downloadData());
            return saveData;
        } else {
            return null;
        }
    }
}