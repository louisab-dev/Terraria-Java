package unites;

import affichage.Affichable;
import main.Keyboard;
import main.Niveau;
import java.awt.Rectangle;

import java.util.ArrayList;

import blocks.BlocksManager;


public class UnitesManager implements Affichable {

    public ArrayList<Unite> unites;
    private BlocksManager blocks;

    private Joueur joueur;

    public UnitesManager(BlocksManager blocks) {
        unites = new ArrayList<Unite>();
        joueur = new Joueur(10, 0);
        unites.add(joueur);
        this.blocks = blocks;

        //unites.add(new unites.MonstreTest(30, 0));
        unites.add(new unites.Monstre(20,0,1,2,"unites/creep.png"));
        unites.add(new unites.Monstre(30,0,1,3,"unites/moeil.png"));
    }

    /**
    * Pour chaque unite de la liste des unites, met à jour l'unite.
    * @param niveau L'objet de niveau.
    * @param keyboard L'objet clavier.
    */
    public void maj(Niveau niveau, Keyboard keyboard) {
        double random1 = Math.random()*1800;
        
        if (random1 <= 1){
            unites.add(new unites.Monstre(Math.random()*80+joueur.getPosition().getX() -40,0,1,2,"unites/creep.png"));
        }
        else if(random1<=2){
            unites.add(new unites.Monstre(Math.random()*80+joueur.getPosition().getX() -40,0,1,3,"unites/moeil.png"));
        }
        for (Unite unite : unites) {
            unite.maj(blocks, keyboard);
        }
    }

    /**
     * Affiche toutes les unites visibles
     */
    public void afficher() {
        for(Unite unite : unites){
            if (unite.estVisible() && unite.isAlive()) {
                unite.afficher();
            }
        }
    }

    /**
     * Ajoute un unite à la liste des unites
     * 
     * @param u L'unité à ajouter à la liste des unités.
     */
    public void addUnite(Unite u){
        unites.add(u);
    }

    /** 
     * Retourne l'unité présente à la position x,y
     * @param x La position x
     * @param y La position y
     */
    public Unite getUnite(int xSouris, int ySouris) {
        for (Unite unite : unites) {
            Rectangle r = unite.getRectangle();
            if (r.contains(xSouris, ySouris)) {
                // On considère que la souris ne peut être que sur une unite
                return unite;
            }
        }
        return null;
    }

    /** 
     * Supprime l'unité de la liste des unites
     * @param u L'unité à supprimer
     */
    public void supprime(Unite u){
        unites.remove(u);
    }

    /**
     * Renvoie le joueur
     * 
     * @return L'attribut joueur de la classe.
     */
    public Joueur getJoueur() {
        return joueur;
    }

}
