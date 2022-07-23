package main;

import affichage.*;
import blocks.BlocksManager;
import items.InventoryManager;
import unites.UnitesManager;
import unites.PlayerInteraction;
import unites.Joueur;
import java.util.ArrayList;

import achievements.Achievement;
import achievements.AchievementManager;
import utils.Point;
import blocks.chunkPos;
import java.lang.Math;

public class Niveau implements Affichable {

    private ArrayList<chunkPos> chunks = new ArrayList<chunkPos>(100); //chunks courrant
    private ArrayList<chunkPos> nChunks = new ArrayList<chunkPos>(100); //chunks à charger
    private ArrayList<chunkPos> aChunks = new ArrayList<chunkPos>(100); //chunks à décharger

    public static final int CHUNKSIZE = 16;
    private static final int DISTANCE_CHARGE = 3;
    private static final int DISTANCE_SAVE = 6;
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
    private AchievementManager achievementManager;

    /* le joueur du jeu */
    private Joueur joueur;

    public Niveau(Keyboard keyboard, Mouse mouse, Afficheur a, boolean modeDecouverte) {

        // Déclarations des objets du niveau
        blocksManager = new BlocksManager(); 
        unitesManager = new UnitesManager(blocksManager);
        joueur = unitesManager.getJoueur();
        inventoryManager = new InventoryManager(this, joueur);
        playerInteraction = new PlayerInteraction(this);
        achievementManager = new AchievementManager(this, playerInteraction.getJoueurStats());

        // Déclaration de la caméra
        this.cameraManager = new CameraManager();

        // Déclaration des entrées clavier et souris
        this.keyboard = keyboard;
        this.mouse = mouse;
        inputManager = new InputManager(mouse, keyboard);
        
        // Déclaration de l'afficheur
        Niveau.afficheur = a;
    }

    /* Met à jour la logique interne de tout les composants du niveau */
    public void maj() {
            
        // Mise à jour des entrées clavier
        keyboard.maj();
        mouse.maj();

        chunksMaj();

        blocksManager.cgChunk(nChunks, aChunks);
        inventoryManager.maj(this);
        unitesManager.maj(this,keyboard);
        playerInteraction.maj(this);
        cameraManager.maj(joueur.getPosition());
        achievementManager.maj();
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

    private void chunksMaj(){
        Point posj = joueur.getPosition();
        this.chunks.removeAll(aChunks);
        this.chunks.addAll(nChunks);       
        int chunkCX = (int) Math.floor(posj.getX()/CHUNKSIZE);
        int chunkCY = (int) Math.floor(posj.getY()/CHUNKSIZE);
        aChunks.clear();
        nChunks.clear();

        ArrayList<chunkPos> tchunks = new ArrayList<chunkPos>(400);

        for(int i = -DISTANCE_SAVE; i <= DISTANCE_SAVE; i++){
            for(int j = -DISTANCE_SAVE; j<= DISTANCE_SAVE; j++){
                chunkPos ct = new chunkPos(chunkCX + i, chunkCY + j);
                if(chunks.contains(ct)) {
                    tchunks.add(ct);
                }
            }
        }

        //System.out.println("Chunk à charger");
        for(int i = -DISTANCE_CHARGE; i <= DISTANCE_CHARGE; i++){
            for(int j = -DISTANCE_CHARGE; j<= DISTANCE_CHARGE; j++){
                chunkPos ct = new chunkPos(chunkCX + i, chunkCY + j);
                if(!chunks.contains(ct)) {
                    nChunks.add(ct);
                    //System.out.println("Chunk à charger : " + ct[0] + " " + ct[1]);
                }
            }
        }

        chunks.removeAll(tchunks); //on enleve de chunk tout les chunks en commun avec ceux à charger, il ne reste donc que les anciens chunks, qui ne sont pas à charger, donc qui sont à décharger
        aChunks = new ArrayList<chunkPos>(chunks); //c'est donc achunks
        //System.out.println("tailles de achunks " + aChunks.size());
        //System.out.println("tailles de nchunks " + nChunks.size());
        chunks.addAll(tchunks); //on remet chunks dans son état original (pourquoi pas)
    } 
}
