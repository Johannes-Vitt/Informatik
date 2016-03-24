import random 
class Tree:
    def __init__(self):
        self.root = None

    def add(self,value):
        if (self.root==None):
            self.root = Knot(value)
        else:
            self.root.add(value)
            
    def print_value(self):
        if(self.root!=None):
            self.root.print_value()
    
class Knot:
    def __init__(self,value):
        self.left = None
        self.right = None
        self.data = Data(value)

    def add(self,value):
        if (self.data.get_value() >=value):
            if(self.left==None):
                self.left = Knot(value)
            else:
                self.left.add(value)
        else:
            if(self.right==None):
                self.right = Knot(value)
            else:
                self.right.add(value)
                
    def print_value(self):
        if(self.left!=None):
            self.left.print_value()
        print(self.data.get_value())
        if(self.right!=None):
            self.right.print_value()
    
class Data:
    def __init__(self,value):
        self.value = value

    def get_value(self):
        return self.value

class Exercise:
    def __init__(self):
        self.t = None
        self.random_tree()
        self.t.print_value()
        
    def random_tree(self):
        self.t = Tree()
        for i in range(20):
            self.t.add(random.randrange(1,100,1))

