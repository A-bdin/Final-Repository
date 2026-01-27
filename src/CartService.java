import java.util.ArrayList;

public class CartService {

    private ArrayList<CartItem> cart;

    public CartService() {
        cart = new ArrayList<>();
    }

    // cart adding
    public void addToCart(Product product, int quantity) {
        cart.add(new CartItem(product, quantity));
    }


    public void removeFromCart(int index) {
        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
    }


    public void viewCart() {
        System.out.println("----- Cart -----");
        for (int i = 0; i < cart.size(); i++) {
            CartItem ci = cart.get(i);
            System.out.println(
                    (i+1) + ". " + ci.getProduct().getName() + " | $" + ci.getProduct().getPrice() + " | Qty: " + ci.getQuantity()
            );
        }
    }


    public void checkout() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem ci = cart.get(i);
            Product p = ci.getProduct();
            if (ci.getQuantity() <= p.getQuantity()) {
                total += ci.getQuantity() * p.getPrice();
                p.setQuantity(p.getQuantity() - ci.getQuantity());
            } else {
                System.out.println("Not enough stock for " + p.getName());
            }
        }
        System.out.println("Total: $" + total);
        cart.clear();
    }

    // Clear cart manually
    public void clearCart() {
        cart.clear();
    }

    // Getter for cart (if needed)
    public ArrayList<CartItem> getCart() {
        return cart;
    }
}

