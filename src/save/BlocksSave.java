package save;

import java.util.ArrayList;
import blocks.Block;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import main.Niveau;
import blocks.Chunk;
import blocks.chunkPos;
import blocks.Air;
import java.lang.Math;

public class BlocksSave implements Save {

    public static void main (String[] args) {
        BlocksSave save = new BlocksSave();
    }

    public ArrayList<Chunk> chunks;
    public static String fileDir = "../game/saves/world";
    private static File savefile = new File(fileDir + "/chunks.s");
    private static File annuairefile = new File(fileDir + "/annuaire.s");
    private RandomAccessFile saveaccess;
    private RandomAccessFile annuaireaccess;
    private static int MAX_INT_CHAR = (int) Math.pow(2, 16);

    public BlocksSave(ArrayList<Chunk> nchunks) {
        this.chunks = nchunks;
        constructorFacto();
    }

    public BlocksSave(Chunk chunk) {
        ArrayList<Chunk> arlb = new ArrayList<Chunk>(1);
        arlb.add(chunk);
        this.chunks = arlb;
        constructorFacto();
    }

    public BlocksSave(){
        ArrayList<Chunk> arlb = new ArrayList<Chunk>(10);
        this.chunks = arlb;
        constructorFacto();
        
    }

    
    /**
     * Crée les dossiers et les fichiers s'ils n'existent pas, puis crée les objets RandomAccessFile qui seront
     * utilisés pour lire et écrire dans les fichiers
     */
    private void constructorFacto(){
        if(savefile.exists() && annuairefile.exists()){
            try {
                this.saveaccess = new RandomAccessFile(savefile, "rw");
                this.annuaireaccess = new RandomAccessFile(annuairefile, "rw");
            } catch (FileNotFoundException e) {
                System.out.println("Problème d'instanciation du flux de sauvegarde pour BlocksSave");
            }
        }
        else{
            try{
                File t = new File(fileDir);
                t.mkdirs();
                savefile.createNewFile();
                annuairefile.createNewFile();
                try {
                    this.saveaccess = new RandomAccessFile(savefile, "rw");
                    this.annuaireaccess = new RandomAccessFile(annuairefile, "rw");
                } catch (FileNotFoundException e) {
                    System.out.println("Problème d'instanciation du flux de sauvegarde pour BlocksSave");
                }
            }
            catch (IOException e){
                System.out.println("Erreur création des fichiers de save de block : " + e);
            }
        }
    }


  /**
   * Ecrit les blocs d'un chunk dans le fichier de sauvegarde
   * 
   * @param c le chunk à sauver
   * @param blockref Le bloc de référence pour le chunk.
   */
    private void ecritureChunk(Chunk c) {
        //System.out.println("Chunk à sauvegarder :" + Math.floorMod((int) c.pos.getX(), MAX_INT_CHAR) + ":" + Math.floorMod((int) c.pos.getY(), MAX_INT_CHAR));
        for (int k = 0; k < Niveau.CHUNKSIZE; k++) {
            for (int j = 0; j < Niveau.CHUNKSIZE; j++) {
                Block bc = c.chunk[k][j];
                if (!(bc instanceof Air)) {
                    try {
                        saveaccess.writeByte(bc.getBlockID());
                        saveaccess.writeByte(Math.floorMod(bc.getX(), Niveau.CHUNKSIZE));
                        saveaccess.writeByte(Math.floorMod(bc.getY(), Niveau.CHUNKSIZE));
                    } catch (IOException e) {
                        System.out.println("Erreur enregistrement du block : " + bc);
                    }
                } else {
                    try {
                        saveaccess.writeByte(0);
                        saveaccess.writeByte(Math.floorMod(bc.getX(), Niveau.CHUNKSIZE));
                        saveaccess.writeByte(Math.floorMod(bc.getY(), Niveau.CHUNKSIZE));
                    } catch (IOException e) {
                        System.out.println("Erreur enregistrement du block : null");
                    }
                }
            }
        }
        /* int cx = Math.floorMod((int) c.pos.getX(), MAX_INT_CHAR);
        int cy = Math.floorMod((int) c.pos.getY(), MAX_INT_CHAR);
        if (cx == MAX_INT_CHAR-1 && cy == 5) {
            System.out.println("Déchargé");
            for (int k = 0; k < Niveau.CHUNKSIZE; k++) {
                for (int l = 0; l < Niveau.CHUNKSIZE; l++) {
                    System.out.print(" " + c.chunk[k][l]);
                }
                System.out.println();
            }
            System.out.println();
        } */
    }


