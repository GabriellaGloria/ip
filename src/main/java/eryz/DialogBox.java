package eryz;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The DialogBox class is responsible for creating and displaying a dialog box
 * that contains a message and a corresponding display picture.
 * It can generate a dialog for both the user and the EryzBot.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;  // The label displaying the dialog text.
    @FXML
    private ImageView displayPicture;  // The image view displaying the dialog's associated image.

    /**
     * Private constructor for initializing a DialogBox with the provided text and image.
     * This constructor is used internally by the static methods.
     * 
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed next to the dialog.
     */
    private DialogBox(String text, Image img) {
        try {
            // Load the FXML for the DialogBox.
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the dialog text and the display image.
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box, changing the order of the text and image.
     * This is used to swap the user and EryzBot's dialog boxes, placing the image and text in reverse order.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);  // Reverse the order of the children (image and text).
        getChildren().setAll(tmp);  // Update the children in the new order.
    }

    /**
     * Creates a DialogBox for the user, displaying the provided text and image.
     * 
     * @param text The text to be displayed in the user's dialog box.
     * @param img The image to be displayed alongside the user's dialog.
     * @return A new DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for the EryzBot, displaying the provided text and image,
     * and flips the dialog so that the image appears on the left side of the dialog box.
     * 
     * @param text The text to be displayed in the EryzBot's dialog box.
     * @param img The image to be displayed alongside the EryzBot's dialog.
     * @return A new DialogBox representing the EryzBot's dialog, flipped.
     */
    public static DialogBox getEryzBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();  // Flip the dialog box for the EryzBot.
        return db;
    }
}
