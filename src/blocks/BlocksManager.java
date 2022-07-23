package blocks;

import affichage.Affichable;
import utils.*;
import affichage.CameraManager;
import affichage.Camera;
import java.util.ArrayList;
import main.Niveau;
import save.*;
import save.Saveable;
import java.util.*;

public class BlocksManager implements Affichable, Saveable {
    public ArrayList<chunkPos> generatedChunks = new ArrayList<chunkPos>(100);
    public PlanMap<Block> blocks;

    private Map<Integer, Integer> ligneDeNiveau = new HashMap<>();

    public BlocksManager(){
        blocks = new PlanMap<>();
        //generateWorld();
    }
    
    public void afficher() {
        Camera camera = CameraManager.getCamera();
        for (int i = (int) camera.getX(); i <= camera.getX()+Camera.LARGEUR+1; i++) {
            for (int j = (int) camera.getY(); j <= camera.getY()+Camera.HAUTEUR+1; j++) {
                if (blocks.get(i,j) != null) {
                    blocks.get(i,j).afficher();
                    //blocks[i][j].debug = false;
                }
            }
        }
    }

    private void generateChunk(chunkPos chunk) {
        int x_bloc = Niveau.CHUNKSIZE * chunk.getX();
        int y_bloc = Niveau.CHUNKSIZE * chunk.getY();

        if (!ligneDeNiveau.containsKey(x_bloc)) {
            try {
                if (x_bloc < Collections.min(ligneDeNiveau.keySet())) {
                    for (int x = x_bloc+Niveau.CHUNKSIZE-1; x >= x_bloc; x--) {
                        setLigneDeNiveau(x);
                    }
                } else {
                    for (int x = x_bloc; x < x_bloc+Niveau.CHUNKSIZE; x++) {
                        setLigneDeNiveau(x);
                    }
                }
            } catch (NoSuchElementException e) {
                for (int x = x_bloc; x < x_bloc+Niveau.CHUNKSIZE; x++) {
                    setLigneDeNiveau(x);
                }
            }
        }

        for (int y = y_bloc; y < y_bloc+Niveau.CHUNKSIZE; y++) {
            for (int x = x_bloc; x < x_bloc+Niveau.CHUNKSIZE; x++) {
                if (y >= getLigneDeNiveau(x)) {
                    blocks.put(x, y, a_placer(x, y));
                }         
            }
        }
        generatedChunks.add(chunk);
    }

    /* private void generateChunk_Vieux(chunkPos chunk) {
        int x_bloc = Niveau.CHUNKSIZE * chunk.getX();
        int y_bloc = Niveau.CHUNKSIZE * chunk.getY();
        for (int y = y_bloc; y < y_bloc+Niveau.CHUNKSIZE; y++) {
            for (int x = x_bloc; x < x_bloc+Niveau.CHUNKSIZE; x++) {
                boolean placable = placable(x,y);
                if (placable){
                    blocks.put(x, y, a_placer(x,y));
                }              
            }
        }
        generatedChunks.add(chunk);
    }
 */

    private Block a_placer(int x, int y) {
        int random = (int) (Math.random()*200);
        //Cas sans blocs au dessus
        if (y == getLigneDeNiveau(x)){
            if (random<35){
                genererArbre(x,y-1);
            }
            if(y<14){
                return new Snow(x,y);
            }
            else{  
                return new Grass(x, y);
            }
           
        }
        //Couche moyenne charbon + fer 
        else if (y > 21 && y<=25) {
            if (random<170){
                return new Stone(x, y);
            }          
            else if(random < 185){
                return new Iron(x,y);
            }
            else{
                return new Coal(x,y);
            }
        } 

        //couche basse  fer +diamand
        else if (y>30 && y<=46){
            if (random<180){
                return new Stone(x, y);
            }          
            else if(random < 190){
                return new Diamond(x,y);
            }
            else{
                return new Iron(x,y);
            }
        }
        else if (y > 46) {
            return new Bedrock(x, y);
        }
        else if (y >=1) {
            if(blocks.get(x, y-5) ==null){
                return new Dirt(x, y);
            }
            else{
                if(random<180){
                    return new Stone(x,y);
                }
                else{
                    return new Coal(x,y);
                }
            }
        }
        
        return null;
    }

    private void genererArbre(int x, int y) {
        blocks.put(x, y, new Wood(x,y));
        blocks.put(x, y-1, new Wood(x,y-1));
        blocks.put(x, y-2, new Wood(x,y-2));
        blocks.put(x,y-3, new Leaf(x,y-3));
        blocks.put(x,y-4,new Leaf(x,y-4));
        if ( blocks.get(x-1, y-3)==null){
            blocks.put(x-1,y-3,new Leaf(x-1,y-3));
        }
        if (blocks.get(x+1,y-2)==null){
            blocks.put(x+1,y-3,new Leaf(x+1,y-3));
        }

    }

