import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        primaryStage.getIcons().add(new Image(Constants.APP_ICON_PATH));
        primaryStage.setOnCloseRequest(event -> controller.closeButtonPressed());
        primaryStage.show();

        // Load Save Data. Do this in another thread, so that the GUI appears before data fully loads.
        controller.loadData();


        // Bring Window to front. Second line prevents this Window from always being at front when using other apps.
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setAlwaysOnTop(false);

    }
}