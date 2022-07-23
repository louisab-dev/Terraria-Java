package blocks;

import affichage.Sprite;

public class Diamond extends Block {
    
    public Diamond(int x, int y) {
        super(x, y,6, 0.8);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Diamond(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.diamond;
    }
}