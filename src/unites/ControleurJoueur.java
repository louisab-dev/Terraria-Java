package unites;
import main.Key;
import main.Keyboard;
import utils.Vecteur;

public class ControleurJoueur implements Controleur {

    private final static double jump = 0.4;
    private Joueur joueur;

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public Vecteur getDeplacement(Keyboard keyboard) {
        Vecteur v = new Vecteur(0, 0);
        if (keyboard.isKeyDown(Key.LEFT)) {
            v.addv(new Vecteur(-0.09, 0));
        }
        if (keyboard.isKeyDown(Key.RIGHT)) {
            v.addv(new Vecteur(0.09, 0));
        }
        if (keyboard.isKeyDown(Key.UP)) {
            if (joueur.isOnGround()) {
                v.addv(new Vecteur(0, -jump));
            }
            else {
                System.out.println("en l'air");
            }
        }
        if(keyboard.isKeyReleased(Key.LEFT)){
            System.out.println("release");
        }
        return v;
    }

}
