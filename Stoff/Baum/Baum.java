public class Baum{

    Knoten wurzel;
    
    public Baum(){
        wurzel = new Knoten();
    }
    
    public void massenanhängen(int n){
        for(int z = 0; z < n; z++){
            wurzel.anhängen((int)(Math.random()*100));
        }    
    }
    

    public void massenanhängen_ord(int n){
        for(int z = 0; z < n; z++){
            wurzel.geordnetAnhängen((int)(Math.random()*100));
        }    
    }
    
    public int tiefe(){
        return wurzel.tiefe();
    }
    
    public void lösche(double x){
        if( x == wurzel.d.zahl){
        }
        else{
            boolean gefunde = wurzel.lösche(x);
            //wurzel = null;
        }
    }

}





