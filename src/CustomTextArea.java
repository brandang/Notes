import javafx.beans.binding.Bindings;
import javafx.scene.control.TextArea;

/**
 * A custom TextArea with a customized look and feel.
 */
public class CustomTextArea extends TextArea {

    // The size of the text.
    private double fontSize;

    // The font family for the text.
    private String fontFamily;

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

        // Setup default font.
        this.fontSize = Constants.DEFAULT_FONT_SIZE;
        this.fontFamily = Constants.DEFAULT_FONT_FAMILY;
        this.updateFont();
    }

    /**
     * Updates the font for the text with the current font size and family.
     */
    private void updateFont() {
        // A JavaFx TextArea resizes its children (such as it's scrollbar) in relation to the pixel size of the font.
        // This means that each component will have it's size based on the 'em' unit, where 'em' is a CSS unit of
        // measurement referring to the default size of the font (usually, this is 12px). Whenever we set a font size
        // to, say, 14px, this changes the value of 'em', thereby changing everything else in the TextArea.
        // To remedy this, set Font size based on 'em'. If em == 12, then 24 == 2em, and 18 == 1.5em, etc.
        this.styleProperty().bind(Bindings.concat("-fx-font-size: ", this.fontSize /
                Constants.DEFAULT_FONT_SIZE, "em;" + "-fx-text-fill: #d6d6d6;" + "-fx-font-family: " +
                this.fontFamily));
    }

    /**
     * Set the new size of the font.
     * @param size The size.
     */
    public void setFontSize(double size) {
        this.fontSize = size;
        this.updateFont();
    }

    /**
     * Set the new family for the font to display.
     * @param family The font family.
     */
    public void setFontFamily(String family) {
        this.fontFamily = family;
        this.updateFont();
    }

    /**
     * Returns the size of the font.
     * @return The size of the font.
     */
    public double getFontSize() {
        return this.fontSize;
    }
}