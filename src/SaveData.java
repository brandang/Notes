import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents the Save Data. Used to encapsulate the Save Data or convert it into usable form. The Save Data
 * includes the text that the user entered, and the font the user used.
 */
public class SaveData implements Serializable {

    private int textSize = 16;
    private ArrayList<ItemData> data;

    /**
     * Create a new SaveData object directly from the given String.
     * @param data The data.
     */
    public SaveData(ArrayList<ItemData> data) {
        this.data = data;
    }

    /**
     * Create a new SaveData object based on the text and the font size.
     * @param data The data.
     * @param fontSize The size of the font.
     */
    public SaveData(ArrayList<ItemData> data, int fontSize) {
        this.data = data;
        this.textSize = fontSize;
    }

    /**
     * Returns whether or not the Save Data is valid. It is not valid when there is no data.
     * @return True for yes, False for no.
     */
    public boolean isValid() {
        if (this.data.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Returns the Save Data with the text and font size in the proper save format, as a String.
     * @return The Save Data.
     */
    public ArrayList<ItemData> getData() {
        return this.data;
    }

    /**
     * Returns the font size that is stored here. If data is not valid, return 0.
     * @return The size of the font.
     */
    public int getFontSize() {
        if (!this.isValid()) {
            return 0;
        }
        return this.textSize;
    }
}