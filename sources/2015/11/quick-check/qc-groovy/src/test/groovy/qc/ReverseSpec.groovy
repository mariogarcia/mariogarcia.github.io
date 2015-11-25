package qc

import spock.genesis.Gen
import spock.lang.Specification

class ReverseSpec extends Specification {

    void 'reversing a string [#original]'() {
        when: 'reversing a given string'
        def reversed = original.reverse()

        then: 'the reversed and the original should have same size'
        reversed.size() == original.size()

        and: 'when reversing the reversed should give us the original'
        reversed.reverse() == original

        where: 'input values are strings from 0 to 20 characters'
        original << Gen.string(20).take(100)
    }

}
