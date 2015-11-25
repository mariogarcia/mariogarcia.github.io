package qc;

public class Supervisor {

    private Double AMOUNT_MAX_ACCEPTED = 1000d;
    private Double AMOUNT_MIN_CHECKED = 200d;

    public Loan process(final Loan loan) {
        if (loan.amount > AMOUNT_MAX_ACCEPTED) {
            return new Loan(State.REJECTED, loan.amount);
        }

        if (loan.amount < AMOUNT_MIN_CHECKED) {
            return new Loan(State.ACCEPTED, loan.amount);
        }

        return new Loan(State.PENDING, loan.amount);
    }

}
