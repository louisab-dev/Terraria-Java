package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener {
    

    public static boolean[] keys, keysLast, keysPressed, keysReleased;
    private static final int NUM_KEYS = 256;

    public Keyboard() {
        keys = new boolean[NUM_KEYS];
        keysLast = new boolean[NUM_KEYS];
        keysPressed = new boolean[NUM_KEYS];
        keysReleased = new boolean[NUM_KEYS];
    }

    public void maj() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keysPressed[i] = keys[i] && !keysLast[i];
            keysReleased[i] = !keys[i] && keysLast[i];
            keysLast[i] = keys[i];
        }
    }

    public boolean isInventoryOpen() {
        return keysReleased[73];
    }

    public boolean isKeyDown(Key key) {
        return keys[key.code];
    }

    public boolean isKeyPressed(Key key) {
        return keysPressed[key.code];
    }

    public boolean isKeyReleased(Key key) {
        return keysReleased[key.code];
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {
    }
}