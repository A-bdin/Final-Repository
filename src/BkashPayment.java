class BkashPayment implements PaymentStrategy {
    private String phone;
    private String pin;
    private Bkash bkash;

    public BkashPayment(String phone, String pin, Bkash bkash) {
        this.phone = phone;
        this.pin = pin;
        this.bkash = bkash;
    }

    public boolean pay(double amount) {
        return bkash.pay(phone, pin, amount);
    }
}
