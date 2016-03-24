public class Ampelanlage{

    Ampel an, as, ao, aw;
    int zustand = -1;
    private String passwd;
    boolean on = false;
    
    Ampel[] anlage = new Ampel[4];
    
    
    public Ampelanlage(){
        Square s1 = new Square();
        s1.changeSize(250);
        s1.changeColor("black");
        s1.makeVisible();
        Square s2 = new Square();
        s2.changeSize(240);
        s2.changeColor("white");
        s2.makeVisible();

        Square s3 = new Square();
        s3.changeSize(250);
        s3.moveHorizontal(350);
        s3.changeColor("black");
        s3.makeVisible();
        Square s4 = new Square();
        s4.changeSize(240);
        s4.changeColor("white");
        s4.moveHorizontal(360);
        s4.makeVisible();
        
        Square s5 = new Square();
        s5.changeSize(250);
        s5.moveVertical(350);
        s5.changeColor("black");
        s5.makeVisible();
        Square s6 = new Square();
        s6.changeSize(240);
        s6.changeColor("white");
        s6.moveVertical(360);
        s6.makeVisible();
        
        Square s7 = new Square();
        s7.changeSize(250);
        s7.moveVertical(350);
        s7.moveHorizontal(350);
        s7.changeColor("black");
        s7.makeVisible();
        Square s8 = new Square();
        s8.changeSize(240);
        s8.changeColor("white");
        s8.moveVertical(360);
        s8.moveHorizontal(360);
        s8.makeVisible();


        
        
        
        aw = new Ampel();
        aw.ampelVerschieben(150, 350);
        ao = new Ampel();
        ao.ampelVerschieben(400, 100);
        an = new Ampel();
        an.ampelVerschieben(200, 100);
        as = new Ampel();
        as.ampelVerschieben(350, 350);
        zustand = 0;
    }
    
    public int zustandGeben(){
        return zustand;
    }
    
    public void onOff(){
        an.c1.changeColor("black");
        an.c2.changeColor("black");
        an.c3.changeColor("black");
        as.c1.changeColor("black");
        as.c2.changeColor("black");
        as.c3.changeColor("black");
        ao.c1.changeColor("black");
        ao.c2.changeColor("black");
        ao.c3.changeColor("black");
        aw.c1.changeColor("black");
        aw.c2.changeColor("black");
        aw.c3.changeColor("black");
        for(int z = 0; z < 5; z++){
            an.c2.changeColor("yellow");
            as.c2.changeColor("yellow");
            aw.c2.changeColor("yellow");
            ao.c2.changeColor("yellow");
            an.wait(150);
            an.c2.changeColor("black");
            as.c2.changeColor("black");
            aw.c2.changeColor("black");
            ao.c2.changeColor("black");
            an.wait(150);
        }
        if(on == false){
            an.c1.changeColor("red");
            as.c1.changeColor("red");
            aw.c1.changeColor("red");
            ao.c1.changeColor("red");
            zustand = 1;
            an.zustand = 2;
            as.zustand = 2;
            aw.zustand = 2;
            ao.zustand = 2;
            on = true;
        }
        else{
            zustand = 0;
            an.zustand = 1;
            as.zustand = 1;
            aw.zustand = 1;
            ao.zustand = 1;
            on = false;
        }    
    } 

    
    public void on(){
        aw.ampelGelbBlinken(3);
        aw.ampelAufRot();
        an.ampelGelbBlinken(3);
        an.ampelAufRot();
        ao.ampelGelbBlinken(3);
        ao.ampelAufRot();
        as.ampelGelbBlinken(3);
        as.ampelAufRot();
        zustand = 1;
    }
    
   public void weiterschalten(){
        if( zustand == 1){
            an.ampelAufRotGelb();
            as.ampelAufRotGelb();
            zustand = 2;
        }
        else if( zustand == 2){
            an.ampelAufGrün();
            as.ampelAufGrün();
            zustand = 3;
        }
        else if( zustand == 3){
            an.ampelAufGelb();
            as.ampelAufGelb();
            zustand = 4;
        }
        else if( zustand == 4){
            an.ampelAufRot();
            as.ampelAufRot();
            zustand = 5;
        }

        // ab hier die OW-Ampeln
        else if( zustand == 5){
            ao.ampelAufRotGelb();
            aw.ampelAufRotGelb();
            zustand = 6;
        }
        else if( zustand == 6){
            ao.ampelAufGrün();
            aw.ampelAufGrün();
            zustand = 7;
        }
        else if( zustand == 7){
            ao.ampelAufGelb();
            aw.ampelAufGelb();
            zustand = 8;
        }
        else if( zustand == 8){
            ao.ampelAufRot();
            aw.ampelAufRot();
            zustand = 1;
        }
        
   }

   public void autoSchalten(int anz){
        for(int z = 0; z < anz*9; z++){
            weiterschalten();
            if((zustand == 3) |(zustand == 7))ao.wait(2000);
            else ao.wait(500);
        }
   }
 

}






