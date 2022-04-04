import org.codehaus.groovy.control.MultipleCompilationErrorsException

/*

This script should throw the following exception from the shell.evaluate(script)

org.codehaus.groovy.control.MultipleCompilationErrorsException: startup failed:
    Script1.groovy: 8: [Static type checking] - Bad regex: Unclosed group near index 26
(\d{4})-(\d{1,2})-(\d{1,2}
@ line 8, column 12.
    assert date ==~ /(\d{4})-(\d{1,2})-(\d{1,2}/
    ^

    1 error

*/

String script = '''
import groovy.transform.TypeChecked

@TypeChecked(extensions = 'groovy.typecheckers.RegexChecker')
def testRegexChecker() {
    def date = '2022-04-03\'

    assert date ==~ /(\\d{4})-(\\d{1,2})-(\\d{1,2}/
}
'''

try {
    GroovyShell shell = new GroovyShell()
    shell.evaluate(script)

    throw new IllegalStateException("This line should not be executued - shell.evaluate(script) should throw a MultipleCompilationErrorsException")
} catch (MultipleCompilationErrorsException e) {

    assert e.message.contains('Bad regex: Unclosed group near index')
}

