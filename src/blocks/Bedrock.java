package blocks;

import affichage.Sprite;

public class Bedrock extends Block {
    
    public Bedrock(int x, int y) {
        super(x, y, 3, 10e10);
    }

    @Override
    public Block newItem(int x, int y) {
        return new Bedrock(x, y);
    }

    @Override
    public Sprite getSprite() {
        return Sprite.bedrock;
    }
}
