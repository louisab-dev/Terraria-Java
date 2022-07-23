package blocks;

public class chunkPos {
    
    private double cx;
    private double cy;

    public chunkPos(double cx, double cy) {
        this.cx = cx;
        this.cy = cy;
    }

    @Override 
    public boolean equals(Object o){
        if(o instanceof chunkPos){
            chunkPos c = (chunkPos) o;
            return c.cx == this.cx && c.cy == this.cy;
        }
        return false;
    }

    public double[] toDouble() {
        return new double[]{cx, cy};
    }

    public int getX() {
        return (int) cx;
    }

    public int getY() {
        return (int) cy;
    }
}
