package com.andrewalia.view;

// Importing required classes and packages
import com.andrewalia.controller.FractalController;
import com.andrewalia.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI class that extends the JavaFX Application class.
 * Handles the GUI components and event handling for the fractal viewer application.
 */
public class GUI extends Application {

    // Class-level variables for GUI components and properties
    static HBox root;
    static Scene scene;
    static WritableImage writableImage;
    static PixelWriter pixelWriter;
    static final int VIEWER_HEIGHT = 900;
    static final int VIEWER_WIDTH = 900;
    
    // Variables to keep track of mouse positions and viewport
    double lastMouseX;
    double lastMouseY;
    double viewportX = 0;
    double viewportY = 0;
    double viewportHeight = 4;
    Slider iterationsSlider;
    
    // FractalController instance for rendering fractals
    FractalController controller;

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        // Initialize GUI components
        root = new HBox(20);
        root.setPadding(new Insets(15, 12, 15, 12));
        VBox controls = new VBox(10);
        controls.setPadding(new Insets(10));
        VBox sliderBox = new VBox(10);
        sliderBox.setPadding(new Insets(10));

        // Image setup
        writableImage = new WritableImage(VIEWER_WIDTH, VIEWER_HEIGHT);
        ImageView imageView = new ImageView(writableImage);
        root.getChildren().add(imageView);
        
        // Event Handlers
        imageView.setOnMousePressed(this::handleMousePressed);
        imageView.setOnMouseDragged(this::handleMouseDragged);
        imageView.setOnScroll(this::handleMouseScroll);

        // Initialize PixelWriter for image manipulation
        pixelWriter = writableImage.getPixelWriter();
        
        // Initialize the controller
        controller = new FractalController(this);
        
        // Slider for iterations
        Label iterationLabel = new Label("Iterations:");
        iterationsSlider = new Slider(0, 100, 2);
        iterationsSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        iterationsSlider.setShowTickLabels(true);
        iterationsSlider.setShowTickMarks(true);
        iterationsSlider.prefHeightProperty().bind(root.heightProperty());
        sliderBox.getChildren().addAll(iterationLabel, iterationsSlider);


        // Buttons for fractal types
        // Updating fractal on slider change
        Button mandelbrotButton = styledButton("Mandelbrot");
        mandelbrotButton.setOnAction(e -> {
            controller.setFractal(new Mandelbrot());
            controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
        });

        Button sierpinskiButton = styledButton("Sierpinski Triangle");
        sierpinskiButton.setOnAction(e -> {
            controller.setFractal(new SierpinskiTriangle());
            controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
        });

        Button barnsleyFern = styledButton("Barnsley Fern");
        barnsleyFern.setOnAction(e -> {
            controller.setFractal(new Barnsley());
            controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
        });

        Button dragonButton = styledButton("Dragon Curve");
        dragonButton.setOnAction(e -> {
            controller.setFractal(new DragonCurve());
            controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
        });

        Button kochButton = styledButton("Koch Snowflake");
        kochButton.setOnAction(e -> {
            controller.setFractal(new KochSnowflake());
            controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
        });

        Button newtonButton = styledButton("Newton's Method");
        kochButton.setOnAction(e -> {
            controller.setFractal(new Newton());
            controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
        });

        //Temporarily set these to invisible as these don't work as expected.
        sierpinskiButton.setVisible(false);
        barnsleyFern.setVisible(false);
        dragonButton.setVisible(false);
        kochButton.setVisible(false);
        newtonButton.setVisible(false);


        iterationsSlider.valueProperty()
                .addListener((obs, oldVal, newVal) -> controller.reRenderWithParams(viewportX, viewportY, viewportHeight, newVal.intValue()));

        controls.getChildren().addAll(mandelbrotButton, sierpinskiButton, barnsleyFern, dragonButton, kochButton, newtonButton);
        root.getChildren().addAll(controls, sliderBox);

        scene = new Scene(root);
        scene.getStylesheets().add("styles.css");

        stage.setScene(scene);
        stage.setTitle("Fractal Viewer");
        stage.show();
    }

    /**
     * Returns a styled button with the given text.
     *
     * @param text The text to display on the button.
     * @return A styled button.
     */
    private Button styledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("custom-button");
        return button;
    }

    /**
     * Handles mouse-pressed events on the ImageView.
     *
     * @param event The mouse event.
     */
    private void handleMousePressed(MouseEvent event) {
        lastMouseX = event.getX();
        lastMouseY = event.getY();
    }

    /**
     * Handles mouse-dragged events on the ImageView.
     *
     * @param event The mouse event.
     */
    private void handleMouseDragged(MouseEvent event) {
        // Calculate change in mouse position
        double deltaX = (event.getX() - lastMouseX) * viewportHeight / VIEWER_WIDTH;
        double deltaY = (event.getY() - lastMouseY) * viewportHeight / VIEWER_HEIGHT;

        viewportX -= deltaX;
        viewportY -= deltaY;

        lastMouseX = event.getX();
        lastMouseY = event.getY();

        // Re-render fractal based on new viewport
        controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
    }

    /**
     * Handles mouse-scroll events for zooming on the ImageView.
     *
     * @param event The scroll event.
     */
    private void handleMouseScroll(ScrollEvent event) {
        // Calculate zoom factor and new viewport parameters
        double zoomFactor = 1.5;
        double offsetX = (event.getX() / VIEWER_WIDTH - 0.5) * viewportHeight;
        double offsetY = -(event.getY() / VIEWER_HEIGHT - 0.5) * viewportHeight;

        if (event.getDeltaY() > 0) {
            viewportHeight /= zoomFactor;
            viewportX += offsetX * (1 - 1 / zoomFactor);
            viewportY -= offsetY * (1 - 1 / zoomFactor);
        } else {
            viewportHeight *= zoomFactor;
            viewportX += offsetX * (1 - zoomFactor);
            viewportY -= offsetY * (1 - zoomFactor);
        }

        // Re-render fractal based on new viewport
        controller.reRenderWithParams(viewportX, viewportY, viewportHeight, (int) iterationsSlider.getValue());
    }

    /**
     * Renders the fractal on the ImageView.
     *
     * @param fractal The fractal to render.
     * @param viewportX The x-coordinate of the viewport.
     * @param viewportY The y-coordinate of the viewport.
     * @param viewportHeight The height of the viewport.
     * @param iterations The number of iterations for generating the fractal.
     */
    public void renderFractal(Fractal fractal, double viewportX, double viewportY, double viewportHeight, int iterations) {
        fractal.generateFractal(pixelWriter, VIEWER_WIDTH, VIEWER_HEIGHT, viewportX, viewportY, viewportHeight, iterations);
    }

    /**
     * The main method for launching the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
