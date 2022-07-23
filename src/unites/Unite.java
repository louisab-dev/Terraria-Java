package unites;

import affichage.Affichable;
import affichage.CameraManager;
import affichage.Camera;
import main.Keyboard;
import main.Niveau;


import blocks.BlocksManager;
import utils.Vecteur;
import hitbox.*;
import utils.*;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class Unite implements Affichable, Collisionnable, hasHealth {

    public static double gd = 0.2; //déplacement selon y de la gravité à chaque tick (à intégrer différement)

    public rectanglehitbox H;
    private Vecteur vitesse; //vitesse de l'unite (en block/tick)(ce qui corréspond donc aussi au déplacement de l'unite pour le prochain tick)
    private Controleur controle;
    protected double x, y; //position de l'unite
    protected int health;
    protected int maxHealth;
    protected boolean lookingRight;
    protected boolean onGround;

    /**
     * Met à jour la position de l'objet en le translatant par le vecteur vitesse
     */
    private void updatePos(){
        this.H.translater(vitesse.getX(),vitesse.getY());
        this.vitesse.setX(0);
        this.vitesse.setY(0); 
    }

    public Unite(double  x, double y, double larg, double haut, Controleur controle) {
        this.H = new rectanglehitbox(larg, haut, x, y);
        this.vitesse = new Vecteur(0,0);
        this.controle = controle;
        this.health = 100;
        this.maxHealth = 100;
        this.lookingRight = true;
        this.onGround = false;
    }

    /**
     * Met à jour la position du joueur en fonction de la saisie au clavier, de la gravité et des
     * collisions avec les blocs
     * 
     * @param blocks le gestionnaire de blocs
     * @param keyboard l'objet clavier
     */
    public void maj(BlocksManager blocks, Keyboard keyboard) {
        this.vitesse.translater(0, gd); // gravité      
        Vecteur nv = this.controle.getDeplacement(keyboard);
        //System.out.println("vitesse1 : " + vitesse);
        Vecteur vcorr = Calculateur.calculCollision(this.H, vitesse, blocks);
        vcorr.setX(0);
        this.vitesse.addv(nv);
        this.vitesse.addv(vcorr);
        vcorr = Calculateur.calculCollision(this.H, vitesse, blocks);
        
       // System.out.println("correcteur de vitesse2 : " + vcorr);
        this.vitesse.addv(vcorr);
        this.updatePos();

        // maj de la poisition
        x = H.getPosition().getX();
        y = H.getPosition().getY();

        // maj de l'orientation
        if (nv.getX() > 0) {
            lookingRight = true;
        } else if (nv.getX() < 0) {
            lookingRight = false;
        }

        // maj de onGround
        if (vitesse.getY() > 0) {
            onGround = false;
        } else {
            onGround = true;
        }
    }

   /**
    * Dessine un rectangle sur l'écran
    */
    public void afficher() {
        Point pos = this.H.getPosition();

        Niveau.afficheur.drawRect(pos.getX()-H.getLargeur()/2,
        pos.getY()-H.getHauteur()/2,
        H.getLargeur(), 
        H.getHauteur(), 
        Color.BLACK);

        // Afficher les points de vie
        /*Niveau.afficheur.drawRect(pos.getX()-H.getLargeur()/2,
        pos.getY()-H.getHauteur()/2,
        H.getLargeur()*health/maxHealth,
        H.getHauteur()/10,
        Color.GREEN);*/
        afficherVie(pos);
    }
    //afficher les points de vie d'un joueur

    public void afficherVie(Point pos){
        double rapport_vie= (double)this.health/this.maxHealth;
        Color couleur;
        if(rapport_vie <=0.25)
            couleur = Color.red;
        else if(rapport_vie<=0.50){
            couleur = Color.orange;
        }
        else{
            couleur = Color.green;
        }
        
        Niveau.afficheur.drawRect(pos.getX()-H.getLargeur()/2,
        pos.getY()-H.getHauteur()/2 -0.3,
        H.getLargeur()*health/maxHealth,
        H.getHauteur()/10,
        couleur);
    }



    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Si l'objet est dans le champ de vision de la caméra, alors il est visible
     * 
     * @return Un booléen
     */
    public boolean estVisible() {
        Camera camera = CameraManager.getCamera();
        if (H instanceof rectanglehitbox) {
            rectanglehitbox hitbox = (rectanglehitbox) H;
            Point pos = hitbox.getPosition();
            if (pos.getX()+hitbox.getLargeur() >= camera.getX() & pos.getX()-1 <= camera.getX()+Camera.LARGEUR) {
                if (pos.getY()+hitbox.getHauteur() >= camera.getY() && pos.getY()-1 <= camera.getY()+Camera.HAUTEUR) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    public polygonhitbox getHitbox() {
        return H;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, (int) H.getLargeur(), (int) H.getHauteur());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void removeHealth(int health) {
        this.health -= health;
    }

    public void addHealth(int health) {
        if(this.health+health <=this.maxHealth){
            this.health += health;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setAlive(boolean isAlive) {
        if (!isAlive) {
            this.health = 0;
        }
    }

    public boolean isLookingRight() {
        return lookingRight;
    }

    public void setLookingRight(boolean lookingRight) {
        this.lookingRight = lookingRight;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setControle(Controleur controle) {
        this.controle = controle;
    }

    public Controleur getControleur() {
        return controle;
    }
}
