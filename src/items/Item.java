package items;

import affichage.*;

public abstract class Item {

    private boolean estPosable;
    private boolean estStackable;
    private int maxStack;
    protected int x;
    protected int y;
    protected int dammage;
    protected int efficacite;
    protected int cooldown;

    public static final int TAILLE = 32;

    public Item(boolean estPosable, boolean estStackable, int maxStack, int x, int y, int dammage, int efficacite, int cooldown) {
        this.estPosable = estPosable;
        this.estStackable = estStackable;
        this.maxStack = maxStack;
        this.x = x;
        this.y = y;
        this.dammage = dammage;
        this.efficacite = efficacite;
        this.cooldown = cooldown;
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

    public int getDammage() {
        return dammage;
    }

    public int getEfficacite() {
        return efficacite;
    }

    public int getCooldown() {
        return cooldown;
    }

    public abstract Sprite getSprite();

    public abstract Item newItem(int x, int y);
}