    /**
     * Ecrit sur le disque la sauvegarde des chunks présent dans l'objet save.
     */
    public void saveToFile(){
        if (!savefile.exists()){
            try{
                savefile.createNewFile();
                annuairefile.createNewFile();
            }
            catch (IOException e){
                System.out.println("Erreur création des fichiers de save de block");
            }
        }
        if(savefile.length() == 0){
            int wrtdChunks = 0;
            for(int i=0; i<chunks.size(); i++){
                Chunk c = chunks.get(i);
                if(!c.vide){
                    wrtdChunks += 1;
                    int nsize = wrtdChunks;
                    try {
                        annuaireaccess.seek(0);
                        annuaireaccess.writeInt(nsize);
                        annuaireaccess.seek(annuaireaccess.length());
                    }
                    catch (IOException e){
                        System.out.println("Erreur écriture de la taille de l'annuaire");
                    }
                    try {
                        annuaireaccess.writeChar(c.pos.getX());
                        annuaireaccess.writeChar(c.pos.getY());
                        annuaireaccess.writeInt((int) savefile.length());
                    }
                    catch (IOException e){
                        System.out.println("Erreur écriture du chunk dans l'annuaire");
                    }
                    try{
                        saveaccess.seek(saveaccess.length());
                    }
                    catch(IOException e){
                        System.out.println("Erreur de positionnement du flux de sauvegarde pour BlocksSave : " + e);
                    }
                    ecritureChunk(chunks.get(i));
                }
            }
        }
        else{
            try{
                annuaireaccess.seek(0);
                int annuT = annuaireaccess.readInt();
                //chercher les chunks déjà présent pour les modifier et ne pas rajouter une nouvelle occurence de ceux çi (et donc ne pas augmenter annuT pour ceux ci)
                int[][] annu = new int[annuT][3];
                for(int i=0; i<annuT; i++){
                    annu[i] = new int[] {annuaireaccess.readChar(),  annuaireaccess.readChar(),annuaireaccess.readInt()};
                }
                for(Chunk chunk : chunks){
                    if (!chunk.vide){
                        boolean found = false;
                        int cx = chunk.pos.getX();
                        int cy = chunk.pos.getY();
                        for(int j=0; j<annuT; j++){
                            if(Math.floorMod(cx, MAX_INT_CHAR) == annu[j][0] && Math.floorMod(cy, MAX_INT_CHAR) == annu[j][1]){
                                found = true;
                                saveaccess.seek(annu[j][2]);
                                ecritureChunk(chunk);
                            }
                        }                    
                        if(!found){
                            annuaireaccess.seek(0);
                            int nbC = annuaireaccess.readInt();
                            annuaireaccess.seek(0);
                            annuaireaccess.writeInt(nbC + 1);
                            annuaireaccess.seek(annuaireaccess.length());
                            annuaireaccess.writeChar(cx);
                            annuaireaccess.writeChar(cy);
                            annuaireaccess.writeInt((int) savefile.length());
                            try{
                                saveaccess.seek(saveaccess.length());
                            }
                            catch(IOException e){
                                System.out.println("Erreur de positionnement du flux de sauvegarde pour BlocksSave : " + e);
                            }
                            ecritureChunk(chunk);
                            
                        }
                    }
                }
            }
            catch(IOException e){
                System.out.println("Erreur écriture de l'annuaire ou de l'écriture des chunks");
            }
        }
    }

