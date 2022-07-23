package achievements;

import items.InventoryItem;
import items.Sword;
import main.Niveau;
import sound.SoundEffect;
import unites.JoueurStats;


public class MineurPierre extends Achievement {

    public MineurPierre() {
        super("Mineur", "Détruire 5 blocs de pierre", new InventoryItem(new Sword(), 1));
    }

    @Override
    public void maj(JoueurStats stats, Niveau niveau) {

        if (!rewarded) {

            
            if (stats.getNbBlocsDetruits("Stone") >= 5) {

                niveau.getInventoryManager().getPlayerInventory().addInventoryItem(this.getReward());
                completed = true;
                rewarded = true;
                SoundEffect.play("achievement");

            }
        }
    }
    
}
