package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Niveau;


public class MouseInventory extends Inventory {

    private Niveau niveau;

    public MouseInventory(Niveau niveau) {
        super(1); 
        this.niveau = niveau; 
    }

    @Override
    public void afficher() {
        Graphics2D g = Niveau.afficheur.g;
        if (this.getInventoryItem(0) != null) {
            // Affichage de l'item
            BufferedImage image = this.getInventoryItem(0).getItem().getSprite().getImage();
            g.drawImage(image, this.niveau.mouse.x, this.niveau.mouse.y, null);
        }    
    }
}
    
