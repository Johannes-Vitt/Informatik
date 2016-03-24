class Zahltester:

    def __init__(self,kette):
        self.zeichenkette=kette

    def teste(self):
        zustand=0
        
        for zeichen in self.zeichenkette:
            zeichen_zahl=int(zeichen)
            zustand=self.zustand_wechseln(zustand, zeichen_zahl)
            
        print("Endzustand ",zustand)
        if zustand==2:
            print("Das Wort ",self.zeichenkette," wird akzeptiert")
        else:
            print("Das Wort ",self.zeichenkette," wird nicht akzeptiert")
            
        
    def zustand_wechseln(self,zustand, zeichen_aktuell):
        if zustand==0:           
            if zeichen_aktuell==0:
                zustand=0
            elif zeichen_aktuell==1:
                zustand=1
        elif zustand==1: 
            if zeichen_aktuell==0:
                zustand=2
            elif zeichen_aktuell==1:
                zustand=1
        elif zustand==2: 
            if zeichen_aktuell==0:
                zustand=2
            elif zeichen_aktuell==1:
                zustand=3
        elif zustand==3: 
            if zeichen_aktuell==0:
                zustand=2
            elif zeichen_aktuell==1:
                zustand=3
        return zustand

neu=Zahltester("0100110")
neu.teste()

