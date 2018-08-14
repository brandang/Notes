import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Launcher class that starts the program.
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Constants.WINDOW_TITLE);
        NotepadGUI gui = new NotepadGUI();
        NotepadController controller = new NotepadController();
        controller.setFrontend(gui);
        gui.setBackend(controller);
        Scene scene = gui.getScene();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

        controller.loadData();
    }
}