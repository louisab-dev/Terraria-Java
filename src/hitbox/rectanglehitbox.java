package hitbox;
import utils.*;

public class rectanglehitbox extends polygonhitbox{

    double largeur,hauteur;

    //public rectanglehitbox(Point a1, Point a2, Point a3, Point a4, double posx, double posy){
     //   super(new Polygone(new Point[] {a1,a2,a3,a4}), posx,posy); //impossible de vérifier que c'est bien un rectangle, puisque qu'il faut appeler super en tout premier. Peut-être switch sur une fabrique static ?
    //}
 
    public rectanglehitbox(Polygone h, double posx, double posy){
        super(h,posx,posy);
    }

    public rectanglehitbox(double nlargeur, double nhauteur, double posx, double posy){
        super(new Polygone(new Point[] {new Point(-nlargeur/2, nhauteur/2), new Point(nlargeur/2, nhauteur/2), new Point(nlargeur/2,-nhauteur/2), new Point(-nlargeur/2, -nhauteur/2)})
        ,posx,posy);
        this.largeur = nlargeur;
        this.hauteur = nhauteur;
    }

    /**
     * Retourne la valeur de la variable largeur
     * 
     * @return La valeur de la variable largeur.
     */
    public double getLargeur(){
        return largeur;
    }

    /**
     * Retourne la hauteur du rectangle
     * 
     * @return La hauteur du rectangle.
     */
    public double getHauteur(){
        return hauteur;
    }

    /** Renvoie une hitbox dont la position x,y est translatée de dx,dy
     * @param dx translation selon x
     * @param dy tanslation selon y
    */
    public rectanglehitbox HBtranslatee(double dx, double dy){
        rectanglehitbox h = this.copie();
        h.translater(dx, dy);
        return h;
    }

    /**
     * Renvoie une copie du rectanglehitbox
     * 
     * @return Un nouvel objet rectanglehitbox.
     */
    public rectanglehitbox copie(){
        Polygone  p = super.getPolygone().copie();
        return new rectanglehitbox(p,0,0);
    }
}
