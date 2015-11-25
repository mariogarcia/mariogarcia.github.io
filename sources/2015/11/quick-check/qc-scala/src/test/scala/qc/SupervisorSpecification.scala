package qc

import qc.State._
import org.scalacheck._
import Arbitrary.arbitrary

object SupervisorSpecification extends Properties("Supervisor") {
  import Prop.forAll

  // tag::acceptedLoans[]
  val acceptableLoans = for {
    amount <- Gen.chooseNum(0,200) // <1>
  } yield Loan(State.PENDING, amount) // <2>

  property("accepted loans") = forAll(acceptableLoans) { (loan: Loan) =>
    Supervisor.process(loan).state == State.ACCEPTED // <3>
  }
  // end::acceptedLoans[]

  // tag::rejectableLoans[]
  val rejectableLoans = for {
    amount <- Gen.chooseNum(1001,2000) // <1>
  } yield Loan(State.PENDING, amount) // <2>

  property("rejected loans") = forAll(rejectableLoans) { (loan: Loan) =>
    Supervisor.process(loan).state == State.REJECTED // <3>
  }
  // end::rejectableLoans[]

}
