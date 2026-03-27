class CreditCardPayment implements PaymentStrategy {
    private String card;
    private FakeBank bank;

    public CreditCardPayment(String card, FakeBank bank) {
        this.card = card;
        this.bank = bank;
    }

    public boolean pay(double amount) {
        return bank.pay(card, amount);
    }
}
