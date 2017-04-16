package api

import spock.lang.Specification
import spock.genesis.Gen
import grails.test.mixin.TestFor

@TestFor(GoodbyesController)
class GoodbyesControllerSpec extends Specification {

    void 'check a successful goodbye message'() {
        given: 'a proper person information'
        def command = new PersonCommand(name: name, age: 22)

        when: 'invoking the controller'
        controller.index(command)

        then: 'we should get the expected result'
        response.status == 200

        where: 'possible names are'
        name << ["mario", "johnny"]
    }
}
