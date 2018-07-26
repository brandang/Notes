
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

    // Initial Window dimensions.
    final public static int WINDOW_WIDTH = 300;

    final public static int WINDOW_HEIGHT = 450;

    // The style of the background.
    final public static String BACKGROUND_STYLE = "-fx-background-color: #464646";
//    final public static String BACKGROUND_STYLE = "-fx-background-color: red";

    // The style for the top menu bar.
    final public static String MENU_STYLE = "-fx-background-color: #3f3f3f";

    // The height of the menu.
    final public static double MENU_HEIGHT = 50;

    // Style for separator for the menu.
    final public static String MENU_SEPARATOR_STYLE = "-fx-border-style: solid; -fx-border-width: 0 0 1 0;";

    // Style sheet for Text Area file path.
    final public static String TEXTAREA_STYLE_PATH = "textarea.css";

    // Style sheet for the custom Scroll Bar in the Text Area filepath.
    final public static String SCROLLBAR_STYLE_PATH = "scrollbar.css";

    // Style for Text Area.
    final public static String TEXTAREA_STYLE = "-fx-control-inner-background: #464646; -fx-border-style: none none " +
            "none none; -fx-border-width: 0 0 0 0; -fx-border-color: transparent; -fx-background-insets: 0 0 0 0; " +
            "-fx-background-color: transparent, transparent, transparent, transparent; -fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent; -fx-background-radius: 0; -fx-control-inner-background-radius: 0;";

}