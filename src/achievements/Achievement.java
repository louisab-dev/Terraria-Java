package achievements;

import items.*;
import main.Niveau;
import unites.JoueurStats;

abstract public class Achievement {

    protected String name;
    private String description;
    private InventoryItem reward;
    protected boolean completed;
    protected boolean rewarded; 

    public Achievement(String name, String description, InventoryItem reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.completed = false;
        this.rewarded = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public InventoryItem getReward() {
        return reward;
    }

    public boolean isCompleted() {
        return completed;
    }

    abstract public void maj(JoueurStats stats, Niveau niveau);

    public boolean isRewarded() {
        return rewarded;
    }

    public void setRewarded(boolean rewarded) {
        this.rewarded = rewarded;
    }
    
}
