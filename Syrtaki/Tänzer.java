
public class Tänzer
{
    char geschlecht;
    Tänzer next;
    public Tänzer(char gesch)
    {
        geschlecht = gesch;
    }

    public void add(char geschl){
        if(next!=null){
            next.add(geschl);
        }
        else {
            next = new Tänzer(geschl);
        }
    }
    
    public int laenge(){
        if(next!=null){
            return 1+next.laenge();
        }
        else {
            return 1;
        }
    }
    
    public char getGeschLast(){
        if(next==null){
            return this.geschlecht; 
        }
        else {
            return next.getGeschLast();
        }
    }
}
