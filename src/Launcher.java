import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launcher class that starts the program.
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Constants.WINDOW_TITLE);
        NotepadGUI gui = new NotepadGUI();
        Scene scene = gui.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}