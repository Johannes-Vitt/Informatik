public class Ampel{
    
    Circle c1 = new Circle();
    Square s1 = new Square();
    Circle c2 = new Circle();
    Square s2 = new Square();
    Circle c3 = new Circle();
    Square s3 = new Square();
    int zustand = -1;
    static Ampel a;
    
    public static void main(String[] arg){
        a = new Ampel();
    }
    
    
    public Ampel(){
        s1.changeSize(50);
        s1.changeColor("black");
        s1.makeVisible();
        c1.moveHorizontal(9);
        c1.moveVertical(9);
        c1.changeColor("red");
        c1.makeVisible();

        s2.changeSize(50);
        s2.moveVertical(50);
        s2.changeColor("black");
        s2.makeVisible();
        c2.moveHorizontal(9);
        c2.moveVertical(59);
        c2.changeColor("yellow");
        c2.makeVisible();

        s3.changeSize(50);
        s3.moveVertical(100);
        s3.changeColor("black");
        s3.makeVisible();
        c3.moveHorizontal(9);
        c3.moveVertical(109);
        c3.changeColor("green");
        c3.makeVisible();
        zustand = 1;
        ampelAus();
    }

    
    public void ampelAus(){
        if( zustand == 1){
            c1.changeColor("black");
            c2.changeColor("black");
            c3.changeColor("black");
            zustand = 0;
        }    
    }

    public void ampelAufRot(){
        if(( zustand == 5) | (zustand == 1) ){
            c1.changeColor("red");
            c2.changeColor("black");
            c3.changeColor("black");
            zustand = 2;
        }    
    }

    public void ampelAufGrün(){
        if( zustand == 3){
            c1.changeColor("black");
            c2.changeColor("black");
            c3.changeColor("green");
            zustand = 4;
        }    
    }

    // geht nur von grün aus
    public void ampelAufGelb(){
        if( zustand == 4){
            c1.changeColor("black");
            c2.changeColor("yellow");
            c3.changeColor("black");
            zustand = 5;
        }    
    }
    public void ampelAufRotGelb(){
        if( zustand == 2){
            c1.changeColor("red");
            c2.changeColor("yellow");
            c3.changeColor("black");
            zustand = 3;
        }    
    }
    
    public void ampelGelbBlinken(int wieoft){
        if(( zustand == 0) | (zustand == 2) ){
            c1.changeColor("black");
            c2.changeColor("black");
            c3.changeColor("black");
            
            for(int z = 0; z < wieoft; z++){
                c2.changeColor("black");
                wait(150);
                c2.changeColor("yellow");
                wait(150);
            }
            c2.changeColor("black");
            zustand = 1;
        }    
    }
    
    public void ampelVerschieben(int x, int y){
        s1.moveHorizontal(x);
        s1.moveVertical(y);
        s2.moveHorizontal(x);
        s2.moveVertical(y);
        s3.moveHorizontal(x);
        s3.moveVertical(y);
        c1.moveHorizontal(x);
        c1.moveVertical(y);
        c2.moveHorizontal(x);
        c2.moveVertical(y);
        c3.moveHorizontal(x);
        c3.moveVertical(y);
    }
    
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }
    
}


















