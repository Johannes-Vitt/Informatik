def euclid(a,b):
    print("A",a,"b",b)
    if (b==0):
        return a
    else:
        return euclid(b, a % b)

def euclid_mainstream(a,b):
    if(a>b):
        return euclid_mainstream(a-b,b)
    if(a<b):
        return euclid_mainstream(b-a,a)
    if(a==b):
        return a
    
