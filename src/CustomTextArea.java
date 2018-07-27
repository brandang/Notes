import javafx.scene.control.ContextMenu;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import java.awt.*;

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
    public void setup() {
        // Setup style.
        this.setStyle(Constants.TEXTAREA_STYLE);
        this.getStylesheets().add(CustomTextArea.class.getResource(Constants.TEXTAREA_STYLE_PATH).toExternalForm());
        // Disables horizontal scrolling.
        this.setWrapText(true);
    }
} 