package unites;
import main.Keyboard;
import utils.Vecteur;
import java.util.Random;

public class ControleurAleatoire implements Controleur {

    public ControleurAleatoire() {
    }

    @Override
    public Vecteur getDeplacement(Keyboard keyboard) {
        Random random = new Random();
        double vx = 0;
        // Toujours négatif, les monstres ne peuvent que sauter
        double vy = 0;
        // à adapter
        if (Math.abs(random.nextGaussian())>3.0) {vy = - 2.001;} 
        if (Math.abs(random.nextGaussian())>2.0) {vx = Math.random()*2 -1;}
        return new Vecteur(vx,vy);
    }
}
