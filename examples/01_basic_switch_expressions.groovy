
static String test(Serializable value) {
    return switch (value) {
        case null -> 'just a null'
        case 0 -> 'zero'
        case 1 -> 'one'
        case { it instanceof List && it.empty } -> 'an empty list'
        case List -> 'a list'
        case '007' -> 'James Bond'
        case ~/\d+/ -> 'a number'
        default -> 'unknown'
    }
}

assert test(0)           == 'zero'
assert test(1)           == 'one'
assert test([])          == 'an empty list'
assert test([1,'a',23])  == 'a list'
assert test(23)          == 'a number'
assert test(null)        == 'just a null'
assert test('007')       == 'James Bond'
assert test([map: true]) == 'unknown'
