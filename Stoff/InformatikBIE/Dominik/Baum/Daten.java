public class Daten
{
    double zahl;
    public Daten()
    {
        
    }
    
    public String toString()
    {
        return Double.toString(zahl).substring(0,4);
    }
}
