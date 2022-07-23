package affichage;

import utils.Point;
import blocks.Block;

public class Camera {

    /* position en blocs du coin haut gauche de la caméra */
    private Point position;

    public static double LARGEUR = (double)Fenetre.LARGEUR/Block.TAILLE;
    public static double HAUTEUR = (double)Fenetre.HAUTEUR/Block.TAILLE;

    public Camera() {
        position = new Point(0, 0);
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public Point getPosition() {
        return new Point(getX(), getY());
    }

    public void setX(double x) {
        position.setX(x);
    }

    public void setY(double y) {
        position.setY(y);
    }

    public void setPosition(Point pos) {
        this.position.setX(pos.getX());
        this.position.setY(pos.getY());
    }
    
}
