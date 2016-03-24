

public class Leader
{
    Tänzer rechts,links;
    char geschlecht;
    public Leader(char geschl)
    {
        geschlecht = geschl;
    }
    
    public void add(String seite,char geschl){
        switch(seite){
            
        case "rechts": 
        if (rechts!=null){
                rechts.add(geschl);
            }
            else {
                rechts = new Tänzer(geschl);
        }
        break;
        case "links":
        if (links!=null){
                links.add(geschl);
            }
            else {
                links = new Tänzer(geschl);
        }
        break;
    }
    
   }
   
   public int laenge(){
       if(rechts!=null&&links!=null){
           return 1+rechts.laenge()+links.laenge();
        }
        else if (rechts == null){
            return 1+links.laenge();
        }
        else if(links==null){
            return 1+rechts.laenge();
        }
        return 0;
    }
  
    public void addBunt(char geschl){

        
        if(links.getGeschLast()!=geschl){
            links.add(geschl);
        }
        else if(rechts.getGeschLast()!=geschl){
            rechts.add(geschl);
        }
        else if(links == null){
            links = new Tänzer(geschl);
        }
        else if(rechts == null){
            rechts = new Tänzer(geschl);
        }
        else {
            System.out.println(geschl+" konnte leider nicht angehängt werden");
        }
    }
}
