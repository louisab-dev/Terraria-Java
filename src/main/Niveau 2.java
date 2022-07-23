package main;

import affichage.*;
import blocks.BlocksManager;
import items.InventoryManager;
import unites.UnitesManager;
import unites.PlayerInteraction;
import unites.Joueur;

public class Niveau implements Affichable {

    public Keyboard keyboard;
    public Mouse mouse;
    public static InputManager inputManager;

    /* l'afficheur utilisé pour le jeu */
    public static Afficheur afficheur = null;

    /* la caméra utilisée pour le jeu */
    public CameraManager cameraManager = null;

    private BlocksManager blocksManager;
    private UnitesManager unitesManager;
    private InventoryManager inventoryManager;
    private PlayerInteraction playerInteraction;

    /* le joueur du jeu */
    private Joueur joueur;

    public Niveau(Keyboard keyboard, Mouse mouse, Afficheur a) {

        // Déclarations des objets du niveau
        blocksManager = new BlocksManager(); 
        unitesManager = new UnitesManager(blocksManager);
        inventoryManager = new InventoryManager(this);
        playerInteraction = new PlayerInteraction(this);

        // Déclaration de la caméra
        this.cameraManager = new CameraManager();

        // Déclaration des entrées clavier et souris
        this.keyboard = keyboard;
        this.mouse = mouse;
        inputManager = new InputManager(mouse, keyboard);
        
        // Déclaration de l'afficheur
        Niveau.afficheur = a;

        joueur = unitesManager.getJoueur();
    }

    /* Met à jour la logique interne de tout les composants du niveau */
    public void maj() {
            
        // Mise à jour des entrées clavier
        keyboard.maj();
        mouse.maj();

        // Mise à jour du niveau
        unitesManager.maj(this, keyboard);  
        inventoryManager.maj(this);
        playerInteraction.maj(this);
        cameraManager.maj(joueur.getPosition());
    }

    /* Affichage de tout les composants du niveau */
    public void afficher() {
        
        // Affichage du niveau
        blocksManager.afficher();
        unitesManager.afficher();
        inventoryManager.afficher();
        playerInteraction.afficher();
    }

    /* Getters */
    public BlocksManager getBlocksManager() {
        return blocksManager;
    }

    public UnitesManager getUnitesManager() {
        return unitesManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public PlayerInteraction getPlayerInteraction() {
        return playerInteraction;
    }
}
