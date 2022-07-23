package achievements;

import java.util.ArrayList;
import java.util.List;

import items.InventoryItem;
import items.Pickaxe;
import items.Sword;
import main.Niveau;
import unites.JoueurStats;

public class AchievementManager {

    public static List<Achievement> achievements;
    private JoueurStats stats;
    private Niveau niveau;

    public AchievementManager(Niveau niveau, JoueurStats stats) {
        achievements = new ArrayList<>();
        achievements.add(new MineurPierre());
        achievements.add(new TueurDeMonstre());
        achievements.add(new Fermier());
        this.stats = stats;
        this.niveau = niveau;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public static List<Achievement> getAchievementsToDisplay() {
        List<Achievement> toDisplay = new ArrayList<>();
        toDisplay.add(new MineurPierre());
        toDisplay.add(new TueurDeMonstre());
        toDisplay.add(new Fermier());
        return toDisplay;
    }

    public void maj() {
        for (Achievement a : achievements) {
            a.maj(stats, niveau);
        }
    }

}
