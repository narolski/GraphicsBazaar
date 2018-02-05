package editor;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static com.sun.tools.doclint.Entity.Delta;
import static java.lang.StrictMath.abs;

/**
 * The PolygonFactory class is used to create a Polygon according to the user's input.
 */
class PolygonFactory {

    Polygon polygon;
    private Pane imageCanvas;

    /**
     * Constructs the PolygonFactory
     * @param imageCanvas is the pane on which the polygon will be placed
     */
    PolygonFactory(Pane imageCanvas) {
        polygon = new Polygon();
        this.imageCanvas = imageCanvas;
    }

    /**
     * An event handler which is used to create the polygon by getting the individual points which contribute to the figure as a whole.
     */
    EventHandler<MouseEvent> createPolygon = new EventHandler<MouseEvent>() {

        private int pointsCount = 1;
        private Boolean hasFinishedShape = false;

        private ArrayList<Double> points = new ArrayList<Double>();

        @Override
        public void handle(MouseEvent event) {

            if (!hasFinishedShape) {
                double newPointX = event.getX();
                double newPointY = event.getY();

                System.out.println("X: " + newPointX + ", Y: " + newPointY);

                if (pointsCount > 2) {
                    for (int i = 0; i < points.size(); i = i + 2) {
                        double x = points.get(i);
                        double y = points.get(i + 1);

                        double a = abs(x - newPointX);
                        double b = abs(y - newPointY);

                        System.out.println(i + ") Abs X: " + a + ", abs y: " + b);

                        if ((abs(x - newPointX) < 50.0) && (abs(y - newPointY) < 50.0))
                            hasFinishedShape = true;
                    }
                    System.out.println("Shape finished: " + hasFinishedShape);
                }

                if (hasFinishedShape) {
                    System.out.println("Is now finishing shape...");

                    Double[] coordinates = new Double[points.size()];

                    for (int i = 0; i < coordinates.length; i++)
                        coordinates[i] = points.get(i);

                    polygon.getPoints().addAll(coordinates);
                    polygon.setFill(Color.color(Math.random(), Math.random(), Math.random()));

                    imageCanvas.getChildren().add(polygon);
                } else {
                    points.add(newPointX);
                    points.add(newPointY);
                    pointsCount++;
                }
            }
        }
    };
}
