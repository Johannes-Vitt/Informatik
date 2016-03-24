import random

class Hotelier:
    def __init__(self):
        self.laenge = 0
        self.erster = None

    def unten_anhaengen(self):
        if (self.erster==None):
            self.erster = Knoten(Person())
        else:
            self.erster.unten_anhaengen()

    def gewicht_warteschlange_geben(self):
        return self.erster.gewicht_naechster()

    def knoten_ausgeben(self,number):
        if (number == 1):
            return self.erster
        else:
            self.erster.knoten_ausgeben(1,number)
class Person:
    def __init__(self):
        self.__name = "Müller"
        self.__masse = random.randint(40,100)

    def masse_nennen(self):
        return self.__masse

    def inhalt_nennen(self):
        return self.__name

    def ausgabe(self):
        print("Name "+self.__name)
        print("Masse "+str(self.__masse))
        
class Knoten:
    def __init__(self,perso):
        self.naechster = None
        self.pers = perso

    def unten_anhaengen(self):
        if (self.naechster==None):
            self.naechster = Knoten(Person())
        else:
            self.naechster.unten_anhaengen()

    def gib_inbhalt_aus(self):
        return self.pers.inhalt_nennen()
            
    def gewicht_geben(self):
        return self.pers.masse_nennen()

    def gewicht_naechster(self):
        if(self.naechster == None):
            return self.gewicht_geben()
        else:
            return self.gewicht_geben() + self.naechster.gewicht_naechster()
        
    def knoten_ausgeben(self,gesucht,number):
        if (self.naechster!=None):
            if(gesucht==number+1):
               return self.naechster
            else:
                return self.naechster.knoten_ausgeben(gesucht,number+1)
        else:
            return -1
