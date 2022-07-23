package items;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import affichage.Sprite;
import blocks.*;
import main.Niveau;


public class PlayerInventory extends Inventory {

    private int maxSizeBarre;
    private int currentIndex;

    public PlayerInventory() {
        super(32);  
        this.maxSizeBarre = 8;
        this.currentIndex = 0;

        // pour tester
        this.addInventoryItem(new InventoryItem(new Coal(0, 0), 5));
        this.addInventoryItem(new InventoryItem(new Grass(0, 0), 5));
        this.addInventoryItem(new InventoryItem(new Dirt(0, 0), 5));
    }

    public int getMaxSizeBarre() {
        return maxSizeBarre;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public PlayerInventory getInventoryBarre() {
        PlayerInventory inventoryBarre = new PlayerInventory();
        for (int i = 0; i < this.maxSizeBarre; i++) {
            inventoryBarre.addInventoryItem(this.getInventoryItem(i));
        }
        return inventoryBarre;
    }

    public PlayerInventory getInventoryReste() {
        PlayerInventory inventoryReste = new PlayerInventory();
        for (int i = this.maxSizeBarre; i < this.maxSizeInventory; i++) {
            inventoryReste.addInventoryItem(this.getInventoryItem(i));
        }
        return inventoryReste;
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
    }
}