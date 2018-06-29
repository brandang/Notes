import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;

import java.io.IOException;

/**
 * Class that is responsible for uploading and downloading the program data from Google Drive`s appdatafolder.
 * Also contains many methods to manage files stored in the appdatafolder.
 */
public class AppData {

    // Drive API client.
    private Drive drive;

    // JSON Factory used to load client secrets.
    private JacksonFactory jsonFactory;

    // HTTP Transport.
    private HttpTransport httpTransport;

    // DataStoreFactory, used to store authentication data.
    private DataStoreFactory dataStoreFactory;

    /**
     * Creates a new AppData instance, which can be used to download and upload application data.
     */
    public AppData() {

        // Disable annoying warnings.
        final java.util.logging.Logger buggyLogger = java.util.logging.Logger.getLogger(FileDataStoreFactory.class.getName());
        buggyLogger.setLevel(java.util.logging.Level.SEVERE);

        this.jsonFactory = JacksonFactory.getDefaultInstance();

        try {
            this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            this.dataStoreFactory = new FileDataStoreFactory();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(1);
    }

    /**
     * Ask the user permission to access the appdatafolder on Google Drive.
     */
    public void askUserPermission() {

    }

    /**
     * Downloads and returns the app data.
     * @return The app data.
     */
    public String downloadData() {
        return null;
    }

    /**
     * Uploads the app data.
     * @param data The data to upload.
     */
    public void uploadData(String data) {

    }
}