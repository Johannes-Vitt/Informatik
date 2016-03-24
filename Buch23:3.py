def rec(n):
    return 300*factor(n)

def factor(n):
    if(n==1):
        return 1.2
    else:
        return 1.2*factor(n-1)
