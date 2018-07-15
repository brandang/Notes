import javafx.scene.control.TextArea;

/**
 * A custom TextArea with a customized look and feel.
 */
public class CustomTextArea extends TextArea {

    /**
     * Creates a CustomTextArea with no initial content.
     */
    public CustomTextArea() {
        super();
        this.setup();
    }

    /**
     * Creates a CustomTextArea with initial content.
     * @param text The text to display.
     */
    public CustomTextArea(String text) {
        super(text);
        this.setup();
    }

    /**
     * Sets up the look and feel of the CustomTextArea.
     */
    private void setup() {
        this.setStyle(Constants.TEXTAREA_STYLE);
        this.getStylesheets().add(CustomTextArea.class.getResource(Constants.TEXTAREA_STYLE_PATH).toExternalForm());
//        this.scroll
    }
} 