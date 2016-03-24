class Taxi:
    """ Die Klasse Taxi hat die Attribute benutzer und kennzeichen """
    
    def __init__(self,kennzeichen,benutzer):
        self.benutzer=benutzer
        self.kennzeichen=kennzeichen

    def name_geben(self):
        return self.benutzer

    
class Taxischlange:
    """ Die Klasse Taxischlange hat die Attribute
        laenge, max_laenge und liste
        liste ist ein Array, das Objekte vom Typ Taxi aufnehmen soll
    """
    
    def __init__(self, max_laenge):
        self.laenge=0
        self.liste=[]
        self.maximale_laenge=max_laenge
             
        for i in range(0,max_laenge):
            self.liste.append(None)


    def anzahl_taxis_nennen(self):
        return self.laenge

    def ankommen(self, taxi):
        if(self.laenge == self.maximale_laenge):
            return -1
        else:
            self.liste[self.laenge]=taxi
            self.laenge = self.laenge + 1
        

    def abfahren(self):
        if self.laenge==0:
            return -1
        else:
            for i in range(1,self.maximale_laenge):
                self.liste[i-1]=self.liste[i]
            self.laenge = self.laenge - 1
          
        
    def ausgabe(self):
        for i in range(0,self.laenge):
            if self.liste[i]!=None:
                print(i+1,":", self.liste[i].kennzeichen)
        print("------")
            
    

    


