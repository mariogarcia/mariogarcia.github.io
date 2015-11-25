package qc

import qc.State._

object Supervisor {

  // tag::process[]
  def process(loan: Loan) : Loan = loan.amount match { // <1>
    case x if 0   until 201  contains x  => Loan(State.ACCEPTED, loan.amount) // <2>
    case x if 201 until 1001 contains x  => Loan(State.PENDING,  loan.amount) // <3>
    case _                               => Loan(State.REJECTED, loan.amount) // <4>
  }
  // end::process[]

}
