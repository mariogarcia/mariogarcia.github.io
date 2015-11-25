package qc

import java.util.regex.Pattern
import spock.lang.Unroll
import spock.lang.Specification
import spock.genesis.Gen
import spock.genesis.generators.values.StringGenerator

class URIComposerSpec extends Specification {

    static final Integer DEFAULT = 100

    // tag::composerRules[]
    static final Pattern WORD            = ~/[a-z0-9-._\-]{1,8}/
    static final Pattern OPTIONAL_SLASH  = ~/[\/]{0,1}/
    static final Pattern OPTIONAL_WORD   = ~/($WORD){0,1}/

    static final Pattern COMPLIANT_FRAGMENT  = ~/$OPTIONAL_SLASH$OPTIONAL_WORD$OPTIONAL_SLASH/
    static final Pattern COMPLIANT_URI       = ~/s3:\/\/$WORD($COMPLIANT_FRAGMENT){1,10}/
    // end::composerRules[]

    // tag::composerTest[]
    @Unroll('Getting an URI from (h: #host, r: #root, p: #path)')
    void 'composing URI fragments to get a full URI'() {
        when: 'composing all pieces'
        def uri = URIComposer.compose(host, root, path).toString()

        then: 'we should get a compliant URI'
        uri ==~ COMPLIANT_URI // <1>

        where: 'possible values are'
        root << fragmentProperties.take(DEFAULT) // <2>
        path << fragmentProperties.take(DEFAULT) // <3>

        host = "s3://username"
    }

    StringGenerator getFragmentProperties() {
        return Gen.string(COMPLIANT_FRAGMENT)
    }
    // end::composerTest[]

}
