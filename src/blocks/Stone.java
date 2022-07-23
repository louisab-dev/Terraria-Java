package blocks;

import affichage.Sprite;

public class Stone extends Block {
    
    public Stone(int x, int y) {
        super(x, y,2, 0.3);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Stone(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.stone;
    }
}
