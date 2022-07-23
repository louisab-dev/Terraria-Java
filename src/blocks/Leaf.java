package blocks;


import affichage.Sprite;


public class Leaf extends Block {
    
    public Leaf(int x, int y) {
        super(x, y,false,9, 0);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Leaf(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.leaf;
    }
}