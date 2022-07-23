package unites;
import main.Keyboard;
import utils.Vecteur;

public class ControleurImmobile implements Controleur{

    public ControleurImmobile() {
    }

    @Override
    public Vecteur getDeplacement(Keyboard keyboard) {
        return new Vecteur(0, 0);
    }
}
