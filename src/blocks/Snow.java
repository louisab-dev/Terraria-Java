package blocks;

import affichage.Sprite;

public class Snow extends Block {
    
    public Snow(int x, int y) {
        super(x, y,7, 0.1);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Snow(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.snow;
    }
}