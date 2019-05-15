/**
 * Class used to test that save data on Google is correct.
 */
public class Tester {

    public static void main(String[] args) {
        AppData appData = new AppData();
        appData.signIn();
        appData.printFileNames();
    }

}