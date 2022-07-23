package main;

import affichage.Camera;
import affichage.CameraManager;
import utils.Point;
import blocks.Block;

public class InputManager {

    private Mouse mouse;
    private Keyboard keyboard;

    public InputManager(Mouse m, Keyboard k) {
        mouse = m;
        keyboard = k;
    }

    /* position absolue de la souris en bloc
    i.e. position dans le jeu et pas sur l'écran */
    public Point getMousePosition() {
        Camera camera = CameraManager.getCamera();

        double x = (double)mouse.getX()/Block.TAILLE + camera.getX() + 0.5; // conversion en double: division exacte et non entière
        double y = (double)mouse.getY()/Block.TAILLE + camera.getY() + 0.5;

        return new Point(x, y);
    }
    
}
