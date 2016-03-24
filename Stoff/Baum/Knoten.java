public class Knoten extends Baumelement{

    Daten d;
    Baumelement links, rechts;
    
    public Knoten(){
        links = new Abschluss();
        rechts = new Abschluss();
        d = null;
    }
    
    
    
    public boolean geordnetAnhängen(double n){
        if (d == null){
            d = new Daten(n);
            return true;
        }
        else if(d.zahl > n){
           if (links instanceof Abschluss){
                links = new Knoten();
                ((Knoten)links).d = new Daten(n);
                ((Knoten)links).d.zahl = n;
                return true;
           }
           else ((Knoten)links).geordnetAnhängen(n);
           
        }    
        else if(rechts instanceof Abschluss){          
                rechts = new Knoten();
                ((Knoten)rechts).d = new Daten(n);
                ((Knoten)rechts).d.zahl = n;
                return true;
            }
            else ((Knoten)links).geordnetAnhängen(n);
        }
            
    
    public int tiefe(){
        return Math.max(links.tiefe(),rechts.tiefe())+1;
    }
    
    public boolean lösche(double x){
        if( links instanceof Abschluss && rechts instanceof Abschluss){
           if (d.zahl == x) return true;
           else return false;
        }
        else if (links instanceof Abschluss && ((Knoten)rechts).d.zahl == x){
            if (( ((Knoten)rechts).rechts instanceof Abschluss) && 
                (((Knoten)rechts).links instanceof Abschluss)){
                    rechts = null;
                    return true;
                }
            else if(( ((Knoten)rechts).rechts instanceof Abschluss) && 
                !(((Knoten)rechts).links instanceof Abschluss)){
                    rechts = ((Knoten)rechts).links;
                    return true;
                }   
            else if(!( ((Knoten)rechts).rechts instanceof Abschluss) && 
                (((Knoten)rechts).links instanceof Abschluss)){
                    rechts = ((Knoten)rechts).rechts;
                    return true;
                }
            else{
                int l = ((Knoten)rechts).links.tiefe();
                int r = ((Knoten)rechts).rechts.tiefe();
                if(l > r){
                    ((Knoten)rechts).links.einhängen((Knoten)((Knoten)rechts).rechts);
                }
                else ((Knoten)rechts).rechts.einhängen((Knoten)((Knoten)rechts).links);
                return false;
            }    
            
        }
        return false;
    }
    
    public void einhängen(Knoten k){
        if( links instanceof Abschluss ) links = k;
        else if( rechts instanceof Abschluss ) rechts = k;
        else ((Knoten)rechts).rechts.einhängen(k);
    }
    
    public Daten inOrder(){
        return null;
    }
}



















