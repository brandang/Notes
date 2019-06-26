import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

/*
403 error: insufficient permissions. perhaps because need to datastorefactory to have physical file on computer.
 */

/**
 * Class that is responsible for uploading and downloading the program data from Google Drive`s appdatafolder.
 * Creates a connection.
 * Also contains many methods to manage files stored in the appdatafolder.
 */
public class AppData {

    // Results to let caller know whether actions were successful, canceled, or failed.
    public enum Results {
        SUCCESS,
        CANCELED,
        FAILED
    }

    // The Drive in which to extract and save data to.
    private Drive drive;

    // JSON Factory used to load client secrets.
    private JacksonFactory jsonFactory;

    // HTTP Transport.
    private HttpTransport httpTransport;

    // DataStoreFactory, used to store authentication data.
    private DataStoreFactory dataStoreFactory;

    // Credentials to access user data.
    private Credential credentials;

    // Only do one thing/task  at a time.
    private boolean busy;

    /**
     * Creates a new AppData instance, which can be used to download and upload application data.
     */
    public AppData() {

        // We are not busy yet.
        this.busy = false;

        // Disable annoying warnings.
        final java.util.logging.Logger buggyLogger = java.util.logging.Logger.getLogger(FileDataStoreFactory.class.getName());
        buggyLogger.setLevel(java.util.logging.Level.SEVERE);

        this.jsonFactory = JacksonFactory.getDefaultInstance();

        try {
            this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            // Store Credential data in a file.
            // this.dataStoreFactory = new MemoryDataStoreFactory();
            this.dataStoreFactory = new FileDataStoreFactory(new java.io.File(Constants.CREDENTIAL_SAVE_PATH));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Ask the user permission to access the appdatafolder on Google Drive. Must call this method before using
     * AppData class. However, only have to do this once when the program runs. After the first call, it signs in
     * automatically without need for user input.
     */
    public void acquireCredentials() throws IOException {
        // Load client secrets from Constants.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(this.jsonFactory,
                new InputStreamReader(new ByteArrayInputStream(Constants.CLIENT_SECRETS.getBytes())));

        // Set up authorization code flow.
        GoogleAuthorizationCodeFlow.Builder builder = new GoogleAuthorizationCodeFlow.Builder(
            this.httpTransport, this.jsonFactory, clientSecrets,
            // Access scopes, where to store credentials, and whether to always ask user for permissions.
                Arrays.asList(DriveScopes.DRIVE_APPDATA, DriveScopes.DRIVE));
        builder.setDataStoreFactory(dataStoreFactory).setApprovalPrompt("force");
        GoogleAuthorizationCodeFlow flow = builder.build();

        // Authorize and get credentials.
        this.credentials = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        // Get the Drive to use to save and extract data.
        this.drive = new Drive.Builder(this.httpTransport, this.jsonFactory, this.credentials).setApplicationName(
                Constants.WINDOW_TITLE).build();
    }

    /**
     * Determines whether or not the Credentials are currently valid to access appdatafolder.
     * @return True for yes, false for no.
     */
    public boolean credentialsValid() {
        if (this.credentials == null) {
            return false;
        }
        return true;
    }

    /**
     * Determines whether or not the Credentials are currently valid to access appdatafolder. Raises
     * CredentialInvalidException if not, does nothing if it is valid.
     */
    private void checkCredentials() {
        if (this.credentialsValid() == false) {
            throw new CredentialInvalidException();
        }
    }

    /**
     * Sign in to Google Drive.
     * @return Whether or not sign in was successful.
     */
    public boolean signIn() {
        try {
            this.acquireCredentials();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Returns the save file. Returns null if the file does not exist. Throws exception if there was an error accessing
     * the file.
     * @return The file.
     */
    private File getSaveFile() {
        // File searcher.
        Drive.Files.List fileSearch;
        try {
            fileSearch = drive.files().list();
            // Where to search.
            fileSearch.setSpaces(Constants.APPDATAFOLDER);
            // Start search.
            FileList files = fileSearch.execute();

            // Look for the right file and return it.
            for (File file : files.getItems()) {
                if (file.getTitle().equals(Constants.SAVE_FILE_NAME)) {
                    return file;
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
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
            return saveFile.getId();
        }
    }

    /**
     * Determines whether or not the save file exists on the user`s Drive.
     * @return True for yes, false for no.
     */
    private boolean saveFileExists() {
        return this.getSaveFile() != null;
    }

    /**
     * Downloads and returns the app data. Throws exception if credentials are invalid.
     * @return The app data. Returns empty String if it does not exist.
     */
    public SaveData downloadData() {
        this.checkCredentials();
        String downloadUrl = this.getSaveFileURL();

        // Make sure downloadURL is valid.
        if (downloadUrl == null) {
            return null;
        }

        // Download file to memory.
        try {
            InputStream inputStream = this.drive.files().get(downloadUrl).executeMediaAsInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }

            Gson gson = new Gson();
            SaveData data = gson.fromJson(stringBuilder.toString(), SaveData.class);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Uploads the app data. Overwrites the current data file, or creates a new one if it does not exist. Throws
     * exception if credentials are invalid.
     * @param data The data to upload.
     * @return The Results: task was met with success, canceled, or failed.
     */
    public Results uploadData(String data) {
        if (this.busy) {
            return Results.CANCELED;
        } else {
            this.busy = true;
        }
        this.checkCredentials();
        try {
            if (this.saveFileExists()) {
                this.updateData(data);
            } else {
                this.createAndUpload(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Results.FAILED;
        }
        this.busy = false;
        return Results.SUCCESS;
    }

    /**
     * Update the data file.
     * @param data The data.
     */
    private void updateData(String data) throws IOException {

        File fileMetadata = new File();
        fileMetadata.setTitle(Constants.SAVE_FILE_NAME);
        // Where to place the file.
        ParentReference pr = new ParentReference();
        pr.setId(Constants.APPDATAFOLDER);
        fileMetadata.setParents(Collections.singletonList(pr));

        // Get the file from Drive.
        String fileID = this.getSaveFileID();

        // Save data in memory and upload.
        Gson gson = new Gson();
        String json = gson.toJson(data, SaveData.class);
        ByteArrayContent content = new ByteArrayContent(Constants.SAVE_FILE_TYPE, json.getBytes());
        Drive.Files.Update update = drive.files().update(fileID, fileMetadata, content);

        // Start uploading.
        update.execute();

        System.out.println("Updated file");
    }

    /**
     * Creates a new data file and uploads it.
     * @param data The data.
     */
    private void createAndUpload(String data) throws IOException {
        // Create Google Drive file.
        File fileMetadata = new File();
        fileMetadata.setTitle(Constants.SAVE_FILE_NAME);
        // Where to place the file.
        ParentReference pr = new ParentReference();
        pr.setId(Constants.APPDATAFOLDER);
        fileMetadata.setParents(Collections.singletonList(pr));

        // Store data in memory.
        ByteArrayContent content = new ByteArrayContent(Constants.SAVE_FILE_TYPE, data.getBytes());

        // Insert file into drive.
        Drive.Files.Insert insert = drive.files().insert(fileMetadata, content);
        // Get the media uploader.
        MediaHttpUploader uploader = insert.getMediaHttpUploader();

        // Set whether or not to upload entire thing at once, or in chunks.
        uploader.setDirectUploadEnabled(Constants.USE_DIRECT_UPLOAD);

        // Start uploading.
        insert.execute();

        System.out.println("Created file.");
    }

    /*******
    Methods used for debugging program.
     ******/

    /**
     * Deletes all of the files in the appDataFolder. Throws Exception if Credentials are invalid.
     */
    public void deleteFiles() {
        this.checkCredentials();
        this.printFileNames();

        try {
            // File searcher.
            Drive.Files.List fileSearch = drive.files().list();
            // Where to search.
            fileSearch.setSpaces("appDataFolder");
            // Start search.
            FileList files = fileSearch.execute();

            // Delete all files.
            for (File file : files.getItems()) {
                Drive.Files.Delete deleter = drive.files().delete(file.getId());
                deleter.execute();
            }
            this.printFileNames();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CredentialInvalidException();
        }
    }

    /**
     * Prints all of names of the files in the appDataFolder. Throws Exception if Credentials are invalid.
     */
    public void printFileNames() {
        this.checkCredentials();
        System.out.println("Files:");

        // File searcher.
        Drive.Files.List fileSearch;
        try {
            fileSearch = this.drive.files().list();
            // Where to search.
            fileSearch.setSpaces(Constants.APPDATAFOLDER);
            // Start search.
            FileList files = fileSearch.execute();

            // Look for the right file and return it.
            for (File file : files.getItems()) {
                System.out.println(file.getTitle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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