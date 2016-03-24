public abstract class Baumelement
{
    public Baumelement()
    {
    }
    
    public abstract int tiefe();
    
    public abstract Daten inOrder();
    
    public abstract Daten preOrder();
    
    public abstract Daten postOrder();
}
