import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;

/**
 * A loading animation consisting of a circular rotating spinner.
 */
public class LoadingPane extends VBox {

    private Color spinnerColour = Color.GRAY;
    private Color trackColour = Color.TRANSPARENT;

    private Arc track;
    private Arc spinner;

    // What to text to display on the loading screen.
    private Label messageDisplay;

    final private static Color DEFAULT_MESSAGE_COLOUR = Color.rgb(214, 214, 214);

    final private static Font DEFAULT_FONT = new Font("Segoe UI", 16);

    final private static Color DEFAULT_BACKGROUND_COLOUR = Color.rgb(63,63,63);

    final private static double DEFAULT_SPINNER_RADIUS = 40;

    final private static double DEFAULT_SPINNER_SIZE = 245;

    /**
     * Initializes a LoadingPane loading animation with given message. Warning: if there is insufficient space
     * in the parent since the radius is too large, the spinner might not display properly.
     * @param message The message to display while loading.
     */
    public LoadingPane(String message) {
        super();
        this.setupComponents(message, this.DEFAULT_SPINNER_RADIUS, this.DEFAULT_SPINNER_SIZE);
        // Prepare the rotation animation.
        Animation rotateAnimation = this.prepareSpinAnimation(this.spinner);
        rotateAnimation.setOnFinished(event -> rotateAnimation.play());
        rotateAnimation.play();
    }

    /**
     * Initializes a LoadingPane loading animation. Displays the given text. Warning: if there is insufficient space
     * in the parent since the radius is too large, the spinner might not display properly.
     * @param message The text to display to the user.
     * @param radius The radius of the spinner.
     * @param size The size in angles of the spinner.
     */
    public LoadingPane(String message, double radius, double size) {

        super();

        if (size > 360) {
            size = 360;
        }
        if (size < 0) {
            size = 0;
        }

        this.setupComponents(message, radius, size);

        // Prepare the rotation animation.
        Animation rotateAnimation = this.prepareSpinAnimation(this.spinner);
        rotateAnimation.setOnFinished(event -> rotateAnimation.play());
        rotateAnimation.play();
    }

    /**
     * Sets up the components such as the background and the spinner.
     * @param message The message to display.
     * @param radius The radius of the spinner.
     * @param size The size in angles of the spinner.
     */
    private void setupComponents(String message, double radius, double size) {
        this.setBackground(new Background(new BackgroundFill(this.DEFAULT_BACKGROUND_COLOUR, CornerRadii.EMPTY,
                Insets.EMPTY)));

        // Set up the spinner and it`s track.
        this.setupSpinner(radius, size);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(this.track, this.spinner);

        this.messageDisplay = new Label(message);
        this.messageDisplay.setFont(this.DEFAULT_FONT);
        this.setFontColour(this.DEFAULT_MESSAGE_COLOUR);

        this.getChildren().addAll(pane, this.messageDisplay);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
    }

    /**
     * Sets up the spinner and track.
     * @param radius The radius of the spinner and track.
     * @param size The angle size, in degrees.
     */
    private void setupSpinner(double radius, double size) {
        this.spinner = new Arc();
        this.spinner.setRadiusX(radius);
        this.spinner.setRadiusY(radius);
        this.spinner.setStartAngle(0);
        this.spinner.setLength(size);
        this.spinner.setType(ArcType.OPEN);
        this.spinner.setFill(Color.TRANSPARENT);
        this.spinner.setStroke(this.spinnerColour);
        this.spinner.setStrokeWidth(7);

        this.track = new Arc();
        this.track.setRadiusX(radius);
        this.track.setRadiusY(radius);
        this.track.setStartAngle(0);
        this.track.setLength(360);
        this.track.setType(ArcType.OPEN);
        this.track.setFill(Color.TRANSPARENT);
        this.track.setStroke(this.trackColour);
        this.track.setStrokeWidth(5);
    }

    /**
     * Prepares rotating animation for the specified arc shape.
     * @param shape The Arc.
     * @return The Animation.
     */
    private Animation prepareSpinAnimation(Arc shape) {
        // The rotation animation.
        Rotate rotation = new Rotate();
        rotation.pivotXProperty().bind(shape.centerXProperty());
        rotation.pivotYProperty().bind(shape.centerYProperty());
        shape.getTransforms().add(rotation);

        // Start at a random angle.
        Random rand = new Random();
        int startAngle = rand.nextInt(180);
        Timeline anim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), startAngle)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), startAngle + 360)));
        return anim;
    }

    /**
     * Sets the message to display on the loading screen.
     * @param message The message.
     */
    private void setMessage(String message) {
        this.messageDisplay.setText(message);
    }

    /**
     * Sets the font for the message.
     * @param messageFont The font for the message.
     */
    private void setFont(Font messageFont) {
        this.messageDisplay.setFont(messageFont);
    }

    /**
     * Sets the colour of the message font.
     * @param colour The colour.
     */
    private void setFontColour(Color colour) {
        this.messageDisplay.setTextFill(colour);
    }

    /**
     * Sets the colour of the spinner.
     * @param colour The colour.
     */
    private void setSpinnerColour(Color colour) {
        this.spinnerColour = colour;
        this.spinner.setStroke(colour);
    }

    /**
     * Sets the colour of the track.
     * @param colour The colour.
     */
    public void setTrackColour(Color colour) {
        this.trackColour = colour;
        this.track.setStroke(colour);
    }
}