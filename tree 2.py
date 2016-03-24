class Tree:
    def __init__(self):
        self.root = None

    def add(self,some_input):
        if(self.root==None):
            self.root = Knot(some_input)
        else:
            self.root.add(some_input)

    def in_order(self):
        if(self.root!=None):
            self.root.in_order()

    def pre_order(self):
        if(self.root!=None):
            self.root.pre_order()

    def post_order(self):
        if(self.root!=None):
            self.root.post_order()

    def search(self,search_value):
        if(self.root!=None):
            if(self.root.data.get_some_input()==search_value):
                return self.root
            else:
                self.root.search(search_value)

    def search_easier(self,search_value):
        if(self.root!=None):
            self.root.search_easier(search_value)
class Knot:
    def __init__(self,some_input):
        self.right_next=None
        self.left_next=None
        self.data = Data(some_input)

    def search(self,search_value):
        if(self.left_next.data.get_some_input()==search_value):
            return self.left_next
        if(self.right_next.data.get_some_input()==search_value):
            return self.right_next
        if(search_value<self.data.get_some_input()):
            self.left_next.search(search_value)
        else:
            self.right_next.search(search_value)

    def search_easier(self,search_value):
        if(search_value<self.data.get_some_input()):
            self.left_next.search_easier(search_value)
        if(search_value>self.data.get_some_input()):
            self.right_next.search_easier(search_value)
        if(search_value==self.data.get_some_input()):
            return self
            
    def add(self,some_input):
        if(some_input<self.data.get_some_input()):
            if(self.left_next==None):
                self.left_next = Knot(some_input)
            else:
                self.left_next.add(some_input)
        else:
            if(self.right_next==None):
                self.right_next = Knot(some_input)
            else:
                self.right_next.add(some_input)

    def in_order(self):
        if(self.left_next!=None):
            self.left_next.in_order()
        print(self.data.get_some_input())
        if(self.right_next!=None):
            self.right_next.in_order()

    def pre_order(self):
        print(self.data.get_some_input())
        if(self.left_next!=None):
            self.left_next.pre_order()
        if(self.right_next!=None):
            self.right_next.pre_order()

    def post_order(self):
        if(self.left_next!=None):
            self.left_next.post_order()
        if(self.right_next!=None):
            self.right_next.post_order()
        print(self.data.get_some_input())

class Data:
    def __init__(self,some_input):
        self.some_input = some_input

    def get_some_input(self):
        return self.some_input


