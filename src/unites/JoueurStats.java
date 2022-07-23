package unites;

import java.util.HashMap;

public class JoueurStats {
 
    private HashMap <String, Integer> monstreTues;
    private HashMap <String, Integer> blocsDetruits;
    private HashMap <String, Integer> blocsPlaces;

    public JoueurStats() {
        this.monstreTues = new HashMap <String, Integer>();
        this.blocsDetruits = new HashMap <String, Integer>();
        this.blocsPlaces = new HashMap <String, Integer>();
    }

    public HashMap <String, Integer> getMonstreTues() {
        return monstreTues;
    }

    public HashMap <String, Integer> getBlocsDetruits() {
        return blocsDetruits;
    }

    public HashMap <String, Integer> getBlocsPlaces() {
        return blocsPlaces;
    }

    public void addMonstresTues(String nom) {
        if (this.monstreTues.containsKey(nom)) {
            this.monstreTues.put(nom, this.monstreTues.get(nom) + 1);
        } else {
            this.monstreTues.put(nom, 1);
        }
    }

    public void addBlocsDetruits(String nom) {
        if (this.blocsDetruits.containsKey(nom)) {
            this.blocsDetruits.put(nom, this.blocsDetruits.get(nom) + 1);
        } else {
            this.blocsDetruits.put(nom, 1);
        }

        System.out.println("Blocs detruits: " + nom + ": " + this.blocsDetruits.get(nom));
    }

    public void addBlocsPlaces(String nom) {
        if (this.blocsPlaces.containsKey(nom)) {
            this.blocsPlaces.put(nom, this.blocsPlaces.get(nom) + 1);
        } else {
            this.blocsPlaces.put(nom, 1);
        }
    }

    public void reset() {
        this.monstreTues.clear();
        this.blocsDetruits.clear();
        this.blocsPlaces.clear();
    }

    public int getNbMonstreTues(String nom) {
        if (this.monstreTues.containsKey(nom)) {
            return this.monstreTues.get(nom);
        } else {
            return 0;
        }
    }

    public int getNbMonstreTues() {
        int nb = 0;
        for (String nom : this.monstreTues.keySet()) {
            nb += this.monstreTues.get(nom);
        }
        return nb;
    }

    public int getNbBlocsDetruits(String nom) {
        if (this.blocsDetruits.containsKey(nom)) {
            return this.blocsDetruits.get(nom);
        } else {
            return 0;
        }
    }

    public int getNbBlocsPlaces(String nom) {
        if (this.blocsPlaces.containsKey(nom)) {
            return this.blocsPlaces.get(nom);
        } else {
            return 0;
        }
    }

}