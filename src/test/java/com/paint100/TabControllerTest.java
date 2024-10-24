package com.paint100;

import javafx.scene.control.Tab;
import javafx.scene.image.WritableImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabControllerTest {
    TabController tc = new TabController(new Tab(), new WritableImage(1,1));

    @Test
    @DisplayName("Testing tab class type")
    void getTab() {
        assertEquals(tc.getTab().getClass(), Tab.class);
    }

    @Test
    void setTab() {
    }

    @Test
    @DisplayName("Testing what an error looks like")
    void getImage() {
        assertEquals(tc.getImage().getClass(), Tab.class);

    }

    @Test
    void setImage() {
    }

    @Test
    @DisplayName("Testing array indexes")
    void getUndoStack() {
        tc.getUndoStack().add(tc.getImage());;
        assertEquals(tc.getUndoStack().get(0), tc.getImage());

    }

    @Test
    void getRedoStack() {
    }
}