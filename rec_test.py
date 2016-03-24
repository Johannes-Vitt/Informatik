def rec(value):
    print(value)
    if(value>=1):
        rec(value-1)
        rec(value+1)
