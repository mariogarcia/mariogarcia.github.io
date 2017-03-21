package pm

class Example2 {

    // tag::example0[]
    static String check(Mammal mammal) {
        switch (mammal) {
            case Dog:                return 'I dont like dogs'         // <1>
            case Cat('jonas', 4):    return 'jonas cat is still young' // <2>
            case Cat(any(), gt(10)): return 'it should be rocky cat'   // <3>
            case Cat:                return 'at least is a cat'        // <4>

            default:
            return 'no idea whatsoever'
        }
    }
    // end::example0[]

    static Cat Cat(String name, Integer age) {
        return new Cat(name: name, age: age)
    }

    static Cat Cat(Closure<Boolean> namePattern, Closure<Boolean> agePattern) {
        return { Cat cat ->
            namePattern(cat) && agePattern(cat)
        }
    }

    static Closure<Boolean> any() {
        return { -> true }
    }

    static Closure<Boolean> endsWith(String ending) {
        return { String s ->
            s.endsWith(ending)
        }
    }

    static Closure<Boolean> gt(Integer lowerBound) {
        return { Integer n -> n > lowerBound }
    }

    static Closure<Boolean> lt(Integer upperBound) {
        return { Integer n -> n < upperBound }
    }
}
