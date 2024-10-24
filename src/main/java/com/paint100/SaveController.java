package com.paint100;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.TrayIcon.MessageType;
import java.awt.*;


/**
 * Controls saving image to files
 */
public class SaveController {

    /**
     * Controls saving image to files
     */
    public SaveController() {}

    /**
     * Will save or save-as a canvas as a file to the directed location or most recently opened/save image
     *
     * @param canvas The canvas to save
     * @param selectedFile Location of either the most recently opened image, or most recently saved image, if any
     * @param saveNew Determines whether to save or save-as
     * @return imgFile
     */
    //Used for saving the canvas as an image within the users files. Will choose whether to save as a new image or overwrite a previously opened
    //image based on the parameter saveNew, which is determined when called and based on the situation
    public static File save (Canvas canvas, File selectedFile, boolean saveNew, boolean notifEnabled) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG (different file type will lead to data loss)", "*.png"), new FileChooser.ExtensionFilter("JPG (different file type will lead to data loss)", "*.jpg"), new FileChooser.ExtensionFilter("BMP (different file type will lead to data loss)", "*.bmp"));
        Image image = canvas.snapshot(null, null);
        File imgFile;
        if (saveNew){
            imgFile = fileChooser.showSaveDialog(new Stage());
        }
        else {
            imgFile = selectedFile;
        }

        //Credit to randers on StackOverflow for the notifications
        //Changes the image (which is extracted from the canvas) into a bufferedImage, allowing it to be saved with ImageIO.write
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image,null), "png", imgFile);
            if (notifEnabled) {

                //Credit to randers on StackOverflow for the windows notification
                SystemTray tray = SystemTray.getSystemTray();

                java.awt.Image notImage = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(notImage, "Tray Demo");
                //Let the system resize the image if needed
                trayIcon.setImageAutoSize(true);
                //Set tooltip text for the tray icon
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);

                trayIcon.displayMessage("Pain(t)", "Image Saved", MessageType.INFO);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        return imgFile;
    }

    /**
     * Will take a Canvas and return it as an image. Used for server uploading
     *
     * @param canvas The canvas to return as an image
     * @return file
     */
    //Turns a image into a file, used for server uploading
    public static File returnFile(Canvas canvas){
        WritableImage image = new WritableImage( (int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, image);
        File file = new File("output.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
