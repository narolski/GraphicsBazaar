package editor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * The Editor class, which configures the graphics' editor behaviour.
 */
public class Editor {

    public MenuBar mainMenu;
    private Boolean hasMadeChanges = false;
    @FXML
    public Pane imageBoard;

    /**
     * The method that is called upon the click on the About button in the menu, showing the information about the programme in an Alert-class object.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void showAbout(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About GraphicsBazaar");
        alert.setHeaderText("GraphicsBazaar");
        alert.setContentText("The most advanced graphics tool you have never experienced before.\n\nDesigned by PN.\n© 2017. All rights reserved.");

        alert.showAndWait();
    }

    /**
     * The method that is called upon the click on the Rectangle button in the menu, creating the new Rectangle object as a node of the imageBoard.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void addRectangle(ActionEvent actionEvent) {

        hasMadeChanges = true;
        RectangleFactory rectangleLayer = new RectangleFactory(imageBoard);

        imageBoard.setOnMouseClicked(rectangleLayer.createRectangle);

        ShapeTools rectangleTools = new ShapeTools(rectangleLayer.rectangle);

        rectangleLayer.rectangle.setOnMousePressed(rectangleTools.getDataForMove);
        rectangleLayer.rectangle.setOnMouseDragged(rectangleTools.moveShape);
        rectangleLayer.rectangle.setOnContextMenuRequested(rectangleTools.addContextMenu);
        rectangleLayer.rectangle.setOnScroll(rectangleTools.scaleShape);
        mainMenu.toFront();
    }
    /**
     * The method that is called upon the click on the Circle button in the menu, creating the new Circle object as a node of the imageBoard.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void addCircle(ActionEvent actionEvent) {

        hasMadeChanges = true;
        CircleFactory circleLayer = new CircleFactory(imageBoard);

        imageBoard.setOnMouseClicked(circleLayer.createRectangle);

        ShapeTools rectangleTools = new ShapeTools(circleLayer.circle);

        circleLayer.circle.setOnMousePressed(rectangleTools.getDataForMove);
        circleLayer.circle.setOnMouseDragged(rectangleTools.moveShape);
        circleLayer.circle.setOnContextMenuRequested(rectangleTools.addContextMenu);
        circleLayer.circle.setOnScroll(rectangleTools.scaleShape);
        mainMenu.toFront();
    }

    /**
     * The method that is called upon the click on the Polygon button in the menu, creating the new Polygon object as a node of the imageBoard.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void addPolygon(ActionEvent actionEvent) {

        hasMadeChanges = true;
        PolygonFactory polygonLayer = new PolygonFactory(imageBoard);

        imageBoard.setOnMouseClicked(polygonLayer.createPolygon);

        ShapeTools polygonTools = new ShapeTools(polygonLayer.polygon);

        polygonLayer.polygon.setOnMousePressed(polygonTools.getDataForMove);
        polygonLayer.polygon.setOnMouseDragged(polygonTools.moveShape);
        polygonLayer.polygon.setOnContextMenuRequested(polygonTools.addContextMenu);
        polygonLayer.polygon.setOnScroll(polygonTools.scaleShape);
        mainMenu.toFront();
    }

    /**
     * The method that is called upon the click on the Save As button in the menu, used for saving the images.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void saveAs(ActionEvent actionEvent) {

        hasMadeChanges = false;
        FileTools saveTools = new FileTools(imageBoard);
        saveTools.saveImage();
    }

    /**
     * The method that is called upon the click on the Open button in the menu, used for opening the saved images.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void openFile(ActionEvent actionEvent) {

        FileTools openTools = new FileTools(imageBoard);
        openTools.openImage();
    }

    /**
     * The method that is called upon the click on the New file button in the menu, used for creating new blank image file.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void newFile(ActionEvent actionEvent) {

        FileTools closeTools = new FileTools(imageBoard);
        closeTools.showWarning(hasMadeChanges);
        imageBoard.getChildren().clear();
    }

    /**
     * The method that is called upon the click on the Close button in the menu, used for closing the programme.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void closeApp(ActionEvent actionEvent) {

        FileTools closeTools = new FileTools(imageBoard);
        closeTools.showWarning(hasMadeChanges);
        if (!hasMadeChanges)
            Platform.exit();
    }

    /**
     * The method that is called upon the click on the Help button in the menu, used for showing the help screen.
     * @param actionEvent is an event upon which the method is being called.
     */
    public void showHelp(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GraphicBazaar's help");
        alert.setHeaderText("Help");
        alert.setContentText("GraphicsBazaar is a revolutionary way to create vivid computer images using limitless* possibilities offered by it's rich functionality.\n\nTo create a shape, use the Shape menu.\n\nTo create an rectangle, select two points on the canvas.\n\nTo create a circle, select the center and then the desired radius.\n\nTo create a polygon, choose as many points as you want and click on the first point to finish the shape.\n\n*The functionality may vary depending on the laziness of the author.");

        alert.showAndWait();
    }
}
