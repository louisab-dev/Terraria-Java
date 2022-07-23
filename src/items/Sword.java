package items;

import affichage.Sprite;

public class Sword extends Item {

    public Sword(int x, int y) {
        super(false, false, 1, x, y, 10, 3, 2);
    }

    public Sword() {
        this(0, 0);
    }

    @Override
    public Item newItem(int x, int y) {
        return new Sword(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.sword;
    }
    
}
