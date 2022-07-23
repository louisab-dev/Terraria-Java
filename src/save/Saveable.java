package save;

public interface Saveable {
    
    public Save save();

    public Save save(double[] param);

    public void load(Save s);
}
