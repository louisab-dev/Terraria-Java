package blocks;

import affichage.Sprite;

public class Coal extends Block {
    
    public Coal(int x, int y) {
        super(x, y,5, 0.4);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Coal(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.coal;
    }
}
