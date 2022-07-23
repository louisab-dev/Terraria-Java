package main;

import java.awt.event.KeyEvent;

public enum Key {
    
    UP(KeyEvent.VK_SPACE),
    LEFT(KeyEvent.VK_Q),
    RIGHT(KeyEvent.VK_D),
    Inventory0(KeyEvent.VK_0),
    Inventory1(KeyEvent.VK_1),
    OpenInventory(KeyEvent.VK_I);

    int code;

    Key(int code) {
        this.code = code;
    }

}
