import javafx.concurrent.Task;

public class DataLoadTask extends Task<SaveData> {

    private AppData appData;

    public DataLoadTask(AppData appData) {
        this.appData = appData;
    }

    @Override
    protected SaveData call() throws Exception {
        if (this.appData.signIn()) {
            // Get the Save Data. Unpackage using SaveData class.
            SaveData saveData = new SaveData(this.appData.downloadData());
            return saveData;
        } else {
            return null;
        }
    }
}