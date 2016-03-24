"""
Das Programm wird durch ein Doppelklick ausgeführt und öffnet automatisch ein
Kommandozeilenfenster auf dem alle Eingaben gemacht werden 
"""




def tuerme():
    """
    Erzeugt die Kommandozeilen eingabe
    """
    i = int(input("Geben sie an aus wie vielen Steinen ihr Turm bestehen soll (max. 20): "))
    g = game(i)
    answer = input("Möchten sie die Aufgabe lösen? (y/n): ")
    if (answer=="y"):
        g.solve(i,g.s1,g.s2,g.s3)
        g.show()

    repeat = input("Möchten sie das Programm beenden? (y/n): ")
    if (repeat=="n"):
        tuerme()



class Stapel:
    """
    Ein Stapel dient als Kopf einer Liste aus Scheiben
    """
    def __init__(self,anzahl):
        self.erster = None
        if (anzahl >= 2):
            self.erster = Scheibe(1)
            self.erster.einfuegen(2,anzahl)
        else:
            if (anzahl==1):
                self.erster = Scheibe(1)

    #Die Scheibe wird am Anfang der Liste hinzugefügt
    def hinzufuegen(self,scheibe):
        if (self.erster==None):
            self.erster = scheibe
            scheibe.naechster = None
        else:
            h = self.erster
            self.erster = scheibe
            scheibe.naechster = h

    #Die Scheibe am Anfang der Liste wird zurückgegeben
    def entnehmen(self):
        if (self.erster==None):
            return -1
        else:
            if (self.erster.naechster != None):
                h = self.erster.naechster
                h_2 = self.erster
                self.erster = h
                return h_2
            else:
                h = self.erster
                self.erster = None
                return h

    #Die Breite des n-ten Listenobjekts wird zurückgegeben       
    def get_object_breite(self,n):
        if(self.erster!=None):
            return self.erster.get_object_breite(n)
        else:
            return 0

    #Gibt rekursiv die Länge der ganzen Liste zurück
    def get_length(self):
        if(self.erster!=None):
            return self.erster.get_length()
        else:
            return 0
        
class Scheibe:
    """
    Ist das Knotenobjekt der Liste
    """
    def __init__(self,breite):
        self.naechster = None
        self.breite = breite
    #Rekursives einfügen eines Listenelements 
    def einfuegen(self,breite,maximum):
        if (breite<=maximum):
            self.naechster = Scheibe(breite)
            self.naechster.einfuegen(breite+1,maximum)
    #Gibt die breite des n-ten Objekts aus
    def get_object_breite(self,n):
            if(n==1):
                return self.breite
            else:
                if(self.naechster!=None):
                    return self.naechster.get_object_breite(n-1)
                else:
                    return 0
    #gibt rekursiv die Länge der Liste aus 
    def get_length(self):
        if(self.naechster!=None):
            return 1+self.naechster.get_length()
        else:
            return 1
                
class game:
    """
    Die Klasse game erzeugt ein neus Spiel es werden also drei Stapel erzeugt
    die dann mit Hilfe der Funktionen des Stapels "befüllt" werden können.
    """
    def __init__(self,n):
        self.begin(n)
        print("Start!")
        self.show()

    #erzeugt 3 Stapel
    def begin(self,n):
        self.s1 = Stapel(n)
        self.s2 = Stapel(0)
        self.s3 = Stapel(0)

    #kann die oberste Scheibe eines Stapels auf einen anderen setzen
    def move(self,from_where,to_where):
        h = from_where.entnehmen()
        to_where.hinzufuegen(h)

    #implementiert den Lösungsalgorithmus für die Türme von Hanoi
    def solve(self,i,s1,s2,s3):
        if(i>0):
            self.solve(i-1,s1,s3,s2)
            self.move(s1,s3)
            self.solve(i-1,s2,s1,s3)
               
    #Gibt die einzelnen Scheiben als "-" auf der Kommandozeile aus
    def show(self):
        first_length = self.s1.get_length()
        second_length = self.s2.get_length()
        third_length = self.s3.get_length()
        help_list = [first_length,second_length,third_length]
        stapel_liste = [self.s1,self.s2,self.s3]
        max_value = max(help_list)
        max_breite = first_length+second_length+third_length

        #die Schleife wird so oft durchlaufen wie hoch der höchste Stapel ist
        for i in range(1,max_value+1):
            #für jeden Stapel wird jedes seiner Objekt aufgerufen und die Breite
            #als "-" ausgegeben, der rest des Platzes wird mit " " aufgefüllt
            for stapel in stapel_liste:
                if(max_value-stapel.get_length()<=0):
                    self.print_leerzeichen(max_breite-stapel.get_object_breite(i))
                    self.print_minus(stapel.get_object_breite(i))
                    if(stapel.get_object_breite(i)==0):
                        print("|", end="")
                    else:
                        print(" ", end="")
                else:
                    self.print_leerzeichen(max_breite)
                    print("|", end="")
            print("")
        print("")

    #gibt n leerzeichen aus 
    def print_leerzeichen(self,n):
        for i in range(n):
            print(" ",end="")

    #gibt n minusse aus 
    def print_minus(self,n):
        for i in range(n):
            print("-",end="")
            
tuerme()
