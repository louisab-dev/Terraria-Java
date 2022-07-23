package blocks;

import affichage.Sprite;

public class Dirt extends Block {
    
    public Dirt(int x, int y) {
        super(x, y,3, 0.2);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Dirt(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.dirt;
    }
}