    /**
     * Lit le fichier contenant les positions des chunks dans le fichier de sauvegarde, puis lit
     * le fichier de sauvegarde et reconstitue les chunks correspondants.
     */
    public void updFromFile(){
        if (savefile.exists() && savefile.length() != 0){
            System.out.println(savefile.length());
            try{
                annuaireaccess.seek(0);
                int annuT = annuaireaccess.readInt();
                for(int i=0; i<annuT; i++){
                    int cx = annuaireaccess.readChar();
                    int cy = annuaireaccess.readChar();
                    int pos = annuaireaccess.readInt();
                    Chunk chunk = new Chunk(new Block[Niveau.CHUNKSIZE][Niveau.CHUNKSIZE],false);
                    try{
                        saveaccess.seek(pos);
                        for(int k = 0; k<Niveau.CHUNKSIZE; k++){
                            for(int l = 0; l<Niveau.CHUNKSIZE; l++){
                                int id = saveaccess.readByte();
                                int x = saveaccess.readByte();
                                int y = saveaccess.readByte();
                                Block b = Block.blockByID(x + cx*Niveau.CHUNKSIZE, y + cy*Niveau.CHUNKSIZE, id);
                                chunk.chunk[k][l] = b;
                                chunks.add(chunk);
                            }
                        }
                    }
                    catch(IOException e){
                        System.out.println("Erreur lecture du chunk dans la save");
                    }
                }
            }
            catch(IOException e){
                System.out.println("Erreur lecture de l'annuaire 1");
            }
        }
    }

    /**
     * Lit le fichier contenant les positions des chunks dans le fichier de sauvegarde, puis lit
     * le fichier de sauvegarde et reconstitue le chunks demandé.
     * 
     * @param param les coordonnées du morceau
     */
    public void updFromFile(double[] param){
        /* if (param[0] == -1 && param[1] == 5) {System.out.println("Chargement en cours...");} */
        if (savefile.exists() && savefile.length() != 0){
            try{
                annuaireaccess.seek(0);
                int annuT = annuaireaccess.readInt();

                boolean trouve = false;

                //System.out.println("Chunk à charger :" + param[0] + ":" + param[1]);
                //System.out.println("Chunk à charger :" + Math.floorMod((int) param[0], MAX_INT_CHAR) + ":" + Math.floorMod((int) param[1], MAX_INT_CHAR));
                int j = 1;
                for(int i=0; i<annuT; i++){
                    int cx = annuaireaccess.readChar();
                    int cy = annuaireaccess.readChar();

                    int pos = annuaireaccess.readInt();
                    Chunk chunk = new Chunk(new Block[Niveau.CHUNKSIZE][Niveau.CHUNKSIZE],false);
                    if (cx == Math.floorMod((int) param[0], MAX_INT_CHAR) && cy == Math.floorMod((int) param[1], MAX_INT_CHAR)){
                        trouve = true;
                        try{
                            saveaccess.seek(pos);
                            for(int k = 0; k<Niveau.CHUNKSIZE; k++){
                                for(int l = 0; l<Niveau.CHUNKSIZE; l++){
                                    int id = saveaccess.readByte();
                                    int x = saveaccess.readByte();
                                    int y = saveaccess.readByte();
                                    if (cx > MAX_INT_CHAR/2) {
                                        cx = cx - MAX_INT_CHAR;
                                    }
                                    if (cy > MAX_INT_CHAR/2) {
                                        cy = cy - MAX_INT_CHAR;
                                    }
                                    Block b = Block.blockByID(x + cx*Niveau.CHUNKSIZE, y + cy*Niveau.CHUNKSIZE, id);
                                    chunk.chunk[k][l] = b;
                                }
                            }
                        }
                        catch(IOException e){
                            System.out.println("Erreur lecture du chunk dans la save");
                        }
                        finally{
                            chunks.add(chunk);
                        }

                        /* if (param[0] == -1 && param[1] == 5) {
                            System.out.println("Chargé");
                            for (int k = 0; k < Niveau.CHUNKSIZE; k++) {
                                for (int l = 0; l < Niveau.CHUNKSIZE; l++) {
                                    System.out.print(" " + chunk.chunk[k][l]);
                                }
                                System.out.println();
                            }
                            System.out.println();
                        } */

                    }
                }
                if (!trouve) {
                    System.out.println("erreur pour retrouver le chunk");
                } else {
                    //System.out.println("chunk trouvé");
                }
            }
            catch(IOException e){
                System.out.println("Erreur lecture de l'annuaire 2");
            }
        }
    }
}