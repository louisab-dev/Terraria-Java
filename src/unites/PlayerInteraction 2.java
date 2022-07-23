package unites;

import java.awt.Graphics2D;
import java.awt.Point;

import affichage.Affichable;
import blocks.*;
import items.InventoryItem;
import items.PlayerInventory;
import main.Niveau;

import java.awt.Color;
import java.awt.BasicStroke;

public class PlayerInteraction implements Affichable {

    public Point blockCourant;
    private BlocksManager blocksManager;
    private PlayerInventory playerInventory;

    public PlayerInteraction(Niveau niveau) {
        this.blocksManager = niveau.getBlocksManager();
        this.playerInventory = niveau.getInventoryManager().getPlayerInventory();
    }
    

    public void maj(Niveau niveau) {
        /* récupérer la position absolue de la souris en blocs */
        utils.Point mousePos = Niveau.inputManager.getMousePosition();

        /* décalage coin/centre */
        int posX = (int) mousePos.getX();
        int posY = (int) mousePos.getY();
        blockCourant = new Point(posX, posY);
        
        if (niveau.mouse.isRightDown()) {
            if (this.blocksManager.getBlock(blockCourant.x, blockCourant.y) == null) {
                InventoryItem inventoryItem = this.playerInventory.getInventoryItem(this.playerInventory.getCurrentIndex());
                if (inventoryItem != null && inventoryItem.getItem().estPosable()) {
                    Block block = (Block) inventoryItem.getItem().newItem(blockCourant.x, blockCourant.y);
                    boolean result = this.blocksManager.placer(block, blockCourant.x, blockCourant.y); // transtypage pourrait poser porblème
                    if (result) {
                        this.playerInventory.decrementQuantity(this.playerInventory.getIndex(inventoryItem));
                    }
                }
            }
        }
        if (niveau.mouse.isLeftDown()) {
            if (this.blocksManager.getBlock(blockCourant.x, blockCourant.y) != null) {
                Block block = this.blocksManager.getBlock(blockCourant.x, blockCourant.y);
                this.blocksManager.retirer(block.getX(), block.getY());
                this.playerInventory.addInventoryItem(new InventoryItem(block, 1));
            }
        }
    }

    public void afficher() {

        Graphics2D g = Niveau.afficheur.g;
        if (blockCourant != null) {
            g.setStroke(new BasicStroke(2));
            Niveau.afficheur.drawRect(blockCourant.x - 0.5,
            blockCourant.y - 0.5,
            1,
            1,
            Color.BLACK);
        }
    }
}