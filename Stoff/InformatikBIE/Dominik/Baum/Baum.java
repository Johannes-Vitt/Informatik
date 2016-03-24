public class Baum
{
    Baumelement wurzel; 
    
    public Baum()
    {
        wurzel = new Abschluss();
    }
    
    public void löschen(double n)
    {
        if(!(wurzel instanceof Abschluss))
        {
            if(((Datenknoten)wurzel).daten.zahl==n)
            {
                System.out.println("O");
                return;
            }
            ((Datenknoten)wurzel).löschen(n);
        }
    }
    
    public void massenanhängen(int n)
    {
        for(int i=0;i<n;i++)
        {
            anhängen(Math.random()*100);
        }
    }
    
    public void anhängen(double n)
    {
        if(wurzel instanceof Abschluss)
        {
            wurzel = new Datenknoten(n);
        }
        else
        {
            ((Datenknoten)wurzel).anhängen(n);
        }
    }
    
    public int tiefe()
    {
        return wurzel.tiefe();
    }
    
    public int tiefeSeite(boolean links)
    {
        if(wurzel instanceof Abschluss) return 0;
        return ((Datenknoten)wurzel).tiefeSeite(links);
    }
    
    /*public void anhängenRand(double n)
    {
        if(wurzel instanceof Abschluss)
        {
            wurzel = new Datenknoten(n);
        }
        else
        {
            ((Datenknoten)wurzel).anhängenRand(n);
        }
    }*/
    
    public void inOrder()
    {
        wurzel.inOrder();
    }
    
    public void preOrder()
    {
        wurzel.preOrder();
    }
    
    public void postOrder()
    {
        wurzel.postOrder();
    }
    
    public void ausgeben()
    {
        if(wurzel instanceof Abschluss) return;
        String[] s = ((Datenknoten)wurzel).baumString();
        System.out.println(((Datenknoten)wurzel).daten.zahl);
        for(int i=0;i<s.length;i++)
        {
            if(s[i]!=null)
                System.out.println(s[i]);
        }
    }
}
