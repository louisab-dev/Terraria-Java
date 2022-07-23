package utils;

public class Point {
    
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /** Obtenir l'abscisse du point.
	 * @return abscisse du point
	 */
	//@ pure
    public double getX(){
        return this.x;
    }

    /** Obtenir l'ordonnée du point.
	 * @return ordonnée du point
	 */
	//@ pure
    public double getY(){
        return this.y;
    }

    public Point getPoint(){
        return new Point(this.x, this.y);
    }

    public void translater(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString(){
        return "PosX : " + this.x + " PosY : " + this.y;
    }

    public double distance( double x, double y){
        return Math.sqrt(Math.pow(this.x -x,2)+ Math.pow(this.y -y,2));
    }

}
