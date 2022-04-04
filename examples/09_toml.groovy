import groovy.toml.TomlBuilder
import groovy.toml.TomlSlurper

String input = '''
# This is a TOML document (taken from https://toml.io)

title = "TOML Example"

[owner]
name = "Tom Preston-Werner"
dob = 1979-05-27T07:32:00-08:00

[database]
enabled = true
ports = [ 8000, 8001, 8002 ]
data = [ ["delta", "phi"], [3.14] ]
temp_targets = { cpu = 79.5, case = 72.0 }

[servers]

[servers.alpha]
ip = "10.0.0.1"
role = "frontend"

[servers.beta]
ip = "10.0.0.2"
role = "backend"
'''

def toml = new TomlSlurper().parseText(input)

assert toml.title == 'TOML Example'
assert toml.owner.name == 'Tom Preston-Werner'
assert toml.database.ports == [8000, 8001, 8002]
assert toml.servers.alpha.ip == '10.0.0.1'
assert toml.servers.beta.ip == '10.0.0.2'


TomlBuilder builder = new TomlBuilder()
builder {
    title 'This is TOML document'
    servers {
        alpha {
            ip '10.0.0.1'
        }
        beta {
            ip '10.0.0.2'
        }
    }
}
assert builder.toString() ==
'''title = 'This is TOML document'
servers.alpha.ip = '10.0.0.1'
servers.beta.ip = '10.0.0.2'
'''
