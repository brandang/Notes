import javafx.scene.text.Font;

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
    final public static String APPDATAFOLDER = "appDataFolder";

    // Where to save Credentials data.
    final public static String CREDENTIAL_SAVE_PATH = "save_data";

    // Client Secrets data.
    final public static String CLIENT_SECRETS = "{\"installed\":{\"client_id\":\"106704107629-38oibll0mfbrkgn9bgpbrnnkoto5qnvs.apps.googleusercontent.com\",\"project_id\":\"notes-209301\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://accounts.google.com/o/oauth2/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"tMzx-z1Sm8vaM8WkWWWt_8zy\",\"redirect_uris\":[\"urn:ietf:wg:oauth:2.0:oob\",\"http://localhost\"]}}";

    // Whether or not to upload data in chunks.
    final public static boolean USE_DIRECT_UPLOAD = true;

    // Path for save button icon.
    final public static String SAVE_ICON_PATH = "savebutton.png";

    // Path for the sync button icon.
    final public static String SYNC_ICON_PATH = "sync.png";

    // Path for application's icon.
    final public static String APP_ICON_PATH = "icon.png";

    // Message to display for Exception.
    final public static String CREDENTIAL_EXCEPTION_MSG = "Cannot access Google Drive. Credential either invalid " +
            "or permission not attained yet.";

    // Initial Window dimensions.
    final public static int WINDOW_WIDTH = 300;

    final public static int WINDOW_HEIGHT = 450;

    // The style sheet of the background.
    final public static String BACKGROUND_STYLE_PATH = "backgroundstyle.css";

    // Style sheet for Menu.
    final public static String MENU_STYLE_PATH = "menustyle.css";

    // The height of the menu.
    final public static double MENU_HEIGHT = 40;

    // Style sheet for Text Area file path.
    final public static String TEXTAREA_STYLE_PATH = "textareastyle.css";

    // Style for Text Area.
    final public static String TEXTAREA_STYLE = "-fx-control-inner-background: #464646; -fx-border-style: none none " +
            "none none; -fx-border-width: 0 0 0 0; -fx-border-color: transparent; -fx-background-insets: 0 0 0 0; " +
            "-fx-background-color: transparent, transparent, transparent, transparent; -fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent; -fx-background-radius: 0; -fx-control-inner-background-radius: 0;";

    // Default font size of this JavaFx release.
    final public static double DEFAULT_FONT_SIZE = Font.getDefault().getSize();

    // Default font family.
    final public static String DEFAULT_FONT_FAMILY = "Consolas";

    // Style sheet for Button file path.
    final public static String BUTTON_STYLE_PATH = "menubuttonstyle.css";

    // Style sheet for the Button that displays the size of the font in the menu.
    final public static String TEXT_SIZE_LABEL_STYLE_PATH = "textsizebuttonstyle.css";

    // Style sheet for menu separator.
    final public static String SEPARATOR_STYLE_PATH = "separatorstyle.css";

    // Style sheet for message pop up.
    final public static String INFO_POPUP_STYLE_PATH = "infopopupstyle.css";

    // The height of the message popup.
    final public static double POPUP_HEIGHT = 30;

    // The time that it takes for the animation of the popup to complete.
    final public static double POPUP_ANIMATION_TIME = 500;

    // How long to show the popup before hiding it again.
    final public static double POPUP_SHOW_TIME = 2500;

    // Message to show when loaded data.
    final public static String LOADED_DATA_MSG = "Loaded data";

    // Message to show when unable to load data.
    final public static String CANT_LOAD_DATA = "Error Loading Data, Check Internet Connection";

    // Message to show when save successful.
    final public static String SAVED_DATA_MSG = "Save Successful";

    // Message to show when saving was unsuccessful.
    final public static String SAVE_DATA_FAILED_MSG = "Error Saving, Check Internet Connection";
}