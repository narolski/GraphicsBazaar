package editor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javafx.scene.shape.*;
import java.lang.Math.*;
import static java.lang.Math.abs;

/**
 * The ShapeTools class is used to create a ShapeTools object which holds all the necessary tools to prepare the transformations of the shapes.
 */
class ShapeTools {

    private Shape shape;
    private Point2D dragDelta;

    /**
     * Constructor that creates the ShapeTools utility, used to transform the shapes created in the GraphicsBazaar.
     * @param shape is the shape that is going to be used for the transformations.
     */
    ShapeTools(Shape shape) {
        this.shape = shape;
    }

    /**
     * An EventHandler used to get the primary point of origin of the shape before the movement, in order to move the shape without any unnecessary padding.
     */
    EventHandler<MouseEvent> getDataForMove = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            dragDelta = new Point2D(shape.getLayoutX() - event.getSceneX(), shape.getLayoutY() - event.getSceneY());
        }
    };

    /**
     * An EventHandler used to move the shape in a way described by the user through an mouse input.
     */
    EventHandler<MouseEvent> moveShape = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            //double x = shape.getTranslateX() + event.getX();
            //double y = shape.getTranslateY() + event.getY();

            shape.setLayoutX(event.getSceneX() + dragDelta.getX());
            shape.setLayoutY(event.getSceneY() + dragDelta.getY());
        }
    };

    /**
     * An EventHandler used to scale the shape according to a user input from a mouse's scroll.
     */
    EventHandler<ScrollEvent> scaleShape = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(ScrollEvent event) {
            double scale = (event.getDeltaY() > 0) ? 1.2 : (1 / 1.2);

            shape.setScaleX(shape.getScaleX() * scale);
            shape.setScaleY(shape.getScaleY() * scale);
        }
    };

    /**
     * An EventHandler used to add a ContextMenu, including the ability to change colours of individual shapes or change their z-index
     */
    EventHandler<ContextMenuEvent> addContextMenu = new EventHandler<ContextMenuEvent>() {

        ContextMenu contextMenu = new ContextMenu();
        ColorPicker colorPicker = new ColorPicker();

        MenuItem colourHeader = new MenuItem(null, new Label("Shape's colour"));
        MenuItem colourPickerMenuItem = new MenuItem(null, colorPicker);
        MenuItem shapeUp = new MenuItem(null, new Label("Move frontwards"));
        MenuItem shapeDown = new MenuItem(null, new Label("Move backwards"));

        SeparatorMenuItem separator = new SeparatorMenuItem();

        @Override
        public void handle(ContextMenuEvent event) {

            contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent e) {
                    colorPicker.setValue((Color) shape.getFill());
                }
            });

            colourPickerMenuItem.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event)
                {
                    colorPicker.setBackground(new Background(new BackgroundFill(colorPicker.getValue(),null,null)));
                    shape.setFill(colorPicker.getValue());
                }
            });

            shapeUp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    shape.toFront();
                }
            });

            shapeDown.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    shape.toBack();
                }
            });

            contextMenu.getItems().setAll(colourHeader, colourPickerMenuItem, separator, shapeUp, shapeDown);
            contextMenu.show(shape, Side.RIGHT, 0, 0);
        }
    };

    /**
     * Method used to calculate the width of the rectangle
     * @param x1 is an x-coordinate of the starting point of the diagonal
     * @param x2 is an x-coordinate of the ending point of the diagonal
     * @return returns the width of the rectangle
     */
    static double calculateWidth(double x1, double x2) {
        return abs(x2 - x1);
    }

    /**
     * Method used to calculate the height of the rectangle
     * @param y1 is an y-coordinate of the starting point of the diagonal
     * @param y2 is an y-coordinate of the ending point of the diagonal
     * @return returns the height of the rectangle
     */
    static double calculateHeight(double y1, double y2) {
        return abs(y1 - y2);
    }
}
