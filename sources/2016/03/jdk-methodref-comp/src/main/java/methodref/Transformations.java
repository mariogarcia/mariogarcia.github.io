package methodref;

import java.util.function.Function;
import java.util.function.BiFunction;

public class Transformations {

    // tag::use[]
    /**
     * Utility function to convert a method reference to a
     * {@link Function}
     *
     * @param fn method reference matching a {@link Function}
     * @return the matching {@link Function}
     */
    public static <A,B> Function<A,B> use(Function<A,B> fn) {
        return fn;
    }

    /**
     * Utility function to convert a method reference to a
     * {@link BiFunction}
     *
     * @param fn method reference matching a {@link BiFunction}
     * @return the matching {@link BiFunction}
     */
    public static <A,B,C> BiFunction<A,B,C> use(BiFunction<A,B,C> fn) {
        return fn;
    }
    // end::use[]

    // tag::first[]
    public static String doStuff(Integer a, Integer b) {
        Integer sumResult = a + b + 10;

        return sumResult.toString();
    }
    // end::first[]

    // tag::second[]
    public static String doMoreStuff(Integer a, Integer b) {
        BiFunction<Integer,Integer,String> fn = use(Integer::sum)
            .andThen(x -> x + 10)
            .andThen(Object::toString);

        return fn.apply(a,b);
    }
    // end::second[]

    // tag::third[]
    public static String doEvenMoreStuff(Integer a, Integer b) {
        Function<Integer,String> extras =
            use(Object::toString).compose((Integer x) -> x + 10); // <1>

        BiFunction<Integer,Integer,String> fn =
            use(Integer::sum).andThen(extras); // <2>

        return fn.apply(a,b);
    }
    // end::third[]


}
