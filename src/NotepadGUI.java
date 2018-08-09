import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * The main GUI for the program.
 */
public class NotepadGUI implements ProgramFrontend {

    // The background of the GUI.
    private BorderPane background;

    // Menu bar.
    private HBox menuBar;

    // Container for the Menu bar, consists of

    // The text area.
    private CustomTextArea textArea;

    // Button to save data.
    private Button saveButton;

    // Button to decrease font size.
    private Button decreaseFontButton;

    // Button to increase font size.
    private Button increaseFontButton;

    // Button that acts as a label to indicate text size.
    private Button textSizeButton;

    /**
     * Create a new GUI.
     */
    public NotepadGUI() {
        this.setup();
        this.setupButtons();
    }

    /**
     * Sets up the initial contents of the GUI.
     */
    private void setup() {
        this.background = new BorderPane();
        this.background.getStylesheets().add(Constants.BACKGROUND_STYLE_PATH);
        // Need to do this since BorderPane does not have Control as an ancestor.
        this.background.getStyleClass().add("borderpane");

        // The save button.
        this.saveButton = new Button();
        this.saveButton.getStylesheets().add(Constants.BUTTON_STYLE_PATH);
        Image saveImage = new Image(getClass().getResourceAsStream("savebutton.png"));
        ImageView imageView = new ImageView(saveImage);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        this.saveButton.setGraphic(imageView);

        // The separator in the menu.
        Separator menuSeparator = new Separator();
        menuSeparator.setOrientation(Orientation.VERTICAL);
        menuSeparator.getStylesheets().add(Constants.SEPARATOR_STYLE_PATH);

        // Text sizes buttons.
        this.decreaseFontButton = new Button("−");
        this.decreaseFontButton.getStylesheets().add(Constants.BUTTON_STYLE_PATH);
        this.increaseFontButton = new Button("+");
        this.increaseFontButton.getStylesheets().add(Constants.BUTTON_STYLE_PATH);
        this.textSizeButton = new Button("12");
        this.textSizeButton.getStylesheets().add(Constants.TEXT_SIZE_LABEL_STYLE_PATH);

        // Menu at the top.
        this.menuBar = new HBox();
        this.menuBar.getStylesheets().add(Constants.MENU_STYLE_PATH);
        // Need to do this since HBox does not have Control as an ancestor.
        this.menuBar.getStyleClass().add("hbox");
        this.menuBar.setPrefHeight(Constants.MENU_HEIGHT);
        this.menuBar.getChildren().addAll(this.decreaseFontButton, this.textSizeButton, this.increaseFontButton,
                menuSeparator, this.saveButton);
        this.menuBar.setAlignment(Pos.CENTER_RIGHT);

        // The text area.
        this.textArea = new CustomTextArea("Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123Testing 123");

        // Add components to GUI.
        this.background.setTop(this.menuBar);
        this.background.setCenter(this.textArea);
    }

    /**
     * Adds appropriate listeners to Buttons.
     */
    private void setupButtons() {

        // Save button pressed.
        this.saveButton.setOnAction(event -> {

        });

        // Increase text size.
        this.increaseFontButton.setOnAction(event -> {
            this.textArea.setFontSize(this.textArea.getFontSize() + 1);
            this.textSizeButton.setText(Double.toString(this.textArea.getFontSize()));
        });

        // Decrease text size.
        this.decreaseFontButton.setOnAction(event -> {
            this.textArea.setFontSize(this.textArea.getFontSize() - 1);
            this.textSizeButton.setText(Double.toString(this.textArea.getFontSize()));
        });
    }

    @Override
    public void setBackend(ProgramBackend backend) {

    }

    @Override
    public Scene getScene() {
        return new Scene(this.background, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setBackend(AbstractBackend backend) {

    }
}