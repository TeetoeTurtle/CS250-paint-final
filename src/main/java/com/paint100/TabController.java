package com.paint100;

import javafx.scene.control.Tab;
import javafx.scene.image.WritableImage;

import java.util.Stack;

/**
 * Holds all the information in regard to each tab. It holds the Tab object, the image of the GraphicsContext
 * and the undo/redo stacks for each tab
 */
public class TabController {

    private Tab tab;
    private WritableImage image;
    private Stack<WritableImage> undoStack = new Stack<>();
    private Stack<WritableImage> redoStack = new Stack<>();

    public TabController() {
        tab = new Tab();

    }

    public TabController(Tab tab, WritableImage image) {
        this.tab = tab;
        this.image = image;

    }

    /**
     * Returns the associated tab
     * @return tab
     */
    public Tab getTab() {
        return tab;
    }

    /**
     * Sets the tab
     * @param tab associated tab
     */
    public void setTab(Tab tab) {
        this.tab = tab;
    }

    /**
     * Returns the associated image
     * @return image
     */
    public WritableImage getImage() {
        return image;
    }

    /**
     * Sets the image
     * @param image associated image
     */
    public void setImage(WritableImage image) {
        this.image = image;
    }

    /**
     * Returns the undo stack
     * @return undoStack
     */
    public Stack<WritableImage> getUndoStack() {
        return undoStack;
    }

    /**
     * Returns the redo stack
     * @return redoStack
     */
    public Stack<WritableImage> getRedoStack() {
        return redoStack;
    }
}
