package blocks;
import affichage.Sprite;

public class Air extends Block {

    public Air(int x, int y) {
        super(x, y,false, 0, 1);
    }

    @Override 
    public Block newItem(int x, int y) {
        return new Air(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.air;
    }


}
