package api

import groovy.transform.ToString
import grails.validation.Validateable

@ToString
class PersonCommand implements Validateable {
    String name
    Integer age

    static constraints = {
        name size: 5..10
        age size: 0..100
    }
}
