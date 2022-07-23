package utils;

public class Vecteur {
    
    private double x;
    private double y;

    public Vecteur(double nx, double ny){
        //System.out.println(nx);
        //System.out.println(ny);
        this.x = nx;
        this.y = ny;
    }

    public Vecteur(Point p1, Point p2){
        //System.out.println("ici");
        this((p2.getX() - p1.getX()), (p2.getY() - p1.getY()));
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double nx){
        this.x = nx;
    }

    public void setY(double ny){
        this.y = ny;
    }

    public void translater(double dx,double dy){
        this.x += dx;
        this.y += dy;
    }
    
    public void addv(Vecteur vp){
        this.x += vp.getX();
        this.y += vp.getY();
    }
    
    public Vecteur vOppo(){
        return new Vecteur(-this.x,-this.y);
    }

    public String toString(){
        return "x : " + this.x + " y : " + this.y;
    }

    public double norm2(){
        return Math.sqrt(Math.pow(this.x, 2)+ Math.pow(this.y,2));
    }

    public double pDot(Vecteur v1){
        return (this.x*v1.getX() + this.y*v1.getY());
    }

}
