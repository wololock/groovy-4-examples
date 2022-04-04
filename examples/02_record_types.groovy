
record Point(int x, int y) {}

def p1 = new Point(0, 0)
def p2 = new Point(2, 4)
def p3 = new Point(0, 0)

assert p1.x() == 0
assert p1.y() == 0
assert p2.x() == 2
assert p2.y() == 4
assert p1.toString() == 'Point[x=0, y=0]'
assert p2.toString() == 'Point[x=2, y=4]'
assert p1 == p3
