package pm

import spock.lang.Specification

class Example2Spec extends Specification {

    void 'check switch/case pattern matching'() {
        when:
        Mammal jonas = Example2.Cat('jonas', 4)

        then:
        Example2.check(jonas) == 'jonas cat is still young'

        when:
        Mammal anyDog = new Dog()

        then:
        Example2.check(anyDog) == 'I dont like dogs'
    }
}