    private int getLigneDeNiveau(int x) {
        //long seed = 123456;
        //Random randomGenerator = new Random(seed);
        //int random = randomGenerator.nextInt() *200 ;
        if (ligneDeNiveau.containsKey(x)) {
            return ligneDeNiveau.get(x);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private void setLigneDeNiveau(int x) {
        boolean placable = false;
        for (int y = 5; y <= 23 && !placable; y++) {

            int random = (int) (Math.random()*200);

            if (y==23){
                placable  = true;
            }            
            else if (y >= getLigneDeNiveau(x-1) || y >= getLigneDeNiveau(x+1)){
                placable=random <150; 
            }
            else if (y <=17 && y>5){
                placable = random<2;
            }
            else if (y<23 && y>15){
                    placable=random<5;
            }

            if (placable) {
                ligneDeNiveau.put(x, y);   
                //System.out.println(y);
            }
        }
    }

    /* private boolean placable(int x, int y) {
        //long seed = 123456;
        //Random randomGenerator = new Random(seed);
        //int random = randomGenerator.nextInt() *200 ;
        
        int random = (int) (Math.random()*200);
        boolean placable = false;
        try{
            if (y>=23 || blocks.get(x, y-1)!= null){
                placable  = true;
            }
            
            else if (blocks.get(x-1, y-1)!= null|| blocks.get(x+1, y-1) !=null ){
                   placable=random <150 ; 
            }
            else if (y <=17 && y>5){
                   placable = random<2;
            }
            else if (y<23 && y>15){
                    placable=random<5;
            }

        }
        catch ( ArrayIndexOutOfBoundsException e) {
            placable = false;
            
        }
        return placable; 
    } */

                

    public Block getBlock(int x, int y) {
        return blocks.get(x, y);
    }

    public Boolean placer(Block block, int x, int y) {
        if (blocks.get(x, y) == null){
            //condition pour poser un bloc
            if((blocks.get(x,y+1) != null || blocks.get( x+1,y)!=null || blocks.get(x-1,y)!= null) || blocks.get(x,y-1)!= null) {
                blocks.put(x, y, block);
                return true;
            }
        }
        return false;
    }

    public Block retirer(int x, int y) {
        Block block = getBlock(x, y);
        blocks.remove(x, y);
        return block;
    }

    public void cgChunk(ArrayList<chunkPos> nChunks, ArrayList<chunkPos> aChunks){
        for(chunkPos chunk : nChunks){ //nChunks étant les chunk à charger ou générer 
            //System.out.println("nChunks : " + nChunks.get(i)[0] + " " + nChunks.get(i)[1]);
            if (generatedChunks.contains(chunk)) {
            //if(true){ 
                //System.out.println("TEEEEZST");
                BlocksSave cs = new BlocksSave();
                
                cs.updFromFile(chunk.toDouble());
                this.load(cs);
            }
            else{
                generateChunk(chunk);
            }
        }
        for(chunkPos chunk : aChunks){
            BlocksSave as = (BlocksSave) this.save(chunk.toDouble());
            as.saveToFile();
        }
    } 

    public Save save() {
        return null;
    }

    public void load(Save s) {
        if (((BlocksSave) s).chunks == null) {
            return;
        }
        else{
            for (Chunk chunk : ((BlocksSave) s).chunks) {
                for (int i = 0; i < Niveau.CHUNKSIZE; i++) {
                    for (int j = 0; j < Niveau.CHUNKSIZE; j++) {
                        if (chunk.chunk[i][j] instanceof Air) {
                            blocks.put(chunk.chunk[i][j].getX(),chunk.chunk[i][j].getY(),null);
                        }
                        else if(chunk.chunk[i][j] != null) {
                            blocks.put(chunk.chunk[i][j].getX(), chunk.chunk[i][j].getY(), chunk.chunk[i][j]);
                        }
                    }
                }
            }
        }
    } 

    /* param : coordonnées du chunk à sauvegarder */
    public Save save(double[] param) {
        return new BlocksSave(extractChunk((int) param[0],(int) param[1]));
    } 

    /* Les blocs du chunks d'indice x, y 
    retire aussi les blocs du mapping de blocs */
    public Chunk extractChunk(int x, int y) {
        Block[][] archunk = new Block[Niveau.CHUNKSIZE][Niveau.CHUNKSIZE];
        for (int i = 0; i < Niveau.CHUNKSIZE; i++) {
            for (int j = 0; j < Niveau.CHUNKSIZE; j++) {
                int xBlock = x*Niveau.CHUNKSIZE + i;
                int yBlock = y*Niveau.CHUNKSIZE + j;
                if (blocks.get(xBlock, yBlock) != null){
                    archunk[i][j] = blocks.get(xBlock, yBlock);
                    blocks.remove(xBlock, yBlock);
                }
                else{
                    archunk[i][j] = new Air(xBlock,yBlock);
                }
            }
        }
        return new Chunk(archunk);
    }

}