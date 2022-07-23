package hitbox;
import utils.*;
import java.awt.event.*;

public class polygonhitbox extends hitbox{

    private Polygone hitbox;
    private double x;
    private double y;


    /**Instancier une hitbox à partir de sa position et sa forme
     * NE PAS intégrer la position dans le polygone, mais bien avec le point.
     * @param h Le polygone représentant la forme de la hitbox
     * @param p point représentant la position de la hitbox
     */
    public polygonhitbox(Polygone h, Point p){
        new polygonhitbox(h, p.getX(), p.getY());
    }


    /**Instancier une hitbox à partir de sa position et sa forme
     * NE PAS intégrer la position dans le polygone, mais bien avec posx et posy.(Le polygone doit être centré en 0)
     * @param h Le polygone représentant la forme de la hitbox
     * @param posx position en x de la hitbox
     * @param posy position en y de la hitbox
     */
    public polygonhitbox(Polygone h, double posx, double posy){
        h.translaterPoints(posx,posy);
        this.hitbox= h;
        this.x = posx;
        this.y = posy;
    }

    //Retourne le volume de la hitbox
    //public double getVolume(){

    //}

    //Retourne les points qui composent la hitbox
    public Point[] getPoints(){
        return this.hitbox.getPoint();
    }

    public Polygone getPolygone(){
        return this.hitbox;
    }
    
    
    /**
     * Renvoie le MTV (Minimum Translation Vector) de la collision entre les deux hitbox, le signe
     * du vecteur étant le même que le signe du produit scalaire entre le MTV et le vecteur du centre
     * de la première hitbox au centre de la deuxième hitbox
     * 
     * @param h2 l'autre hitbox
     * @return Le MTV (vecteur de translation minimum)
     */
    public Vecteur getCollisionVector(polygonhitbox h2){
        double[] d = SAT.getMTV(h2.getPolygone(), this.hitbox);
        Vecteur vc = new Vecteur(d[0], d[1]);
        Vecteur vectsigne2 = new Vecteur(this.getPosition(), h2.getPosition());
        double s = Math.signum(vectsigne2.pDot(vc));
        if (Math.abs(s)>0){
            vc.setX(vc.getX()*s);
            vc.setY(vc.getY()*s);
        }
        return vc;
    }

   /**
    * Il vérifie si les deux polygones entrent en collision.
    * 
    * @param h2 le polygonhitbox avec lequel vous voulez vérifier la collision
    * @return La valeur booléenne qui indique si les deux polygones entrent en collision.
    */
    public boolean isColliding(polygonhitbox h2){
        double[] d = SAT.getMTV(h2.getPolygone(), this.hitbox);
        return (d[0]!=0 || d[1]!=0);
    }

    public void overlapped(polygonhitbox h2){
        super.onOverlap.actionPerformed(new ActionEvent(h2,ActionEvent.ACTION_PERFORMED,""));
    }

    public void hit(){
        super.onHit.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,""));
    }

    /**Obtenir la position de la hitbox
     * @return la position de la hitboix
     */
    public Point getPosition(){
        return new Point(this.x,this.y);
    }

    /**
     * Translate la hitbox par les  valeurs dx et dy
     * 
     * @param dx Le montant pour déplacer selon x
     * @param dy Le montant pour déplacer selon y
     */
    public void translater(double dx, double dy){
        this.x += dx;
        this.y += dy;
        this.hitbox.translaterPoints(dx, dy);
    }

    public polygonhitbox copie(){
        Polygone  p = this.hitbox.copie();
        return new polygonhitbox(p, 0,0);
    }

}
