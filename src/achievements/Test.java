package achievements;

import items.InventoryItem;
import main.Niveau;
import sound.SoundEffect;
import unites.JoueurStats;

public class Test extends Achievement {

    public Test(String name, String description, InventoryItem reward) {
        super(name, description, reward);
    }

    @Override
    public void maj(JoueurStats stats, Niveau niveau) {

        if (!rewarded) {
            
            if (stats.getBlocsDetruits().containsKey("Dirt")) {

                if (stats.getBlocsDetruits().get("Dirt") >= 5) {

                    niveau.getInventoryManager().getPlayerInventory().addInventoryItem(this.getReward());
                    completed = true;
                    rewarded = true;
                    SoundEffect.play("achievement");

                }

            }
        }
    }
}
