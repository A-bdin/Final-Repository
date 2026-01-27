import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    // adding to cart
    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity.");
            return;
        }
        items.add(new CartItem(product, quantity));
        System.out.println(quantity + " x " + product.getName() + " added to cart.");
    }

    // removing
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            CartItem ci = items.remove(index);
            System.out.println(ci.getProduct().getName() + " removed from cart.");
        } else {
            System.out.println("Invalid cart index.");
        }
    }

    // viewing
    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("----- Cart -----");
        for (int i = 0; i < items.size(); i++) {
            CartItem ci = items.get(i);
            System.out.println((i+1) + ". " + ci.getProduct().getName() +
                    " | $" + ci.getProduct().getPrice() +
                    " | Qty: " + ci.getQuantity());
        }
    }

    // checkout and purchasing
    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }
        double total = 0;
        for (CartItem ci : items) {
            Product p = ci.getProduct();
            int qty = ci.getQuantity();
            if (qty <= p.getQuantity()) {
                total += qty * p.getPrice();
                p.setQuantity(p.getQuantity() - qty);
            } else {
                System.out.println("Not enough stock for " + p.getName());
            }
        }
        System.out.println("Total amount: $" + total);
        items.clear();
    }

    // clearing
    public void clear() {
        items.clear();
        System.out.println("Cart cleared.");
    }

    // showing all items
    public ArrayList<CartItem> getItems() {
        return items;
    }
}
