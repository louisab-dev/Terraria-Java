package items;

import affichage.*;

public abstract class Item {

    private boolean estPosable;
    private boolean estStackable;
    private int maxStack;
    protected int x;
    protected int y;

    public static final int TAILLE = 32;

    public Item(boolean estPosable, boolean estStackable, int maxStack, int x, int y) {
        this.estPosable = estPosable;
        this.estStackable = estStackable;
        this.maxStack = maxStack;
        this.x = x;
        this.y = y;
    }

    public boolean estPosable() {
        return estPosable;
    }

    public boolean estStackable() {
        return estStackable;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract Sprite getSprite();

    public abstract Item newItem(int x, int y);
}
