package api

import spock.lang.Specification

@spock.lang.Ignore
class PersonCommandSpec extends Specification {

    // tag::successful[]
    void 'valid person'() {
        given: 'a valid person'
        def command = new PersonCommand(name: 'Johny', age: 22)

        when: 'validating it'
        def result = command.validate()

        then: 'result should be true'
        result

        and: 'command should have no errors'
        !command.hasErrors()
    }
    // end::successful[]
}
