package unites;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import affichage.CameraManager;
import main.Niveau;
import utils.Point;
import affichage.Camera;


public class Monstre extends Unite {
    protected String pathName ;
    protected int damage;
    protected int portee;

    public Monstre(double x, double y, int damage, int portee,String path) {
        super(x, y, 1, 2, new ControleurAleatoire());
        this.pathName= path;
        this.damage = damage;
        this.portee = portee;
    }
   
    @Override
    public void afficher(){
        Point pos = this.H.getPosition();
        Camera camera = CameraManager.getCamera();
        double d_cam_x = (pos.getX()-camera.getX())*16;
        double d_cam_Y = (pos.getY()-camera.getY())*16;

        try {
            Niveau.afficheur.g.drawImage(ImageIO.read(new File(this.pathName)),(int) (d_cam_x -8), (int)(d_cam_Y -16),null);
        } catch (IOException e) {
            System.out.println("Error loading playerImage : il faut surement rajouter /unites au lieu de unites dans Joueur.java");
        }
        afficherVie(pos);
        
    }

    public int getDamage(){
        return this.damage;
    }

    public int getPortee(){
        return this.portee;
    }

    
    
}


