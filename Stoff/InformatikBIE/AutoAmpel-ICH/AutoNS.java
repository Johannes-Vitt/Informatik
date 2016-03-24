public class AutoNS extends Thread{

    Square s = new Square();
    Ampelanlage a;
    public AutoNS(Ampelanlage an)
    {
        a = an;
        s.moveHorizontal(-50);
        s.moveVertical(310);
    }
    
    
    public void start(){
        super.start();
    }
    
    public void run(){
        for(int i = 0; i < 10; i++){
            try{
                fahren();
                this.sleep((int)(Math.random()*1000));
            }
            catch(Exception e){}
        }
    }
    public void fahren(){
        s.makeVisible();
        s.slowMoveHorizontal(250);
        // schauen ob Grün
        s.slowMoveHorizontal(400);
        s.moveHorizontal(-650);
    }
}
