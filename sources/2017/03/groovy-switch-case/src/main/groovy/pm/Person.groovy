package pm

import groovy.transform.Canonical
import groovy.transform.TupleConstructor

@Canonical
@TupleConstructor
class Person {
    String name
    Integer age
}
