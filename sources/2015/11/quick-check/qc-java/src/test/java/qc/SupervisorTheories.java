package qc;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.generator.InRange;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

@RunWith(Theories.class)
public class SupervisorTheories {

    // tag::accepted[]
    @Theory public void automaticallyApproved( // <1>
        @ForAll @InRange(minDouble = 0d, maxDouble = 200d) Double amount) { // <2>

        Loan loan = new Loan(State.PENDING, amount); // <3>

        assumeThat(loan.state, equalTo(State.PENDING));
        assumeThat(loan.amount, lessThanOrEqualTo(200d)); // <4>

        Supervisor supervisor = new Supervisor();
        Loan processedLoan = supervisor.process(loan); // <5>

        assertEquals(processedLoan.state, State.ACCEPTED); // <6>
    }
    // end::accepted[]

    // tag::pending[]
    @Theory public void needsAFurtherStudy(
        @ForAll @InRange(minDouble = 201d, maxDouble = 1000d) Double amount) {

        Loan loan = new Loan(State.PENDING, amount);

        assumeThat(loan.state, equalTo(State.PENDING));
        assumeThat(loan.amount, allOf(
            greaterThan(200d),
            lessThanOrEqualTo(1000d)
        ));

        Supervisor supervisor = new Supervisor();
        Loan processedLoan = supervisor.process(loan);

        assertEquals(processedLoan.state, State.PENDING);
    }
    // end::pending[]

    // tag::rejected[]
    @Theory public void automaticallyRejected(
        @ForAll @InRange(minDouble = 1001d, maxDouble = 20000d) Double amount) {

        Loan loan = new Loan(State.PENDING, amount);

        assumeThat(loan.state, equalTo(State.PENDING));
        assumeThat(loan.amount, allOf(
            greaterThan(1000d),
            lessThanOrEqualTo(20000d)
        ));

        Supervisor supervisor = new Supervisor();
        Loan processedLoan = supervisor.process(loan);

        assertEquals(processedLoan.state, State.REJECTED);
    }
    // end::rejected[]

}
