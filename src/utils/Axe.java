package utils;

public class Axe { //Norme = 1 par définition

    private double x; //Composante en x
    private double y; //Composante en y
    


    public Axe(double cx, double cy){
        double norme = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (norme > 0){
            this.x = cx/norme;
            this.y = cy/norme;
        }
        else{
            this.x = cx;
            this.y = cy;
        }
    }


    /** Vecteur normale d'une droite définie par deux points.
	 * @param p1 premier point de la droite
     * @param p2 deuxieme point de la droite
	 * @return L'axe normal à cette droite.
	 */
    public static Axe normaleOf(Point p1, Point p2){
        double vx = p2.getX() - p1.getX();
        double vy = p2.getY() - p1.getY();
        return new Axe(-vy,vx);
    }

    /** Produit scalaire entre un axe (vecteur de norme 1) et un vecteur (origine,point).
	 * @param p1 le point du vecteur (origine,point)
	 * @return Le produit scalaire de ces deux vecteurs.
	 */
    public double pDot(Point p1){
        return (this.x*p1.getX() + this.y*p1.getY());
    }

    public double pDot(Vecteur v1){
        return (this.x*v1.getX() + this.y*v1.getY());
    }


    /**Tableau[2] de double correspondant à l'axe.
     * @return tableau[2] représentant l'axe.
    */
    public double[] toDouble(){
        double[] dr = {this.x,this.y};
        return dr;
    }

    public String toString(){
        String sr = "Composante en x : " + this.x + " Composante en y : " + this.y;
        return sr;
    }
}
