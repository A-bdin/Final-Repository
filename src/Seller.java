import java.util.*;

public class Seller extends User {
    private ArrayList<String> productIds;

    public Seller(String username, String password, String email){
        super(username, password, "SELLER", email);
        productIds = new ArrayList<>();
    }

    public ArrayList<String> getProductIds(){ return productIds; }
    public void addProductId(String id){ productIds.add(id); }
    public void removeProductId(String id){ productIds.remove(id); }
}
