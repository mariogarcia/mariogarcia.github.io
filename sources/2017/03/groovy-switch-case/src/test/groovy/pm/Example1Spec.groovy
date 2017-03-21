package pm

import spock.lang.Specification

class Example1Spec extends Specification {

    void ''() {
        given:
        Example1 instance = new Example1()


        when:
        Person john = new Person(name: 'john', age: 34)

        then:
        instance.example1(john) == 'john'

        when:
        Person carl = new Person(name: 'carl', age: 22)

        then:
        instance.example1(carl) == 'carl'
    }
}
