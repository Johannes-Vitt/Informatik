"""
Das Programm erzeugt ein sortierten Binärbaum. Zum Ausprobieren muss ein Objekt
Mobile erzeugt werden, welches zwanzig zufällige Werte in den Baum einfügt.
Bsp: m = Mobile()
Mit Hilfe der Funktion in_order, pre_order und past_order können die Elemente
sortiert zurück gegeben werden.
Bsp: m.in_order()
In diesem Beispiel werden Elemente eines Mobiles in einen sortierten Baum
eingefügt, dabei werden von Ihnen Gewicht(kg),Farbe(hex-Wert) und Geschlecht
gespeichert(boolean).
"""

import random

class Tree:
    """
    Dient als "Stativ" des Baumes, indem es das Wurzelobjekt referenziert
    """

    def __init__(self):
        self.root = None

    #Fügt dem Baum ein neues Element hinzu 
    def add(self,weight):
        if(self.root==None):
            self.root = Knot(weight)
        else:
            self.root.add(weight)

    #ruft die in_order funktion von der wurzel des baumes auf
    def in_order(self):
        if(self.root!=None):
            self.root.in_order()

    #ruft die pre_order funktion von der wurzel des baumes auf
    def pre_order(self):
        if(self.root!=None):
            self.root.pre_order()

    #ruft die past_order funktion von der wurzel des baumes auf
    def past_order(self):
        if(self.root!=None):
            self.root.past_order()
class Knot:
    """
    Dient als Knotenobjekt im Baum mit jeweils zwei Nachfolgern und einem Daten-
    knoten
    """
    def __init__(self,weight):
        self.left_next=None
        self.right_next=None
        self.data = Data(weight)

    #Fügt ein neues Objekt der Größe nach sortiert in den Baum ein        
    def add(self,weight):
        if(self.data.get_weight()<weight):
            if(self.right_next==None):
                self.right_next = Knot(weight)
            else:
                self.right_next.add(weight)
        else:
            if(self.left_next==None):
                self.left_next = Knot(weight)
            else:
                self.left_next.add(weight)

    #gibt die Werte des eigenen Datenobjekts in_order wieder
    def in_order(self):
        if(self.left_next!=None):
            self.left_next.in_order()
        print("Weight: "+str(self.data.get_weight()))
        print("Color: "+ str(self.data.get_color()))
        print("Is male: "+ str(self.data.get_male()))
        if(self.right_next!=None):
            self.right_next.in_order()

    #gibt die Werte des eigenen Datenobjekts pre_order wieder
    def pre_order(self):        
        print("Weight: "+str(self.data.get_weight()))
        print("Color: "+ str(self.data.get_color()))
        print("Is male: "+ str(self.data.get_male()))
        if(self.left_next!=None):
            self.left_next.pre_order()
        if(self.right_next!=None):
            self.right_next.pre_order()

    #gibt die Werte des eigenen Datenobjekts past_order wieder
    def past_order(self):        
        if(self.left_next!=None):
            self.left_next.past_order()
        if(self.right_next!=None):
            self.right_next.past_order()
        print("Weight: "+str(self.data.get_weight()))
        print("Color: "+ str(self.data.get_color()))
        print("Is male: "+ str(self.data.get_male()))
        
class Data:
    """
    Dient als Datenknoten und speichert somit alle Werte
    """
    #Konstruktor, speichert das ihm übergebene Gewicht und erzeugt als Hexadezi-
    #malwert eine Zufallsfarbe und ein Geschlecht als Boolean (true steht für
    #männlich)
    def __init__(self,weight):
        self.color_num = hex(random.randrange(1,16777215,1))
        self.male = bool(int(random.random()+0.5))
        self.weight = weight

    #Gibt das Gewicht (in kg) zurück
    def get_weight(self):
        return self.weight

    #Gibt die Farbe (als hex-Wert) zurück
    def get_color(self):
        return self.color_num

    #Gibt zurück ob das Geschlecht männlich ist(als boolean)
    def get_male(self):
        return self.male

class Mobile:
    """
    Dient als Test-Objekt, das 20 zufällige Objekte in den Baum einfügt
    """
    def __init__(self):
        self.tree = Tree()
        for i in range(20):
            self.tree.add(random.randrange(40,100,1))

    #ruft die in_order Funktion des Baums auf
    def in_order(self):
        self.tree.in_order()

    #ruft die pre_order Funktion des Baums auf
    def pre_order(self):
        self.tree.pre_order()

    #ruft die past_order Funktion des Baums auf
    def past_order(self):
        self.tree.past_order()
