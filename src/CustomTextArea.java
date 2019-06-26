import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * A custom TextArea with a customized look and feel.
 */
public class CustomTextArea extends TextArea {

    // The size of the text.
    private double fontSize;

    // The font family for the text.
    private String fontFamily;

    // The minimum text size.
    final private int MIN_TEXT_SIZE = 4;

    // The maximum text size.
    final private int MAX_TEXT_SIZE = 24;

    private TextChangeListener listener;

    private ChangeListener<String> changeListener;

    /**
     * Creates a CustomTextArea with no initial content.
     * @param listener The listener that gets notified when the text changes.
     */
    public CustomTextArea(TextChangeListener listener) {
        super();
        this.setup();
        this.setListener(listener);
    }

    /**
     * Creates a CustomTextArea with initial content.
     * @param text The text to display.
     * @param listener The listener that gets notified when the text changes.
     */
    public CustomTextArea(String text, TextChangeListener listener) {
        super(text);
        this.setup();
        this.setListener(listener);
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
     * Set the listener that should be notified when the text changes.
     * @param listener The listener.
     */
    public void setListener(final TextChangeListener listener) {
        // Remove previous listener.
        if (this.changeListener != null)
            this.textProperty().removeListener(this.changeListener);

        this.changeListener = (observable, oldValue, newValue) -> {
            listener.onTextChanged(newValue);
            CustomTextArea.this.listener = listener;
        };

        this.textProperty().addListener(this.changeListener);
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
                Font.getDefault().getSize(), "em;" + "-fx-text-fill: #d6d6d6;" + "-fx-font-family: " +
                this.fontFamily));
    }

    /**
     * Set the new size of the font, if it is valid.
     * @param size The size.
     */
    public void setFontSize(double size) {
        if (size >= this.MIN_TEXT_SIZE && size <= this.MAX_TEXT_SIZE) {
            this.fontSize = size;
            this.updateFont();
        }
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