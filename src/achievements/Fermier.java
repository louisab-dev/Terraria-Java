package achievements;

import items.InventoryItem;
import items.Pickaxe;
import main.Niveau;
import sound.SoundEffect;
import unites.JoueurStats;

public class Fermier extends Achievement {

    public Fermier() {
        super("Fermier", "Détruire 5 blocs de terre", new InventoryItem(new Pickaxe(), 1));
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
