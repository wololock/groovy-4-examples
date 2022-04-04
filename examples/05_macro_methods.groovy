def num = 42
def list = [1 ,2, 3]
def range = 0..5
def string = 'foo'

assert SV(num, list, range, string) == 'num=42, list=[1, 2, 3], range=[0, 1, 2, 3, 4, 5], string=foo'

assert SVI(range) == 'range=0..5'

assert NV(range) instanceof NamedValue

assert NV(string).name == 'string' && NV(string).val == 'foo'
