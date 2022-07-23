package items;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import affichage.Affichable;
import affichage.Sprite;
import main.Niveau;


public class InventoryWindow implements Affichable {
    
    private boolean isOpen;
    private PlayerInventory playerInventory;

    public InventoryWindow(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return this.isOpen;
    }
        
    public void afficher() {
        Graphics2D g = Niveau.afficheur.g;
        if (isOpen) {
            int xCounter = 0;
            int yCounter = 2;
            for (int i = this.playerInventory.getMaxSizeBarre(); i < this.playerInventory.maxSizeInventory; i++) {
                Rectangle r = new Rectangle(xCounter * Item.TAILLE + 5, yCounter * Item.TAILLE + 5, Item.TAILLE, Item.TAILLE);
                g.setColor(Color.BLACK);
                g.drawRect(r.x + 5, r.y + 5, r.width, r.height);
                if (this.playerInventory.getInventoryItem(i) != null) {
                    // Affichage de l'item
                    BufferedImage image = this.playerInventory.getInventoryItem(i).getItem().getSprite().getImage();
                    BufferedImage scaled = Sprite.scaleImage(image, 1.6f);
                    g.drawImage(scaled, r.getBounds().x + 7, r.getBounds().y + 7, null);

                    // Affichage de la quantité
                    g.setColor(Color.WHITE);
                    g.drawString(this.playerInventory.getInventoryItem(i).getQuantity() + "", r.x + 30, r.y + 30);
                }
                if (xCounter == this.playerInventory.getMaxSizeBarre() - 1) {
                    xCounter = 0;
                    yCounter++;
                } else {
                    xCounter++;
                }
            }
        }
    }
}