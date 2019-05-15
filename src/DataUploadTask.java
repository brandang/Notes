import javafx.concurrent.Task;

/**
 * Task that is responsible for uploading data.
 */
public class DataUploadTask extends Task<AppData.Results> {

    private String uploadData;
    private AppData appData;

    /**
     * Initializes a new DataUploadTask that uploads the given data using the specified AppData object.
     * @param uploadData The data to upload.
     * @param appData The AppData instance to use to upload.
     */
    public DataUploadTask(String uploadData, AppData appData) {
        this.uploadData = uploadData;
        this.appData = appData;
    }

    @Override
    protected AppData.Results call() {
        if (this.appData.signIn()) {
            return this.appData.uploadData(this.uploadData);
        } else {
            return AppData.Results.FAILED;
        }
    }
}