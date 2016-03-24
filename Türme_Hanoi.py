def tuerme():
    i = int(input("Geben sie an aus wie vielen Steinen ihr Turm bestehen soll: "))
    g = game(i)
    answer = input("Möchten sie die Aufgabe lösen? (y/n): ")
    if (answer=="y"):
        g.solve(i,g.s1,g.s2,g.s3)

    repeat = input("Möchten sie das Programm beenden? (y/n): ")
    if (repeat=="n"):
        tuerme()



class Stapel:
    def __init__(self,anzahl):
        self.erster = None
        if (anzahl >= 2):
            self.erster = Scheibe(1)
            self.erster.einfuegen(2,anzahl)
        else:
            if (anzahl==1):
                self.erster = Scheibe(1)
    
    def hinzufuegen(self,scheibe):
        if (self.erster==None):
            self.erster = scheibe
        else:
            h = self.erster
            self.erster = scheibe
            scheibe.naechster = h 
    def entnehmen(self):
        if (self.erster==None):
            return -1
        else:
            if (self.erster.naechster != None):
                h = self.erster.naechster
                h2 = self.erster
                self.erster = h
                return h2
            else:
                h = self.erster
                self.erster = None
                return h
            
    def get_object_breite(self,n):
        if(self.erster!=None):
            return self.erster.get_object_breite(n)
        else:
            return 0

    def get_length(self):
        if(self.erster!=None):
            return self.erster.get_length()
        else:
            return 0
        
class Scheibe:
    def __init__(self,breite):
        self.naechster = None
        self.breite = breite

    def einfuegen(self,breite,maximum):
        if (breite<=maximum):
            self.naechster = Scheibe(breite)
            self.naechster.einfuegen(breite+1,maximum)

    def hinzufuegen(self,scheibe):
        if(self.naechster == None):
            self.naechster = scheibe
        else:
            self.naechster.hinzufuegen(scheibe)
            
    def entnehmen(self):
        if(self.naechster.naechster==None):
            h = self.naechster
            self.naechster = None
            return h
        else:
            return self.naechster.entnehmen()


    def get_object_breite(self,n):
            if(n==1):
                return self.breite
            else:
                if(self.naechster!=None):
                    return self.naechster.get_object_breite(n-1)
                else:
                    return 0

    def get_length(self):
        if(self.naechster!=None):
            return 1+self.naechster.get_length()
        else:
            return 1
                
class game:
    def __init__(self,n):
        self.begin(n)
        print("Start!")
        self.show()

    def begin(self,n):
        self.s1 = Stapel(n)
        self.s2 = Stapel(0)
        self.s3 = Stapel(0)

    def move(self,from_where,to_where):
        h = from_where.entnehmen()
        to_where.hinzufuegen(h)

    def solve(self,i,s1,s2,s3):
        self.show()
        if(i>0):
            self.solve(i-1,s1,s3,s2)
            self.move(s1,s3)
            self.solve(i-1,s2,s1,s3)
               

    def show(self):
        first_length = self.s1.get_length()
        second_length = self.s2.get_length()
        third_length = self.s3.get_length()
        help_list = [first_length,second_length,third_length]
        stapel_liste = [self.s1,self.s2,self.s3]
        max_value = max(help_list)
        max_breite = first_length+second_length+third_length
        
        for i in range(0,max_value):
            for stapel in stapel_liste:
                self.print_leerzeichen(max_breite-stapel.get_object_breite(stapel.get_length()-i))
                self.print_minus(stapel.get_object_breite(stapel.get_length()-i))
                if(stapel.get_object_breite(stapel.get_length()-i)==0):
                    print("|", end="")
            print("")
        print("")

    def print_leerzeichen(self,n):
        for i in range(n):
            print(" ",end="")

    def print_minus(self,n):
        for i in range(n):
            print("-",end="")
            
tuerme()
