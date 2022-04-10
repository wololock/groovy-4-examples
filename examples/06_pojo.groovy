import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.stc.POJO

@POJO
@Immutable
@CompileStatic
class PojoPoint {
    int x, y

    static void main(String[] args) {
        PojoPoint point = new PojoPoint(1,1)
        System.out.println(point.toString())
    }
}

@Immutable
@CompileStatic
class NoPojoPoint {
    int x, y

    static void main(String[] args) {
        NoPojoPoint point = new NoPojoPoint(1,1)
        System.out.println(point.toString())
    }
}

def p1 = new PojoPoint(0, 0)
def p2 = new NoPojoPoint(0, 0)

p1.metaClass.with {
    // POJO methods exist
    assert methods.any { it.name == 'getX' }
    assert methods.any { it.name == 'getY' }
    assert methods.any { it.name == 'hashCode' }
    assert methods.any { it.name == 'equals' }
    assert methods.any { it.name == 'toString' }
    // Groovy methods does not exist
    assert !methods.any { it.name == 'getMetaClass' }
    assert !methods.any { it.name == 'setMetaClass' }
    assert !methods.any { it.name == '$getLookup' }
}

assert !(p1 instanceof GroovyObject)

p2.metaClass.with {
    // POJO methods exist
    assert methods.any { it.name == 'getX' }
    assert methods.any { it.name == 'getY' }
    assert methods.any { it.name == 'hashCode' }
    assert methods.any { it.name == 'equals' }
    assert methods.any { it.name == 'toString' }
    // Groovy methods exist
    assert methods.any { it.name == 'getMetaClass' }
    assert methods.any { it.name == 'setMetaClass' }
    assert methods.any { it.name == '$getLookup' }
}

assert p2 instanceof GroovyObject
