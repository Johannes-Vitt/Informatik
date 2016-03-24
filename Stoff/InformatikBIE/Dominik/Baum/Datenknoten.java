public class Datenknoten extends Baumelement
{
    Baumelement linker, rechter;
    Daten daten;
    
    public Datenknoten(double zahl)
    {
        linker = new Abschluss();
        rechter = new Abschluss();
        daten = new Daten();
        daten.zahl=zahl;
    }
    
    public void anhängen(double n)
    {
        anhängen(new Datenknoten(n));
    }
    
    public void anhängen(Datenknoten n)
    {
        if(linker instanceof Abschluss&&n.daten.zahl<=daten.zahl) linker = n;
        else if(rechter instanceof Abschluss&&n.daten.zahl>daten.zahl) rechter = n;
        else
        {
            if(n.daten.zahl>daten.zahl)
            {
                ((Datenknoten)rechter).anhängen(n);
            }
            else
            {
                ((Datenknoten)linker).anhängen(n);
            }
        }
    }
    
    public Daten inOrder()
    {
        linker.inOrder();
        System.out.println(daten.zahl);
        rechter.inOrder();
        return daten;
    }
    
    public Daten preOrder()
    {
        System.out.println(daten.zahl);
        linker.preOrder();
        rechter.preOrder();
        return daten;
    }
    
    public Daten postOrder()
    {
        linker.postOrder();
        rechter.postOrder();
        System.out.println(daten.zahl);
        return daten;
    }
    
    public boolean löschen(double n)
    {
        boolean istLinker = false;
        boolean erg = false;
        if(!(linker instanceof Abschluss)&&((Datenknoten)linker).daten.zahl==n)
        {
            istLinker = true;
        }
        else if(rechter instanceof Abschluss||((Datenknoten)rechter).daten.zahl!=n)
        {
            if(!(linker instanceof Abschluss))
                erg = ((Datenknoten)linker).löschen(n);
            if(!(rechter instanceof Abschluss))
                erg = erg || ((Datenknoten)rechter).löschen(n);
            return erg;
        }
        int abschlüsse = ((Datenknoten)(istLinker?linker:rechter)).linker instanceof Abschluss ? 1 : 0;
        abschlüsse += ((Datenknoten)(istLinker?linker:rechter)).rechter instanceof Abschluss ? 1 : 0;
        if(abschlüsse == 2)
        {
            if(istLinker)
            {
                linker = new Abschluss();
            }
            else
            {
                rechter = new Abschluss();
            }
            return true;
        }
        if(abschlüsse == 0)
        {
            if(((Datenknoten)(istLinker?linker:rechter)).linker.tiefe()>((Datenknoten)(istLinker?linker:rechter)).rechter.tiefe())
            {
                Datenknoten help = (Datenknoten)((Datenknoten)(istLinker?linker:rechter)).linker;
                ((Datenknoten)(istLinker?linker:rechter)).linker = new Abschluss();
                ((Datenknoten)((Datenknoten)(istLinker?linker:rechter)).rechter).anhängen(help);
            }
            else
            {
                Datenknoten help = (Datenknoten)((Datenknoten)(istLinker?linker:rechter)).linker;
                ((Datenknoten)(istLinker?linker:rechter)).linker = new Abschluss();
                ((Datenknoten)((Datenknoten)(istLinker?linker:rechter)).rechter).anhängen(help);
            }
        }
        //wenn 1 Abschluss
        if(((Datenknoten)(istLinker?linker:rechter)).linker instanceof Abschluss)
        {
            if(istLinker)
            {
                linker = ((Datenknoten)linker).rechter;
            }
            else
            {
                rechter = ((Datenknoten)rechter).rechter;
            }
        }
        else if(((Datenknoten)(istLinker?linker:rechter)).rechter instanceof Abschluss)
        {
            if(istLinker)
            {
                linker = ((Datenknoten)linker).linker;
            }
            else
            {
                rechter = ((Datenknoten)rechter).linker;
            }
        }
        else
        {
            System.out.println("F");
        }
        return true;
    }
    
    /*public void anhängenRand(double n)
    {
        if(linker instanceof Abschluss) linker = new Datenknoten(n);
        else if(rechter instanceof Abschluss) rechter = new Datenknoten(n);
        else
        {
            if(Math.random()>=0.5)
            {
                ((Datenknoten)rechter).anhängenRand(n);
            }
            else
            {
                ((Datenknoten)linker).anhängenRand(n);
            }
        }
    }*/
    
    public String[] baumString()
    {
        String[] s = new String[tiefe()],l=null,r=null;
        s[0] = (linker instanceof Abschluss?"[A]":Double.toString(((Datenknoten)linker).daten.zahl))+" "+(rechter instanceof Abschluss?"[A]":Double.toString(((Datenknoten)rechter).daten.zahl));
        if(linker instanceof Datenknoten)
        {
            l = ((Datenknoten)linker).baumString();
        }
        if(rechter instanceof Datenknoten)
        {
            r = ((Datenknoten)rechter).baumString();
        }
        for(int i=0;i<s.length-1;i++)
        {
            s[i+1] = "";
            if(l!=null&&i<l.length&&l[i]!=null&&!l[i].equals(""))
            {
                s[i+1]+=l[i];
            }
            else
            {
                s[i+1]+="[A]";
            }
            s[i+1] += " ";
            if(r!=null&&i<r.length&&r[i]!=null&&!r[i].equals(""))
            {
                s[i+1]+=r[i];
            }
            else
            {
                s[i+1]+="[A]";
            }
        }
        return s;
    }
    
    public int tiefeSeite(boolean links)
    {
        if(links) return linker.tiefe();
        return rechter.tiefe();
    }
    
    public int tiefe()
    {
        return Math.max(linker.tiefe(), rechter.tiefe())+1;
    }
}
