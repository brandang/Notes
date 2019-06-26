import java.io.Serializable;

/**
 * A class for purposes of storing data for a single component in a list of items.
 */
public class ItemData implements TextChangeListener, Serializable {

    // Constants for use in identifying which view type this ItemData is for.
    final public static int TYPE_PHOTO = 0;
    final public static int TYPE_TEXT = 1;

    private int dataType;

    private String data;

    /**
     * Creates new item data to store.
     * @param data The data.
     * @param dataType The type. Will set type to TYPE_TEXT if invalid input.
     */
    public ItemData(String data, int dataType) {
        this.data = data;
        this.dataType = dataType;
        if (this.dataType != TYPE_PHOTO && this.dataType != TYPE_TEXT) {
            this.dataType = TYPE_TEXT;
        }
    }

    /**
     * Returns what kind of type this data is.
     * @return The type.
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * Returns the data associated with this ItemData instance. If it is text data, it will return
     * the text, whereas if it is photo, it will have the path.
     * @return The data.
     */
    public String getData() {
        return data;
    }

    /**
     * Append data to the end.
     * @param appendData The data to append.
     */
    public void appendData(String appendData) {
        this.data += appendData;
    }

    @Override
    public void onTextChanged(String newText) {
        this.data = newText;
    }
}
