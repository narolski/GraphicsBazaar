package editor;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;

/**
 * The CircleFactory class is used to create a Circle according to the user's input.
 */
class CircleFactory {

    Circle circle;
    private Pane imageCanvas;

    private double x;
    private double y;

    /**
     * A constructor that creates the CircleFactory.
     * @param imageCanvas is the pane on which the figure will be created.
     */
    CircleFactory(Pane imageCanvas) {
        circle = new Circle();
        this.imageCanvas = imageCanvas;
    }

    /**
     * An EventHandler which is used to create the Circle by getting the center point of the Circle and the radius provided by the user.
     */
    EventHandler<MouseEvent> createRectangle = new EventHandler<MouseEvent>() {

        int clickCount = 1;

        @Override
        public void handle(MouseEvent event) {

            if (clickCount == 1) {
                x = event.getX();
                y = event.getY();

                clickCount++;

            } else if (clickCount == 2) {
                double radius = event.getX() - x;

                circle.setCenterX(x);
                circle.setCenterY(y);
                circle.setRadius(radius);
                circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                imageCanvas.getChildren().add(circle);
                clickCount++;
            }
        }
    };
}
