class Element:
    """ Ein Element hat die Attribute wert und zeit  """

    def __init__(self, wert):
        self.__wert=wert
        self.__zeit="12:00"
    def ausgabe(self):
        print(self.__wert, self.__zeit)


class Knoten:
    """ Ein Knoten hat die Attribute inhalt und naechster """
    def __init__(self, inhalt):
        self.inhalt=inhalt
        self.naechster=None

    def laenge_ausgeben(self):
        pass

    def hinten_anhaengen(self,inhalt):
        pass
    
class Liste:
    def __init__(self):
        self.laenge=0
        self.erster=Abschluss()

    def laenge_ausgeben(self):
        print(self.erster.gib_anzahl_datenknoten())
        
    def hinten_anhaengen(self,inhalt):
        if(self.erster.is_abschluss()==True):           
            h = self.erster
            self.erster = Datenknoten(inhalt)
            self.erster.naechster = h

        else:
            self.erster.hinten_anhaengen(inhalt)
        
class Abschluss(Knoten):
    def __init__(self):
        Knoten.__init__(self,None)

    def gib_anzahl_datenknoten(self):
        return 0

    def hinten_anhaengen(self,inhalt):
        pass

    def is_abschluss(self):
        return True
    
class Datenknoten(Knoten):
    def __init__(self,inhalt):S
        Knoten.__init__(self,inhalt)

    def gib_anzahl_datenknoten(self):
        return 1 + self.naechster.gib_anzahl_datenknoten()

    def hinten_anhaengen(self,inhalt):
        if (self.naechster.is_abschluss==True):
            h = self.naechster
            self.naechster = Datenknoten(inhalt)
            self.naechster.naechster = h
            
    def is_abschluss(self):
        return False
