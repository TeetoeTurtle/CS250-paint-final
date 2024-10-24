package com.paint100;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Controls creation of popup menus
 */
public class PopupStage {

    /**
     * Controls creation of popup menus
     */
    public PopupStage() {}

    /**
     * @param type Determines the type of box to open
     */
    //Credit to Milan Pal Singh on stackoverflow for this popup window
    //Creates a new scene with a VBox that has set text, and displays it
    static void open(String type) {
        Stage dialog = new Stage();
        dialog.setWidth(600);
        dialog.setHeight(600);
        dialog.initModality(Modality.APPLICATION_MODAL);
        ScrollPane pane = new ScrollPane();
        VBox dialogVbox = new VBox(30);
        if (Objects.equals(type, "About")) {
            dialogVbox.getChildren().add(new Text("Release Notes\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 1.0.0 (10/24/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Full public release\n" +
                    "-JavaDocs for all public methods\n" +
                    "-Cleanup and organization of internal code\n" +
                    "-Fixed an issue with undo/redo not working withe rotate/mirror\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-N/A\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-N/A\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 0.2.3 (10/4/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Rotate and/or mirror selected area or whole canvas\n" +
                    "-Tool tips for tools\n" +
                    "-Notifications for saving\n" +
                    "-User logs\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-N/A\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-Internal code cleanup\n" +
                    "-Full release\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 0.2.2 (9/27/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Webserver added. Canvas is uploaded every time user clicks\n" +
                    "-Unit Tests added\n" +
                    "-Auto save every 30 seconds + display timer\n" +
                    "-Javadoc commenting added\n" +
                    "-May now save images as a separate file type\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-N/A\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-Rotate Image\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 0.2.1 (9/19/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Tabs added\n" +
                    "-Clear canvas tool added\n" +
                    "-Text tool added\n" +
                    "-Fonts for text tool\n" +
                    "-Hexagon tool added\n" +
                    "-Any sided polygon tool added\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-N/A\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-Transparency options\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 0.2.0 (9/14/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Draw shape tools added (square, rectangle, oval, circle, triangle, right triangle)\n" +
                    "-Preview for shapes and line\n" +
                    "-Dashed option for all drawing tools\n" +
                    "-When a tool button is toggled on, all others are un-toggled\n" +
                    "-Icons for tools\n" +
                    "-Cleaned up the menu bar to look better with the icons\n" +
                    "-Smart save when closing\n" +
                    "-Touched up canvas resizing\n" +
                    "-Fix some previously unknown problems with the save feature\n" +
                    "-When clicking \"save\" when no previous file has been opened, directs user to choose a directory (functionally\n" +
                    "identical to \"save-as\")\n" +
                    "-Undo and redo added\n" +
                    "-Copy and pasted with select added. Still WIP\n" +
                    "-Select tool has been added\n" +
                    "-Hot keys (Ctrl+S for save, Ctrl+Z for undo, Ctrl+C to copy selected item, Ctrl+V to paste selected item) added\n" +
                    "-Foundation of separate tabs has been created. Currently not function and very WIP\n" +
                    "-Internal code has been cleaned up. Additional classes were made, and many more comments added\n" +
                    "\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-A bug happened when resizing the screen, and then drawing, the entire screen got filled with selected color.\n" +
                    "I haven't been able to recreate this a second time, so the exact cause and source are unknown\n" +
                    "\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-More shape tools\n" +
                    "-Finish tabs\n" +
                    "-Finish copy and paste\n" +
                    "-Text tool\n" +
                    "-Separate PaintController into more classes\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 0.1.1 (9/6/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Paint tool that lets user draw anywhere on image\n" +
                    "-Line tool that lets user draw a straight line by dragging and letting go\n" +
                    "-Color selector that will change the color of the paint and line\n" +
                    "-Eraser that can erase any part of paint or line\n" +
                    "-BMP file support\n" +
                    "-Reorganized menu layout\n" +
                    "-About page added\n" +
                    "-Help page added\n" +
                    "\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-When drawing line, there is no preview of where its currently going to be placed\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-More shape tools\n" +
                    "\n" +
                    "----------------------------------------------------------------------------------\n" +
                    "\n" +
                    "Version 0.1.0 (8/29/2024)\n" +
                    "\n" +
                    "New features\n" +
                    "-Allows user to upload and view image\n" +
                    "-Allows user to save uploaded image\n" +
                    "-Allows user to save uploaded image as a new image\n" +
                    "\n" +
                    "Known Issues\n" +
                    "-N/A\n" +
                    "\n" +
                    "Expected Next Version\n" +
                    "-Basic image filters\n" +
                    "\n"));
        }
        else if (Objects.equals(type, "Help")) {
            dialogVbox.getChildren().add(new Text("File -> Open: Opens file manager to allow user to select image file to\n" +
                    "upload to upload to the canvas\n" +
                    "File -> Save: Will overwrite the most recently opened image with\n" +
                    "what is drawn on the canvas\n" +
                    "File -> Save As: Opens file manager to allow user to save what is drawn\n" +
                    "on the canvas to a location and with a name of the user's choice\n" +
                    "Help -> Help: Opens the help page\n" +
                    "Help -> About: Opens the version & Release Notes page\n" +
                    "Font Size: Changes the size of all drawing tools and the eraser to be \n" +
                    "the size the user chooses\n" +
                    "Color Chooser: Changes the color of all drawing tools\n" +
                    "Paint Box: Selects the current drawing tool to be the paint brush, which\n" +
                    "will draw wherever the user clicks or drags\n" +
                    "Eraser Box: Will delete any paint applied by the any of the drawing tools\n" +
                    "Line Box: Will draw a straight line when a user drags. The start and \n" +
                    "end of the line will be where the user initially dragged and released"));
        }
        pane.setContent(dialogVbox);
        Scene dialogScene = new Scene(pane, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Creates the warning before clearing the canvas
     */
    static boolean clearOpen() {
        final Stage dialog = new Stage();

        dialog.setWidth(200);
        dialog.setHeight(100);
        dialog.initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        HBox hbBtn = new HBox(2);
        final boolean[] returnValue = {false};

        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //primaryStage.hide();
                dialog.close();
                returnValue[0] = true;
            }
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //primaryStage.hide();
                dialog.close();
                returnValue[0] = false;
            }
        });

        hbBtn.getChildren().addAll(clearBtn, cancelBtn);
        grid.add(hbBtn, 1, 1);


        javafx.scene.control.Label label = new javafx.scene.control.Label("Clear Canvas?");


        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        grid.add(label, 1, 0);

        Scene dialogScene = new Scene(grid, 100, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
        return returnValue[0];
    }
}
