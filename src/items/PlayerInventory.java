package items;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import affichage.Camera;
import affichage.CameraManager;
import affichage.Sprite;
import blocks.*;
import main.Niveau;
import utils.Point;
import unites.Joueur;


public class PlayerInventory extends Inventory {

    private int maxSizeBarre;
    private int currentIndex;
    private Joueur joueur;

    public PlayerInventory(Joueur j) {
        super(32);  
        this.maxSizeBarre = 8;
        this.currentIndex = 0;
        this.joueur = j;
    }

    public int getMaxSizeBarre() {
        return maxSizeBarre;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }


    public void update(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public void afficher() {
        Graphics2D g = Niveau.afficheur.g;
        for (int i = 0; i < this.maxSizeBarre; i++) {
            if (i == currentIndex) {
                g.setColor(Color.RED);
            } 
            else {
                g.setColor(Color.BLACK);
            }
            Rectangle r = new Rectangle(i * Item.TAILLE, 10, Item.TAILLE, Item.TAILLE);
            g.drawRect(r.x + 5, r.y + 5, r.width, r.height);
            if (this.items[i] != null) {
                // Affichage de l'item
                BufferedImage image = this.items[i].getItem().getSprite().getImage();
                BufferedImage scaled = Sprite.scaleImage(image, 1.6f);
                g.drawImage(scaled, r.getBounds().x + 7, r.getBounds().y + 7, null);

                // Affichage de la quantité
                g.setColor(Color.WHITE);
                g.drawString(this.items[i].getQuantity() + "", r.getBounds().x + 30, r.getBounds().y + 30);                
            }
        }

        // Affichage de l'item sélectionné au centre de l'écran

        if (this.items[currentIndex] != null) {

            Point pos = this.joueur.H.getPosition();
            Camera camera = CameraManager.getCamera();
            double d_cam_x = (pos.getX()-camera.getX())*16;
            double d_cam_Y = (pos.getY()-camera.getY())*16;

            // Affichage de l'item
            int dxObjet;
            int dyObjet;

            if (this.joueur.isLookingRight()) {
                dxObjet = (int) (d_cam_x + 5);
                dyObjet = (int) (d_cam_Y + 5);
            }
            else {
                dxObjet = (int) (d_cam_x - 12);
                dyObjet = (int) (d_cam_Y + 5);
            }

            BufferedImage image = this.items[currentIndex].getItem().getSprite().getImage();
            BufferedImage scaled = Sprite.scaleImage(image, 0.5f);
            Niveau.afficheur.g.drawImage(scaled, dxObjet, dyObjet ,null);

        }
    }
}