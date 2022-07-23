package utils;

import org.junit.*;
import static org.junit.Assert.*;

public class PlanMapTest {

    public static final double EPSILON = 1e-6;
    
    private PlanMap<Double> map;

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("PlanMapTest");
    }

    @Before
    public void setUp() {
        map = new PlanMap<>();
    }

    @Test
    public void testInitialisation() {
        assertTrue(map.estVide());
    }

    @Test
    public void testAjouter() {
        map.put(3, -5, 4.3);
        assertTrue(!map.estVide());
        assertEquals(map.get(3, -5), 4.3, EPSILON);
    }

    @Test
    public void testSupprimer() {
        map.put(4, 17, 7.2);
        map.put(4, -2, 0.);
        map.put(-8, -31, 42.5);
        map.remove(-8, -31);
        assertTrue(map.get(-8, -31) == null);
        assertTrue(map.remove(4, 4) == null);
        map.remove(4, -2);
        assertEquals(map.get(4, 17), 7.2, EPSILON);
    }

}
