package items;

import main.Niveau;
import main.Key;

import java.awt.Rectangle;
import java.util.HashMap;

import affichage.Affichable;
import unites.Joueur;


public class InventoryManager implements Affichable {

    private PlayerInventory playerInventory;
    private MouseInventory mouseInventory;
    private InventoryWindow inventoryWindow;
    private int currentIndex;
    private int selectedIndex;
    private HashMap<Rectangle, Integer> rectangleToIndex;

    public InventoryManager(Niveau niveau, Joueur joueur) {
        this.playerInventory = new PlayerInventory(joueur);
        this.mouseInventory = new MouseInventory(niveau);
        this.inventoryWindow = new InventoryWindow(playerInventory);
        this.rectangleToIndex = new HashMap<Rectangle, Integer>();
        initRectangleToIndex();
    }

    public void initRectangleToIndex() {

        for (int i = 0; i < this.playerInventory.getMaxSizeBarre(); i++) {
            Rectangle r = new Rectangle(i * Item.TAILLE + 5, 5, Item.TAILLE, Item.TAILLE);
            this.rectangleToIndex.put(r, i);
        }

        int xCounter = 0;
        int yCounter = 2;
        for (int i = this.playerInventory.getMaxSizeBarre(); i < this.playerInventory.maxSizeInventory; i++) {
            Rectangle r = new Rectangle(xCounter * Item.TAILLE + 5, yCounter * Item.TAILLE + 5, Item.TAILLE, Item.TAILLE);
            this.rectangleToIndex.put(r, i);
            xCounter++;
            if (xCounter == 8) {
                xCounter = 0;
                yCounter++;
            }
        }
    }

    public void maj(Niveau niveau) {
        // currentIndex = ((currentIndex + niveau.mouse.scroll) % this.playerInventory.getMaxSizeBarre() + this.playerInventory.getMaxSizeBarre()) % this.playerInventory.getMaxSizeBarre();
        currentIndex = niveau.mouse.scroll;

        if (niveau.keyboard.isKeyDown(Key.OpenInventory)) {
            this.inventoryWindow.setOpen(!this.inventoryWindow.isOpen());
        }

        if (niveau.keyboard.isKeyDown(Key.Inventory0)) {
            System.out.println("Inventory0");
            currentIndex = 0;
        }

        if (niveau.keyboard.isKeyDown(Key.Inventory1)) {
            System.out.println("Inventory1");
            currentIndex = 1;
        }
        // ETC JUSQU'A 9

        if (this.inventoryWindow.isOpen()) {

            if(niveau.mouse.isLeftDown()) {
                this.selectedIndex = -1;
                for (Rectangle r : this.rectangleToIndex.keySet()) {
                    if (r.contains(niveau.mouse.getX(), niveau.mouse.getY())) {
                        this.selectedIndex = this.rectangleToIndex.get(r);
                        break;
                    }
                }
                
                // Si on est sur une case de l'inventaire
                if (this.selectedIndex != -1) {

                    // Si on a cliqué sur une case vide
                    if (this.playerInventory.getInventoryItem(this.selectedIndex) == null) {

                        // Si on a un item dans la souris
                        if (this.mouseInventory.getInventoryItem(0) != null) {

                            // On ajoute l'item de la souris
                            this.playerInventory.addInventoryItem(selectedIndex, this.mouseInventory.getInventoryItem(0));
                            // On retire l'item de la souris
                            this.mouseInventory.removeInventoryItem(0);
                        }
                    }
                    // Si la case n'est pas vide
                    else {

                        // Si on a un item dans la souris
                        if (this.mouseInventory.getInventoryItem(0) != null) {

                            // Si les deux items sont de même type
                            if (this.playerInventory.getInventoryItem(this.selectedIndex).getItem().getClass() == this.mouseInventory.getInventoryItem(0).getItem().getClass()) {
                                // On ajoute la quantité de l'item de la souris au nombre d'items de la case
                                this.playerInventory.getInventoryItem(this.selectedIndex).addQuantity(this.mouseInventory.getInventoryItem(0).getQuantity());
                                // On retire l'item de la souris
                                this.mouseInventory.removeInventoryItem(0);
                            }
                            // Sinon on interverti les deux items
                            else {
                                InventoryItem temp = this.playerInventory.getInventoryItem(this.selectedIndex);
                                this.playerInventory.setInventoryItem(this.selectedIndex, this.mouseInventory.getInventoryItem(0));
                                this.mouseInventory.setInventoryItem(0, temp);
                            }
                        }
                        // Sinon on ajoute l'item dans la souris
                        else {
                            this.mouseInventory.addInventoryItem(this.playerInventory.getInventoryItem(this.selectedIndex));
                            this.playerInventory.removeInventoryItem(this.selectedIndex);
                        }
                    }
                }
            }
        }
        else if (!this.inventoryWindow.isOpen() && this.mouseInventory.getInventoryItem(0) != null) {

            // Drop l'item

            // Le supprimer de la souris
            this.mouseInventory.removeInventoryItem(0);
        }
        this.playerInventory.update(currentIndex);
    }


    @Override
    public void afficher() {
        this.inventoryWindow.afficher();
        this.mouseInventory.afficher();
        this.playerInventory.afficher();
    }

    // Getters
    public PlayerInventory getPlayerInventory() {
        return this.playerInventory;
    }

    public MouseInventory getMouseInventory() {
        return this.mouseInventory;
    }
}
