package utils;

public class Polygone {
    
    private Point[] points; //Points du polygone dans le sens horaire.

    public Polygone(Point[] p){
        points = p;
    }

    /**Projetter le polygone sur un axe
     * @param a L'axe sur lequel on projette
     * @return Un tableau de taille 2 contenant le projeté le plus petit et le projeté le plus grand de : (origine, point polygone) avec l'axe a. C'est "l'ombre" du polygone sur l'axe.
     */
    public double[] projection(Axe a){
        int n = points.length;
        double min = a.pDot(points[0]);
        double max = 0;
        for(int i = 1; i < n; i++){
            double nps = a.pDot(points[i]);
            if (nps > max){
                max = nps;
            }
            else if (nps < min){
                min = nps;
            }
        }
        double[] dr = {min,max};
        return dr;
    }

    /** Obtenir les axes normaux des différents segments qui constituent le polygone
     * @return Un tableau d'axe contenant l'axe normale à chacun des segments
     */
    public Axe[] getAxesNormaux(){
        int n = points.length;
        Axe[] ar = new Axe[n];
        for(int i = 0; i < n; i++){
            ar[i] = Axe.normaleOf(points[i], points[(i+1)%n]);
            
        }
        return ar;
    }

    public Point[] getPoint(){
        return this.points; //Il faudrait renvoyer une copie. On considère que les points sont composante d'un polygone unique, et ne peuvent être manipulé individuellement.
    }

    public void translaterPoints(double dx, double dy){
        for(int i = 0; i <points.length; i++){
            points[i].translater(dx,dy);
        }
    }

    public Polygone copie(){
        return new Polygone(this.points);
    }
}
