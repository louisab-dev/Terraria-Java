package unites;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.stream.DoubleStream;

import achievements.Achievement;
import achievements.AchievementManager;
import affichage.Affichable;
import blocks.*;
import items.Apple;
import items.InventoryItem;
import items.Item;
import items.PlayerInventory;
import main.Niveau;
import sound.SoundEffect;
import java.lang.Math;

import java.awt.Color;
import java.awt.BasicStroke;

public class PlayerInteraction implements Affichable {

    public Point blockCourant;
    private BlocksManager blocksManager;
    private PlayerInventory playerInventory;
    private UnitesManager unitesManager;
    private JoueurStats joueurStats;

    private Unite uniteCourante;
    private int PORTEE_JOUEUR = 4;
    private int PORTEE_MONSTRE = 2;

    private Block blocPrecedent = null;
    private double blocDegat = 0;
    private static int DURABILITE = 100;

    public PlayerInteraction(Niveau niveau) {
        this.blocksManager = niveau.getBlocksManager();
        this.playerInventory = niveau.getInventoryManager().getPlayerInventory();
        this.unitesManager = niveau.getUnitesManager();
        this.joueurStats = new JoueurStats();
        // this.achievementManager = new AchievementManager(this.joueurStats);
    }
    

    public void maj(Niveau niveau) {
        /* récupérer la position absolue de la souris en blocs */
        utils.Point mousePos = Niveau.inputManager.getMousePosition();

        /* décalage coin/centre */
        int posX = (int) Math.floor(mousePos.getX());
        int posY = (int) Math.floor(mousePos.getY());
        blockCourant = new Point(posX, posY);
        uniteCourante = unitesManager.getUnite(blockCourant.x, blockCourant.y);
        Joueur joueur = unitesManager.getJoueur();
        Point joueurPoint = new Point((int)joueur.getPosition().getX(),(int)joueur.getPosition().getY());

        // Get dammage sur le joueur
        for (int i = 1; i<unitesManager.unites.size() ; i++ ){
            Monstre monstre = (Monstre) unitesManager.unites.get(i);
            if(joueurPoint.distance(monstre.x,monstre.y)<monstre.getPortee()){
                joueur.removeHealth(monstre.getDamage());
            }

        }


        if (niveau.mouse.isRightDown()) {
            double Xjoueur = joueur.getPosition().getX();
            double Yjoueur =joueur.getPosition().getY();

            if (blockCourant.distance(Xjoueur,Yjoueur) < PORTEE_JOUEUR && uniteCourante == null ) {
                if (this.blocksManager.getBlock(blockCourant.x, blockCourant.y) == null) {
                    InventoryItem inventoryItem = this.playerInventory.getInventoryItem(this.playerInventory.getCurrentIndex());
                    if (inventoryItem != null && inventoryItem.getItem().estPosable()) {
                        Block block = (Block) inventoryItem.getItem().newItem(blockCourant.x, blockCourant.y);
                        boolean result = this.blocksManager.placer(block, blockCourant.x, blockCourant.y); // transtypage pourrait poser porblème
                        if (result) {
                            System.out.println("index courant : " + this.playerInventory.getCurrentIndex());
                            System.out.println("inventoryItem : " + inventoryItem.getItem().getClass().getSimpleName());
                            this.playerInventory.decrementQuantity(this.playerInventory.getIndex(inventoryItem));
                            this.joueurStats.addBlocsPlaces(block.getClass().getSimpleName());
                            SoundEffect.play("place");
                        }
                    }
                }
            }
        }

        if (niveau.mouse.isLeftDown()) {

            if (blockCourant.distance(joueur.getPosition().getX(), joueur.getPosition().getY()) < PORTEE_JOUEUR) {

                // Attaquer une unité
                if (uniteCourante != null) {
                    // Si l'unité n'est pas le joueur
                    if (!uniteCourante.getClass().getSimpleName().equals("Joueur")) {
                        // Gestion des dégâts
                        int dammage;
                        
                        InventoryItem iitem = this.playerInventory.getInventoryItem(this.playerInventory.getCurrentIndex());
                        if (iitem == null) {
                            dammage = 1;
                        }
                        else {
                            Item item = iitem.getItem();
                            dammage = item.getDammage();
                        }
                        
                        uniteCourante.removeHealth(dammage); 

                        if (uniteCourante.getHealth() <= 0) {
                            this.unitesManager.supprime(uniteCourante);
                            this.joueurStats.addMonstresTues(uniteCourante.getClass().getSimpleName());
                            SoundEffect.play("hit");
                        }
                    }
                }
                
                // Destruction des blocs
                else if (this.blocksManager.getBlock(blockCourant.x, blockCourant.y) != null) {
                    Block block = this.blocksManager.getBlock(blockCourant.x, blockCourant.y);
                    if (block != blocPrecedent) {
                        blocDegat = 0;
                    }
                    blocPrecedent = block;
                    InventoryItem iitem = this.playerInventory.getInventoryItem(this.playerInventory.getCurrentIndex());

                    int efficacite;

                    if (iitem != null) {
                        Item item = iitem.getItem();
                        efficacite = item.getEfficacite();
                    }
                    else {
                        efficacite = 1;
                    }
                    double durete = block.getDurete();
                    if (durete == 0) {
                        blocDegat = DURABILITE;
                    } 
                    else {
                        blocDegat += (1 / durete - 1) * efficacite;
                    }
                    if (blocDegat >= DURABILITE) {
                        this.blocksManager.retirer(block.getX(), block.getY());
                        if(block.getBlockID()==9){
                            double random = Math.random()*10;
                                if(random<1){
                                    this.playerInventory.addInventoryItem(new InventoryItem(new Apple(), 1));
                                }
                            }
                        this.playerInventory.addInventoryItem(new InventoryItem(block, 1));
                        this.joueurStats.addBlocsDetruits(block.getClass().getSimpleName());
                        SoundEffect.play("dirt");
                    }
                    else {
                        // System.out.println("durabilité du bloc : " + (DURABILITE-blocDegat));
                    }
                }
            } 
            else {
                InventoryItem iitem = this.playerInventory.getInventoryItem(this.playerInventory.getCurrentIndex());
                if(iitem != null){
                    Item item = iitem.getItem();
                    if(item instanceof Apple){
                        joueur.addHealth((int)(joueur.getMaxHealth()/10));
                        this.playerInventory.decrementQuantity(this.playerInventory.getIndex(iitem));

                    }
                } 
                blocPrecedent = null;
            }
        }
            
    }

    public JoueurStats getJoueurStats() {
        return this.joueurStats;
    }

    public void afficher() {

        Graphics2D g = Niveau.afficheur.g;

        if (uniteCourante != null) {
            g.setStroke(new BasicStroke(2));
            Niveau.afficheur.drawRect(blockCourant.x - 0.5,
            blockCourant.y - 0.5,
            1,
            1,
            Color.RED);
        }

        else if (blockCourant != null) {
            Color color;

            // Si la distance entre le joueur est le curseur est inférieur à la portée du joueur
            if (blockCourant.distance(unitesManager.getJoueur().getPosition().getX(), unitesManager.getJoueur().getPosition().getY()) < PORTEE_JOUEUR) {
                color = Color.WHITE;
            } 
            else {
                color = Color.BLACK;
            }
            
            g.setStroke(new BasicStroke(2));
            Niveau.afficheur.drawRect(blockCourant.x - 0.5,
            blockCourant.y - 0.5,
            1,
            1,
            color);
        }
    }
}