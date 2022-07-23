package utils;

import java.util.*;

/* map indexée par deux entiers relatifs  */
public class PlanMap<E> {
    
    private Map<Integer, Map<Integer, E>> map;

    public PlanMap() {
        map = new HashMap<>();
    }

    public E get(int i, int j) {
        if (map.containsKey(i)) {
            return map.get(i).get(j); // renvoie null si map.get(i) n'a pas d'entrée j
        }
        return null;
    }

    public void put(int i, int j, E val) {
        if (!map.containsKey(i)) {
            map.put(i, new HashMap<>());
        }
        map.get(i).put(j, val);
    }

    public E remove(int i, int j) {
        E val = map.get(i).remove(j);
        if (map.get(i).isEmpty()) {
            map.remove(i);
        }
        return val;
    }

    public boolean estVide() {
        return map.isEmpty();
    }

}
