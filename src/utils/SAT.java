package utils;
import hitbox.rectanglehitbox;

public class SAT {
    
    public static void main(String[] args) {
        double x1 = 1.0;
        double y1 = 1.0;
        double x2 = 1.5;
        double y2 = 1.0;
        Point A1 = new Point(x1 + 0,y1 + 0);
        Point A2 = new Point(x1 + 0,y1 + 1);
        Point A3 = new Point(x1 + 1,y1 + 1);
        Point A4 = new Point(x1 + 1,y1 + 0);
        Point B1 = new Point(x2 + 0,y2 + 0);
        Point B2 = new Point(x2 + 0,y2 + 1);
        Point B3 = new Point(x2 + 1,y2 + 1);
        Point B4 = new Point(x2 + 1,y2 + 0);
        Point[] carreA = {A1,A2,A3,A4};
        Point[] carreB = {B1,B2,B3,B4};
        
        Polygone CB = new Polygone(carreB);
        Axe[] a1 = CB.getAxesNormaux();
        //System.out.println("axe 3 = " + a1[2]);
        CB.translaterPoints(0.0, 0.0);
        double[] peMTV = getMTV(new Polygone(carreA), CB);
        rectanglehitbox h1 = new rectanglehitbox(1, 1, 1.5, 1.5);
        rectanglehitbox h2 = new rectanglehitbox(1, 1, 1.6, 1.6);
        //double[] peMTV2 = getMTV(h1.getPolygone(),h2.getPolygone());
        //System.out.println(h1.getPoints()[3]);
        //System.out.println("Compostante en x : " + peMTV[0]);
        //System.out.println("Compostante en y : " + peMTV[1]);
        //System.out.println("Compostante en x : " + peMTV2[0]);
        //System.out.println("Compostante en y : " + peMTV2[1]);
    }

    /**Déterminer le vecteur de déplacement minimum à appliquer sur l'objet en superposition avec l'autre pour qu'il ne soit plus en collision 
     * @param p1 polygone dont on doit déterminer l'overlaping (p1 et p2 sont interchangeable)
     * @param p2 polygone sur lequel on vérifie l'overlaping
     * @return le vecteur MTV de déplacement minimum à appliquer sur p2 pour ne plus avoir de collision.
    */
    public static double[] getMTV(Polygone p1, Polygone p2){
        Axe[] a1 = p1.getAxesNormaux();
        Axe[] a2 = p2.getAxesNormaux();
        int test1 = 0,test2 = 0;
        Axe[][] laxes = {a1,a2};
        Axe MTV = new Axe(1.0,1.0);
        double overlapMin = 16*32768.0; //Un grand nombre...
        for (int j = 0; j < 2; j++){
            for (int i = 0; i < laxes[j].length; i++){
                double[] projec1 = p1.projection(laxes[j][i]);
                double[] projec2 = p2.projection(laxes[j][i]);
                //System.out.println(projec1[1]);
                boolean cas1 = projec1[0] > projec2[1];
                boolean cas2 = projec2[0] > projec1[1];
                if (cas1 ||cas2){
                    double[] dr = {0.0,0.0};
                    return dr;
                }
                else{
                    double o;
                    o = Math.min(-projec1[0] + projec2[1], -projec2[0] + projec1[1]);
                    //System.out.println(o);
                    if (o < overlapMin){
                        overlapMin = o;
                        MTV = laxes[j][i];
                        test1 = i + 1;
                        test2 = j + 1;         
                    }
                }
            }
        }
        //System.out.println("overlap : " + overlapMin);
        //System.out.println("axe : " + MTV);
        //System.out.println("segment : " + test1);
        //System.out.println("polyg : " + test2);
        double[] dr = MTV.toDouble();
        dr[0] = dr[0]*overlapMin;
        dr[1] = dr[1]*overlapMin;
        //System.out.println("x : " + dr[0]+ " y : " +dr[1]);
        return dr;
    }

}
