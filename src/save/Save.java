package save;

public interface Save {

    public int priorite = 0;
    public static String fileDir = "";
    
    public void saveToFile();

    public void updFromFile(double[] param);
    
    public void updFromFile();

}
