import groovy.transform.Immutable

interface Expr { }
@Immutable class IntExpr implements Expr { int value }
@Immutable(knownImmutableClasses = [Expr]) class NegExpr implements Expr { Expr expr }
@Immutable(knownImmutableClasses = [Expr]) class AddExpr implements Expr { Expr left, right }
@Immutable(knownImmutableClasses = [Expr]) class MulExpr implements Expr { Expr left, right }

static int eval(Expr e) {
    return switch (e) {
        case IntExpr -> e.value
        case NegExpr -> -eval(e.expr)
        case AddExpr -> eval(e.left) + eval(e.right)
        case MulExpr -> eval(e.left) * eval(e.right)
        default -> throw new IllegalArgumentException()
    }
}

@Newify(pattern = /.*Expr/)
static void main(String[] args) {

    assert eval(IntExpr(4)) == 4
    assert eval(NegExpr(IntExpr(10))) == -10
    assert eval(AddExpr(IntExpr(14), IntExpr(10))) == 24
    assert eval(MulExpr(IntExpr(2), AddExpr(IntExpr(10), IntExpr(6)))) == 32
    assert eval(NegExpr(AddExpr(IntExpr(2), IntExpr(3)))) == -5

}
