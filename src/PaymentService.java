import java.util.List;

class PaymentService {

    public double calculateTotal(Cart cart) {
        double total = 0;

        List<CartItem> items = cart.getItems();

        for (CartItem item : items) {
            Product p = item.getProduct();
            int qty = item.getQuantity();
            total += p.getPrice() * qty;
        }

        return total;
    }

    public boolean processPayment(Cart cart, PaymentStrategy strategy) {

        double total = calculateTotal(cart);

        if (total <= 0) {
            System.out.println("Cart is empty.");
            return false;
        }

        System.out.println("Total amount: $" + total);

        boolean success = strategy.pay(total);

        if (success) {
            System.out.println("Payment successful ...");
        } else {
            System.out.println("Payment failed ...");
        }

        return success;
    }
}
