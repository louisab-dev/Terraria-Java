package blocks;

import affichage.Sprite;

public class Wood extends Block {
    
    public Wood(int x, int y) {
        super(x, y,8, 0.2);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Wood(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.wood;
    }
}