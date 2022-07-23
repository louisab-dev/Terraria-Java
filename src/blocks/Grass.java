package blocks;

import affichage.Sprite;

public class Grass extends Block {
    
    public Grass(int x, int y) {
        super(x, y,1, 0.2);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Grass(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.grass;
    }
}