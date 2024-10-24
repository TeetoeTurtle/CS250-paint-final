package com.paint100;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The launcher for Pain(t)
 */
public class PaintApplication extends Application {

    /**
     * The launcher for Pain(t)
     */
    public PaintApplication() {}
    /**
     * Launches the stage
     * @param primaryStage the main JavaFX stage that is being operated ob
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("Pain(t)");
        primaryStage.setScene(new Scene(root));

        //PaintController.addTab();
        primaryStage.show();


        //Credit to malex on stackOverflow for help with smart save
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                event.consume();

                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);

                Label label = new Label("Do you really want to quit?");

                Button okBtn = new Button("Don't Save");
                okBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.hide();
                        dialog.close();
                    }
                });

                Button saveBtn = new Button("Save");
                saveBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        PaintController.smartSave();
                        primaryStage.hide();
                        dialog.close();
                    }
                });

                Button cancelBtn = new Button("Cancel");
                cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();

                    }
                });

                GridPane grid = new GridPane();
                HBox hbBtn = new HBox(2);
                hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
                hbBtn.getChildren().addAll(saveBtn,okBtn, cancelBtn);
                grid.add(hbBtn, 1, 1);

                grid.add(label, 1, 0);
                dialog.setScene(new Scene(grid,200,100));
                dialog.showAndWait();
            }
        });

        //Functionality for hotkeys. The builtin hotkey controller within FXML wasn't used
        //as it did not function with all UI elements
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            //KeyCombination saveComb = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
            KeyCombination undoComb = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
            KeyCombination copyComb = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
            KeyCombination pasteComb = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent event) {
                //if (saveComb.match(event)) {
                  //  PaintController.smartSave();
                //}
                if (undoComb.match(event)) {
                    PaintController.staticUndo();
                }
                if (copyComb.match(event)) {
                    PaintController.copy();
                }
                if (pasteComb.match(event)) {
                    PaintController.paste();
                }
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}