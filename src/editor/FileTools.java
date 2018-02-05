package editor;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.*;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Optional;

/**
 * The FileTools class is used to create a FileTools object which holds all the necessary tools to prepare the file IO operations.
 */
class FileTools {

    private Pane imagePane;

    /**
     * A constructor which constructs the FileTools utility.
     * @param paneToSave is the Pane which holds all the shapes created by the user.
     */
    FileTools(Pane paneToSave) {
        this.imagePane = paneToSave;
    }

    /**
     * saveImage method is used for saving the final image created by the user into a Portable Network Graphics (PNG) file.
     * It uses the FileChooser class, which alows the user to choose the location where saving of a file is desired.
     * Chosen file location is then stored in the object file of the File class.
     * Finally, the object image of the WritableImage class creates a snapshot of the Pane on which the image is created.
     * Them, ImageIO class is used to write the created image to the user's filesystem.
     */
    void saveImage() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GraphicsBazaar image (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                //Pad the capture area
                WritableImage image = imagePane.snapshot(new SnapshotParameters(), null);
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    /**
     * openImage method is used to open the Portable Network Graphics image file previously created by the user.
     * It uses FileChooser class and File class objects to choose the file that is desired to be opened by the user.
     * Then, a new ImageView class object is created to present the image to the user.
     */
    void openImage() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GraphicsBazaar image (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                imagePane.getChildren().add(new ImageView(file.toURI().toURL().toExternalForm()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * The showWarning method presents a warning to the user every time an action is made which may result in the loss of data.
     * The method is called accordingly to the potentially dangerous situations.
     * @param hasMadeChanges is the current state of changes made in the programme.
     */
    void showWarning(Boolean hasMadeChanges) {

        if (hasMadeChanges) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unsaved changes");
            alert.setHeaderText("Unsaved changes");
            alert.setContentText("There are still unsaved changes made to the file that you've created. Do you want to save them first?");

            ButtonType saveButton = new ButtonType("Save changes");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(saveButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == saveButton){
                hasMadeChanges = false;
                FileTools saveTools = new FileTools(imagePane);
                saveTools.saveImage();
                hasMadeChanges = false;
            }
        }

    }

}
