package blocks;

import main.Niveau;
import java.lang.Math;

public class Chunk {

    public Block[][] chunk;
    public chunkPos pos;
    public boolean vide = false;
    
    public Chunk(Block[][] chunk, chunkPos pos) {
        this.chunk = chunk;
        this.pos = pos;
    }

    public Chunk(Block[][] chunk) {
        this.chunk = chunk;
        int cx;
        int cy;
        this.vide = true;
        for(int i = 0; i < Niveau.CHUNKSIZE; i++) {
            for(int j = 0; j < Niveau.CHUNKSIZE; j++) {
                if  (chunk[i][j] != null){
                    cx = Math.floorDiv(chunk[i][j].getX(), Niveau.CHUNKSIZE);
                    cy = Math.floorDiv(chunk[i][j].getY(), Niveau.CHUNKSIZE);
                    this.pos = new chunkPos(cx,cy);
                    this.vide = false;
                    break;
                }
            }
        }
    }

    public Chunk(Block[][] chunk,boolean isVide) {
        this.chunk = chunk;
        this.vide = isVide;
        int cx;
        int cy;
        for(int i = 0; i < Niveau.CHUNKSIZE; i++) {
            for(int j = 0; j < Niveau.CHUNKSIZE; j++) {
                if (chunk[i][j] != null){
                    cx = Math.floorDiv(chunk[i][j].getX(), Niveau.CHUNKSIZE);
                    cy = Math.floorDiv(chunk[i][j].getY(), Niveau.CHUNKSIZE);
                    this.pos = new chunkPos(cx,cy);
                    break;
                }
            }
        }
    }

    public void setPos(chunkPos pos) {
        this.pos = pos;
    }

}
