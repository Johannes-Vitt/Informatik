public abstract class Baumelement
{
    public Baumelement()
    {
    }
    abstract public int tiefe();
    abstract public void einhängen(Knoten k);
    abstract public Daten inOrder();
}
