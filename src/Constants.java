import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * A class that stores constants for the program.
 */
public class Constants {

    // The name of the Window.
    final public static String WINDOW_TITLE = "Notes";

    // Filepath for client_secrets file.
    final public static String CLIENT_SECRETS_FILEPATH = "datastore/client_secrets.json";

    // Name for save data file.
    final public static String SAVE_FILE_NAME = "save_data";

    // Type for save data file.
    final public static String SAVE_FILE_TYPE = "text/txt";

    // AppDataFolder Scope.
    final public static String APPDATAFOLDER = "appdatafolder";

    // Client Secrets data.
    final public static String CLIENT_SECRETS = "\"{\\\"installed\\\":{\\\"client_id\\\":\\\"579441411049-3bqkmmem" +
            "0ds4ek3r4ob55nciet7bdt7b.apps.googleusercontent.com\\\",\\\"project_id\\\":\\\"application-data-test\\\"" +
            ",\\\"auth_uri\\\":\\\"https://accounts.google.com/o/oauth2/auth\\\",\\\"token_uri\\\":\\\"https://ac" +
            "counts.google.com/o/oauth2/token\\\",\\\"auth_provider_x509_cert_url\\\":\\\"https://www.googleapis.com/" +
            "oauth2/v1/certs\\\",\\\"client_secret\\\":\\\"Pyq2ArAEO4hqcnraEVbh6fia\\\",\\\"redirect_uris\\\":[\\\"u" +
            "rn:ietf:wg:oauth:2.0:oob\\\",\\\"http://localhost\\\"]}}";

    // Whether or not to upload data in chunks.
    final public static boolean USE_DIRECT_UPLOAD = true;

    // Message to display for Exception.
    final public static String CREDENTIAL_EXCEPTION_MSG = "Cannot access Google Drive. Credential either invalid " +
            "or permission not attained yet.";



}