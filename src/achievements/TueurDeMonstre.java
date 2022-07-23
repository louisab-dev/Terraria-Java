package achievements;


import items.InventoryItem;
import items.Sword;
import main.Niveau;
import sound.SoundEffect;
import unites.JoueurStats;

public class TueurDeMonstre extends Achievement {


    public TueurDeMonstre() {
        super("Tueur de Monstre", "Tuer 5 monstres", new InventoryItem(new Sword(0, 0), 1));
    }

    @Override
    public void maj(JoueurStats stats, Niveau niveau) {

        if (!rewarded) {

            if (stats.getNbMonstreTues() >= 5) {
                niveau.getInventoryManager().getPlayerInventory().addInventoryItem(this.getReward());
                completed = true;
                rewarded = true;
                SoundEffect.play("achievement");
            }
            
        }
    }
}
