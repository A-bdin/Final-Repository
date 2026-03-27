class CashOnDelivery implements PaymentStrategy {

    public boolean pay(double amount) {
        System.out.println("Order placed successfully (Cash on Delivery)");
        System.out.println("Amount to pay on delivery: $" + amount);
        return true;
    }
}
