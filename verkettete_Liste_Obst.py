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

    def inhalt_aendern(self, neu):
        self.inhalt=neu

    def setze_naechster(self,naechster):
        self.naechster=naechster         

    def gib_naechster(self):
        return self.naechster

    def gib_inhalt(self):
        return self.inhalt

    def gib_inhalt_aus(self):
        hilf=self.gib_inhalt()
        hilf.ausgabe()

    def laenge_ausgeben(self):
        if(self.naechster==None):
            return 1
        else:
            return self.naechster.laenge_ausgeben() + 1


class Stativ:
    def __init__(self):
        self.laenge=0
        self.erster=None

    def obersten_setzen(self, oben):
        self.laenge=1
        self.erster = oben

    def unten_anhaengen(self, neu):
        k = self.erster
        for i in range (1,self.laenge):
            k=k.gib_naechster()
         
        k.setze_naechster(neu)
        neu.setze_naechster(None)
        self.laenge=self.laenge+1

    def obersten_entfernen(self):
        if self.erster==None:
            print("Es gibt keine Knoten in der Kette")
        else:
            weg=self.erster
            self.erster=weg.naechster
            self.laenge=self.laenge-1
            return weg

    def naechsten_nennen(self, knoten):
        return knoten.naechster
         

    def knoten_ausgeben(self):
        if self.erster==None:
            print("Es gibt keine Knoten in der Kette")
        else:
            k=self.erster
            for i in range(0,self.laenge):
                k.gib_inhalt_aus()
                k=k.naechster
            
    def laenge_ausgeben(self):
        if(self.erster==None):
            self.laenge = 0
            return 0
        else:
            return self.erster.laenge_ausgeben() + 1
        




f0=Element("Stativ")
f1=Element("Apfel")
f2=Element("Birne")
f3=Element("Dattel")
f4=Element("Erdbeere")
f5=Element("Feige")

k0=Knoten(f0)
k1=Knoten(f1)
k2=Knoten(f2)
k3=Knoten(f3)
k4=Knoten(f4)
k5=Knoten(f5)


# oberstes Element setzen
Obstkette=Stativ()
Obstkette.obersten_setzen(k0)
Obstkette.unten_anhaengen(k1)

Obstkette.unten_anhaengen(k2)
Obstkette.unten_anhaengen(k5)

v=Obstkette.naechsten_nennen(k1)
print(v)
print(v.inhalt)
v.inhalt.ausgabe()


Obstkette.knoten_ausgeben()






 

