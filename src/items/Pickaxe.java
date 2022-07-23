package items;

import affichage.Sprite;

public class Pickaxe extends Item {

    public Pickaxe(int x, int y) {
        super(false, false, 1, x, y, 3, 10, 3);
    }

    public Pickaxe() {
        this(0, 0);
    }

    @Override
    public Item newItem(int x, int y) {
        return new Pickaxe(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.pickaxe;
    }
    
}
