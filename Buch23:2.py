def rec(n):
    if(n==1):
        return 1
    else:
        return ((2*n)-1)+rec(n-1)
    
