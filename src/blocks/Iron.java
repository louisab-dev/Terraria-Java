package blocks;

import affichage.Sprite;

public class Iron extends Block {
    
    public Iron(int x, int y) {
        super(x, y,4, 0.5);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Iron(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.iron;
    }
}
