public class Kreuzung{

    Ampelanlage aa;
    AutoNS ns;
    
    public Kreuzung(){
        aa = new Ampelanlage();
        aa.start();
        ns = new AutoNS(aa);
        ns.start();
//        aa.autoSchalten(5);
    }

    public void trafic(){
       
    }
    
    public void autoVonLinks(){
        ns = new AutoNS(aa);
        ns.fahren();
    }
}
