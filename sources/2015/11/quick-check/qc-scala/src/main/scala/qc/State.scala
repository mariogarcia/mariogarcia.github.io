package qc

object State extends Enumeration {
  type State = Value
  val PENDING, ACCEPTED, REJECTED = Value
}
