package affichage;
import utils.Point;

public class CameraManager {
    
    private static Camera cameraUtilisee;

    public CameraManager() {
        cameraUtilisee = new Camera();
    }   

    public void maj(Point joueurPos) {
        double x = joueurPos.getX() - Camera.LARGEUR/2;
        double y = joueurPos.getY() - Camera.HAUTEUR/2;
        cameraUtilisee.setPosition(new Point(x, y));
    }

    public static Camera getCamera() {
        return cameraUtilisee;
    }
    
}
