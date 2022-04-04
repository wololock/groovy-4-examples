import groovy.contracts.Ensures
import groovy.contracts.Invariant
import groovy.contracts.Requires
import org.apache.groovy.contracts.AssertionViolation

@Invariant({ speed >= 0 })
class Rocket {
    int speed = 0
    boolean started = false

    @Requires({ !started })
    Rocket startEngine() { tap {started = true }}

    @Requires({ started })
    Rocket stopEngine() { tap { started = false }}

    @Requires({ started })
    @Ensures({ old.speed < speed })
    Rocket accelerate(int value) { tap { speed += value }}
}

def rocket = new Rocket()
rocket.startEngine()
rocket.accelerate(10)

// Acceleration cannot be negative
try {
    rocket.accelerate(-5)
    throw new IllegalStateException("Previous line should throw an exception")
} catch (AssertionViolation e) {}

rocket.stopEngine()

// Cannot accelerate when the engine is stopped
try {
    rocket.accelerate(20)
    throw new IllegalStateException("Previous line should throw an exception")
} catch (AssertionViolation e) {}

// Cannot stop engine when it is already stopped
try {
    rocket.stopEngine()
    throw new IllegalStateException("Previous line should throw an exception")
} catch (AssertionViolation e) {}
