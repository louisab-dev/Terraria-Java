package items;

import affichage.Affichable;

public abstract class Inventory implements Affichable {

    protected InventoryItem[] items;
    protected int maxSizeInventory;
    private int currentSize;

    public Inventory(int maxSizeInventory) {
        this.currentSize = 0;
        this.items = new InventoryItem[maxSizeInventory];
        this.maxSizeInventory = maxSizeInventory;
    }

    public int getMaxSize() {
        return maxSizeInventory;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void addInventoryItem(int index, InventoryItem item) {
        if (this.currentSize < this.maxSizeInventory) {
            this.items[index] = item;
            this.currentSize++;
        }
    }

    public void addInventoryItem(InventoryItem item) {
        if (currentSize < maxSizeInventory) {
            items[currentSize] = item;
            currentSize++;
        }
    }

    public void removeInventoryItem(int index) {
        items[index] = null;
        currentSize--;
    }

    public InventoryItem getInventoryItem(int index) {
        return items[index];
    }


    public void setInventoryItem(int index, InventoryItem item) {
        items[index] = item;
    }

    public int getIndex(InventoryItem selectedItem) {
        for (int i = 0; i < currentSize; i++) {
            if (items[i] == selectedItem) {
                return i;
            }
        }
        return -1;
    }

    public void decrementQuantity(int index) {
        if(items[index].getQuantity() > 1) {
            items[index].removeQuantity(1);
        }
        else {
            removeInventoryItem(index);
        }
    }

    public abstract void afficher();
}
