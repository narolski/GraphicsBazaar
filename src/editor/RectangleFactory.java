package editor;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

/**
 * The RectangleFactory class is used to create a Rectangle according to the user's input.
 */

class RectangleFactory {

    Rectangle rectangle;
    private Pane imageCanvas;

    private double x1;
    private double y1;

    /**
     * A constructor which creates the RectangleFactory.
     * @param imageCanvas is the pane on which the figure will be created.
     */
    RectangleFactory(Pane imageCanvas) {
        rectangle = new Rectangle();
        this.imageCanvas = imageCanvas;
    }

    /**
     * An EventHandler that creates the Rectangle by getting the points on which the diagonal of the Rectangle is placed.
     */
    EventHandler<MouseEvent> createRectangle = new EventHandler<MouseEvent>() {

        int clickCount = 1;

        @Override
        public void handle(MouseEvent event) {

            if (clickCount == 1) {

                x1 = event.getX();
                y1 = event.getY();
                clickCount++;

            } else if (clickCount == 2) {
                double x2 = event.getX();
                double y2 = event.getY();

                if (x1 < x2 && y1 > y2) {
                    rectangle.setX(x1);
                    rectangle.setY(y2);
                }
                else if (x1 > x2 && y1 < y2) {
                    rectangle.setX(x2);
                    rectangle.setY(y1);
                }
                else if (x1 > x2 && y1 > y2) {
                    rectangle.setX(x2);
                    rectangle.setY(y2);
                }
                else {
                    rectangle.setX(x1);
                    rectangle.setY(y1);
                }

                rectangle.setWidth(ShapeTools.calculateWidth(x1, x2));
                rectangle.setHeight(ShapeTools.calculateHeight(y1, y2));

                rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));

                imageCanvas.getChildren().add(rectangle);
                clickCount++;
            }
        }
    };
}
