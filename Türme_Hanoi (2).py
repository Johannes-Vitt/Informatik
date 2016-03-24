class Stapel:
    def __init__(self,anzahl):
        self.erster = None
        if (anzahl >= 2):
            self.erster = Scheibe(anzahl)
            self.erster.einfuegen(anzahl-1)
        else:
            if (anzahl==1):
                self.erster = Scheibe(1)
    
    def einfuegen(self):
        pass
    def entnehmen(self):
        if (self.erster==None):
            return -1
        else:
            if (self.erster.naechster != None):
                return self.erster.entnehmen()
            else:
                h = self.erster
                self.erster = None
                return h
class Scheibe:
    def __init__(self,breite):
        self.naechster = None
        self.breite = breite

    def einfuegen(self,breite):
        self.naechster = Scheibe(breite)
        if (breite>1):
            self.naechster.einfuegen(breite-1)

    def entnehmen(self):
        if(self.naechster.naechster==None):
            h = self.naechster
            self.naechster = None
            return h
        else:
            return self.naechster.entnehmen()
            
def begin(n):
    s1 = Stapel(n)
    s2 = Stapel(0)
    s3 = Stapel(0)
    print(s1.entnehmen())
   

 
