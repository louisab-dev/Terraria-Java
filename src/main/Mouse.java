package main;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseMotionListener, MouseListener, MouseWheelListener {

    public static final int LEFT = 0; 
    public static final int RIGHT = 1;
    private final static int[] BUTTON_CODES = {MouseEvent.BUTTON1, MouseEvent.BUTTON3};

    public boolean[] buttons = new boolean[BUTTON_CODES.length];
    public boolean[] buttonsLast = new boolean[BUTTON_CODES.length];
    public boolean[] buttonsClick = new boolean[BUTTON_CODES.length];
    public boolean[] testClick = new boolean[BUTTON_CODES.length];

    public int x, y, scroll;

    public void maj() {
        for (int i = 0; i < buttons.length; i++) {
            buttonsClick[i] = buttons[i] && !buttonsLast[i];
            buttonsLast[i] = buttons[i];
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < BUTTON_CODES.length; i++) {
            if (e.getButton() == BUTTON_CODES[i]) {
                buttons[i] = true;
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < BUTTON_CODES.length; i++) {
            if (e.getButton() == BUTTON_CODES[i]) {
                buttons[i] = false;
                break;
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < BUTTON_CODES.length; i++) {
            if (e.getButton() == BUTTON_CODES[i]) {
                testClick[i] = true;
                break;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public boolean isLeftDown() {
        return buttons[LEFT];
    }

    public boolean isRightDown() {
        return buttons[RIGHT];
    }

    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }

    /** Permet de changer le currentIndex de l'inventaire du joueur, les valeurs sont hard codées il faudra le changer */
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            if (scroll <= 0) {
                scroll = 7;
            }
            else {
                scroll--;
            }
        }
        else {
            if (scroll >= 7) {
                scroll = 0;
            }
            else {
                scroll++;
            }
        }
    }
}
