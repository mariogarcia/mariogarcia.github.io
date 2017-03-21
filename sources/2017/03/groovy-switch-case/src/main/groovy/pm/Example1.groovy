package pm

import static javaslang.API.$
import static javaslang.API.Case
import static javaslang.API.Match

import groovy.transform.CompileStatic

@CompileStatic
class Example1 {

    // tag::example0[]
    String example0(Person person) {
        if (person.name == 'carl' && person.age == 22) {
            return 'carl'
        }

        if (person.name == 'john' && person.age == 34) {
            return 'john'
        }

        return 'nobody'
    }
    // end::example0[]

    // tag::example1a[]
    String example1a(Person person) {
        switch (person) {
            case new Person('carl', 22): return 'carl'
            case new Person('john', 34): return 'john'

            default:
            return 'nobody'
        }
    }
    // end::example1a[]

    // tag::example1[]
    String example1(Person person) {
        switch (person) {
            case Person('carl', 22): return 'carl'
            case Person('john', 34): return 'john'

            default:
            return 'nobody'
        }
    }
    // end::example1[]

    // tag::Person[]
    static Person Person(String name, Integer age) {
        new Person(name, age)
    }
    // end::Person[]

    // tag::example2[]
    String example2(Person person) {
        switch (person) {
            case Person(endsWith('arl'), gt(25)): return 'carl' // <1>
            case Person(endsWith('hn'), lt(23)):  return 'john' // <2>
            case Person(any(), gt(60)):           return 'maria' // <3>

            default:
            return 'nobody' // <4>
        }
    }
    // end::example2[]

    // tag::PersonClosures[]
    static Closure<Boolean> Person(Closure<Boolean> name, Closure<Boolean> age) {
        return { Person p ->
            name(p.name) && age(p.age)
        }
    }
    // end::PersonClosures[]

    // tag::any[]
    static Closure<Boolean> any() {
        return { -> true }
    }
    // end::any[]

    // tag::endsWith[]
    static Closure<Boolean> endsWith(String ending) {
        return { String s ->
            s.endsWith(ending)
        }
    }
    // end::endsWith[]

    // tag::gt[]
    static Closure<Boolean> gt(Integer lowerBound) {
        return { Integer n -> n > lowerBound }
    }
    // end::gt[]

    // tag::lt[]
    static Closure<Boolean> lt(Integer upperBound) {
        return { Integer n -> n < upperBound }
    }
    // end::lt[]
}
