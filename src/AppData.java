import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.*;
import java.util.Collections;

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

    // Credentials to access user data.
    private Credential credentials;

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
            // Store Credential data in memory.
            this.dataStoreFactory = new MemoryDataStoreFactory();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Ask the user permission to access the appdatafolder on Google Drive. Must call this method before using
     * AppData class.
     */
    public void acquireCredentials() throws IOException {
        // Load client secrets from Constants.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(this.jsonFactory,
                new InputStreamReader(new ByteArrayInputStream(Constants.CLIENT_SECRETS.getBytes())));

        // Set up authorization code flow.
        GoogleAuthorizationCodeFlow.Builder builder = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, this.jsonFactory, clientSecrets,
            // Access scopes, where to store credentials, and whether to always ask user for permissions.
            Collections.singleton(DriveScopes.DRIVE_APPDATA));
        builder.setDataStoreFactory(dataStoreFactory).setApprovalPrompt("force");
        GoogleAuthorizationCodeFlow flow = builder.build();

        // Authorize and get credentials.
        this.credentials = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    /**
     * Determines whether or not the Credentials are currently valid to access appdatafolder. Raises
     * CredentialInvalidException if not, does nothing if it is valid.
     */
    private void checkCredentials() {
        if (this.credentials == null) {
            throw new CredentialInvalidException();
        }
    }

    /**
     * Returns the save file. Returns null if the file does not exist or if there was an error accessing the file.
     * @return The file.
     */
    private File getSaveFile(){
        // File searcher.
        Drive.Files.List fileSearch;
        try {
            fileSearch = drive.files().list();
            // Where to search.
            fileSearch.setSpaces("appdatafolder");
            // Start search.
            FileList files = fileSearch.execute();

            // Look for the right file and return it.
            for (File file : files.getItems()) {
                if (file.getTitle().equals(Constants.DATA_FILE_NAME)) {
                    return file;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Returns the id of the save data file. Returns null if the file does not exist/inaccessible.
     * @return The id.
     */
    private String getSaveFileID() {
        File saveFile = this.getSaveFile();
        if (saveFile == null) {
            return null;
        } else {
            return saveFile.getId();
        }
    }

    /**
     * Returns the download URL of the save data file. Returns null if the file does not exist/inaccessible.
     * @return The download URL.
     */
    private String getSaveFileURL() {
        File saveFile = this.getSaveFile();
        if (saveFile == null) {
            return null;
        } else {
            return saveFile.getDownloadUrl();
        }
    }

    /**
     * Downloads and returns the app data.
     * @return The app data. Returns empty String if it does not exist.
     */
    public String downloadData() {
        this.checkCredentials();
        // Default value to return.
        String data = "";
        String downloadUrl = this.getSaveFileURL();

        // Make sure downloadURL is valid.
        if (downloadUrl == null) {
            return data;
        }

        // Download file to memory.
        OutputStream output = new ByteArrayOutputStream();
        MediaHttpDownloader downloader =
                new MediaHttpDownloader(this.httpTransport, this.drive.getRequestFactory().getInitializer());
        downloader.setDirectDownloadEnabled(false);
        try {
            downloader.download(new GenericUrl(downloadUrl), output);
        } catch (IOException e) {
            e.printStackTrace();
            return data;
        }

        // Convert to String and return.
        data = output.toString();
        return data;
    }

    /**
     * Uploads the app data. Overwrites the current data file, or creates a new one if it does not exist.
     * @param data The data to upload.
     */
    public void uploadData(String data) {

    }

    /**
     * Update the data file.
     * @param data
     */
    public void updateData(String data) {

    }

    /**
     * Creates a new data file and uploads it.
     */
    public void createAndUpload() {}

    /**
     * Exception that gets created whenever user attempts to use AppData class to access/modify files without sufficient
     * permission for Google Drive. Prints out error message upon creation.
     */
    private class CredentialInvalidException extends RuntimeException {

        /**
         * Creates a new CredentialInvalidException and prints out error message.
         */
        private CredentialInvalidException() {
            super(Constants.CREDENTIAL_EXCEPTION_MSG);
        }
    }
}