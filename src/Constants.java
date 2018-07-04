import java.io.File;

/**
 * A class that stores constants for the program.
 */
public class Constants {

    // The name of the Window.
    final public static String WINDOW_TITLE = "Notes";

    // The directory to store Credential data.
    final public static File DATA_STORE_DIR = new java.io.File("datastore");

    // Filepath for client_secrets file.
    final public static String CLIENT_SECRETS_FILEPATH = "datastore/client_secrets.json";

    // Name for save data file.
    final public static String DATA_FILE_NAME = "save_data";
} 