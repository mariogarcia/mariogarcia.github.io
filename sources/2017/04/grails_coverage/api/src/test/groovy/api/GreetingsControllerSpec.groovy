package api

import spock.lang.Specification
import spock.genesis.Gen
import grails.test.mixin.TestFor

@spock.lang.Ignore
@TestFor(GreetingsController)
class GreetingsControllerSpec extends Specification {

    // tag::successful_message[]
    void 'check a successful greetings message for mario'() {
        given: 'a proper person information'
        params.name = 'mario'
        params.age = 20

        when: 'invoking the controller'
        controller.index()

        then: 'we should get the expected result'
        response.json.message == "You're awesome!"

        and: 'the correct status code'
        response.status == 200
    }
    // end::successful_message[]

    @spock.lang.Ignore
    void 'check a successful greetings message'() {
        given: 'a proper person information'
        params.name = name
        params.age = age

        when: 'invoking the controller'
        controller.index()

        then: 'we should get the expected result'
        response.json.message

        and: 'status is always ok'
        response.status == 200

        where: 'possible names are'
        name << Gen.string(5, 10).take(100)
        age << Gen.integer(0, 100).take(100)
    }

    // tag::many_scenarios[]
    @spock.lang.Ignore
    @spock.lang.Unroll
    void 'check wrong scenarios'() {
        when: 'passing wrong parameters'
        params.name = name
        params.age = age

        and: 'creating a command to check constraints'
        def command = new PersonCommand(name: name, age: age)

        and: 'invoking controller'
        controller.index()

        then: 'there will be a validation error message'
        !command.validate() && response.json.message == "Please check name and age"

        and: 'the response status should be 400'
        response.status == 400

        where: 'possible wrong parameters could be'
        name << Gen.any([null, 'john', 'somethingreallylong']).take(100)
        age  << Gen.integer.take(100)
    }
    // end::many_scenarios[]
}
