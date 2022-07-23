package items;

import affichage.Sprite;

public class Apple extends Item {

    public Apple(int x, int y) {
        super(false,false, 1, x, y, 1, 1, 1);
    }

    public Apple() {
        this(0, 0);
    }

    @Override
    public Item newItem(int x, int y) {
        return new Apple(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.apple;
    }
    
}