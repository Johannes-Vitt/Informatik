import random

class Tree:
    def __init__(self):
        self.root = None

    def add(self,weight):
        if(self.root==None):
            self.root = Knot(weight)
        else:
            self.root.add(weight)

    def in_order(self):
        if(self.root!=None):
            self.root.in_order()
class Knot:
    def __init__(self,weight):
        self.left_next=None
        self.right_next=None
        self.data = Data(weight)
        
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

    def in_order(self):
        if(self.left_next!=None):
            self.left_next.in_order()
        print("Weight: "+str(self.data.get_weight()))
        print("Color: "+ str(self.data.get_color()))
        print("Is male: "+ str(self.data.get_male()))
        if(self.right_next!=None):
            self.right_next.in_order()
class Data:
    def __init__(self,weight):
        self.color_num = hex(random.randrange(1,16777215,1))
        self.male = bool(int(random.random()+0.5))
        self.weight = weight

    def get_weight(self):
        return self.weight

    def get_color(self):
        return self.color_num

    def get_male(self):
        return self.male

class Mobile:
    def __init__(self):
        self.tree = Tree()
        for i in range(20):
            self.tree.add(random.randrange(40,100,1))

    def in_order(self):
        self.tree.in_order()
