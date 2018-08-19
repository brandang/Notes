import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * The main GUI for the program.
 */
public class NotepadGUI implements ProgramFrontend {

    // The Backend that this GUI represents.
    private ProgramBackend backend;

    // The background of the GUI.
    private BorderPane background;

    // Menu bar.
    private HBox menuBar;

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

    // Label to display messages.
    private Label infoPopup;

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
        Image saveImage = new Image(getClass().getResourceAsStream(Constants.SAVE_ICON_PATH));
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
        this.textSizeButton = new Button(Integer.toString((int)Constants.DEFAULT_FONT_SIZE));
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

        // The message box at the bottom.
        this.infoPopup = new Label("Testing");
        // Make sure it expands all the way.
        this.infoPopup.setMaxWidth(Double.MAX_VALUE);
        this.infoPopup.setPrefHeight(Constants.POPUP_HEIGHT);
        this.infoPopup.getStylesheets().add(Constants.INFO_POPUP_STYLE_PATH);

        // StackPane allows us to overlay message over TextArea.
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(this.textArea, this.infoPopup);
        this.background.setCenter(stackPane);
        // Set alignment of the popup.
        StackPane.setAlignment(this.infoPopup, Pos.BOTTOM_CENTER);
        // Make sure it initially does not appear on screen.
        this.infoPopup.setTranslateY(Constants.POPUP_HEIGHT);
    }

    /**
     * Adds appropriate listeners to Buttons.
     */
    private void setupButtons() {

        // Save button pressed.
        this.saveButton.setOnAction(event -> {
            this.backend.saveButtonPressed();
        });

        // Increase text size.
        this.increaseFontButton.setOnAction(event -> {
            this.textArea.setFontSize(this.textArea.getFontSize() + 1);
            this.textSizeButton.setText(Integer.toString((int)this.textArea.getFontSize()));
        });

        // Decrease text size.
        this.decreaseFontButton.setOnAction(event -> {
            this.textArea.setFontSize(this.textArea.getFontSize() - 1);
            this.textSizeButton.setText(Integer.toString((int)this.textArea.getFontSize()));
        });
    }

    /**
     * Creates and returns Animation for showing popup.
     * @param message The message to display.
     * @return The Animation.
     */
    private Animation showAnimation(String message) {
        // Prepare animation.
        Animation expandPopup = new Transition() {
            {
                setCycleDuration(Duration.millis(Constants.POPUP_ANIMATION_TIME));
            }

            @Override
            protected void interpolate(double fraction) {
                NotepadGUI.this.infoPopup.setTranslateY((Constants.POPUP_HEIGHT * (-fraction)) + Constants.POPUP_HEIGHT);
            }
        };

        // Set message.
        NotepadGUI.this.infoPopup.setText(message);
        return expandPopup;
    }

    /**
     * Creates and returns Animation where popup stays and does nothing for a set amount of time.
     * @param waitTime How long in milliseconds to wait.
     * @return
     */
    private Animation waitAnimation(double waitTime) {
        // Prepare animation.
        Animation wait = new Transition() {
            {
                setCycleDuration(Duration.millis(waitTime));
            }

            @Override
            protected void interpolate(double fraction) {
                // Don't do anything; just a placeholder.
            }
        };
        return wait;
    }

    /**
     * Creates and returns an Animation where the popup disappears.
     * @return The Animation.
     */
    private Animation hideAnimation() {
        // Prepare animation.
        Animation closePopup = new Transition() {
            {
                setCycleDuration(Duration.millis(Constants.POPUP_ANIMATION_TIME));
            }

            @Override
            protected void interpolate(double fraction) {
                NotepadGUI.this.infoPopup.setTranslateY((Constants.POPUP_HEIGHT * (fraction)));
            }
        };

        return closePopup;
    }


    /**
     * Display a message on the InfoPopup.
     * @param message The message.
     */
    private void showMessage(String message) {
        Animation show = this.showAnimation(message);
        Animation wait = this.waitAnimation(Constants.POPUP_SHOW_TIME);
        Animation hide = this.hideAnimation();
        show.setOnFinished(event -> wait.play());
        wait.setOnFinished(event -> hide.play());
        show.play();
    }

    @Override
    public void sendMessage(String message) {
        this.showMessage(message);
    }

    @Override
    public void setBackend(ProgramBackend backend) {
        this.backend = backend;
    }

    @Override
    public Scene getScene() {
        return new Scene(this.background, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setText(String text) {
        this.textArea.setText(text);
    }

    @Override
    public String getText() {
        return this.textArea.getText();
    }

    @Override
    public void setTextFontSize(int size) {
        this.textArea.setFontSize(size);
        this.textSizeButton.setText(Integer.toString((int)this.textArea.getFontSize()));
    }

    @Override
    public int getTextFontSize() {
        return (int) this.textArea.getFontSize();
    }
}