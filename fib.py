def fib(n):
    if (n >= 2):
        return fib(n-1)+fib(n-2)
    else:
        return 1

def print_fib(n):
    for i in range(n):
        print(i,fib(i))
