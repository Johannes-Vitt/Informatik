

public class Listenelement
{
    Listenelement nachfolger;
    Daten datenelement;
    public Listenelement()
    {
        datenelement = new Daten();
    }

    public void hinzufuegen(){
        if (this.nachfolger == null){
            this.nachfolger = new Listenelement();
        }
        
        else{
            this.nachfolger.hinzufuegen();
        }
    }
    
    public void loeschen(int welches){
        if(welches == 1){
            Listenelement h = this.nachfolger;
            this.nachfolger = h.nachfolger;
        }
        else {
            nachfolger.loeschen(welches-1);
        }
    }
    
    public int zaehlen(){
        if (nachfolger != null){
            return nachfolger.zaehlen()+1;
        }
        else{
            return 1;
        }
    }
}
