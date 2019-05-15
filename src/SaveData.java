/**
 * Class that represents the Save Data. Used to encapsulate the Save Data or convert it into usable form. The Save Data
 * includes the text that the user entered, and the font the user used.
 */
public class SaveData {

    private String data;

    /**
     * Create a new SaveData object directly from the given String.
     * @param data The data.
     */
    public SaveData(String data) {
        this.data = data;
    }

    /**
     * Create a new SaveData object based on the text and the font size.
     * @param text The text.
     * @param fontSize The size of the font.
     */
    public SaveData(String text, int fontSize) {

        // First character represents number of digits of the font. This character denotes how many characters
        // following it are dedicated to storing the font size.
        this.data = Integer.toString(this.getNumDigits(fontSize));

        // The font size.
        this.data += Integer.toString(fontSize);

        // Finally, the text itself.
        this.data += text;
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
     * Returns the number of digits in the given number.
     * @param number The number.
     * @return The number of digits.
     */
    private int getNumDigits(int number) {
        return (int) Math.floor(Math.abs(Math.log10(number))) + 1;
    }

    /**
     * Returns the Save Data with the text and font size in the proper save format, as a String.
     * @return The Save Data.
     */
    public String getSaveData() {
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
        int digits = Integer.parseInt(this.data.substring(0, 1));
        return Integer.parseInt(this.data.substring(1, digits + 1));
    }

    /**
     * Returns the text that is stored here. If data is not valid, return empty String.
     * @return The text.
     */
    public String getText() {
        if (!this.isValid()) {
            return "";
        }
        int digits = Integer.parseInt(this.data.substring(0, 1));
        return this.data.substring(digits + 1);
    }
}